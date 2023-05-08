package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
import com.jenschen.config.WebSecurityConfig;
import com.jenschen.constant.CommonConstant;
import com.jenschen.entity.RoleEntity;
import com.jenschen.entity.RoleUserEntity;
import com.jenschen.entity.UserEntity;
import com.jenschen.enumeration.ErrorEnum;
import com.jenschen.exception.BizException;
import com.jenschen.helper.JwtHelper;
import com.jenschen.mapper.RolePermissionMapper;
import com.jenschen.mapper.RoleUserMapper;
import com.jenschen.mapper.UserMapper;
import com.jenschen.request.Page;
import com.jenschen.request.user.UserLoginReq;
import com.jenschen.request.user.UserReq;
import com.jenschen.response.*;
import com.jenschen.security.CurrentUser;
import com.jenschen.service.AbstractService;
import com.jenschen.service.UserService;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends AbstractService<UserEntity> implements UserService, UserDetailsService {

    private final UserMapper userMapper;

    private final RoleUserMapper roleUserMapper;

    private final RolePermissionMapper rolePermissionMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtHelper jwtHelper;

    /**
     * 用户密码前缀
     */
    @Value("${spring.security.prefix}")
    private String prefix;

    /**
     * 创建用户的默认密码
     */
    @Value("${spring.security.defaultPassword}")
    private String password;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RoleUserMapper roleUserMapper,
                           PasswordEncoder passwordEncoder, JwtHelper jwtHelper,
                           RolePermissionMapper rolePermissionMapper){
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtHelper = jwtHelper;
        this.roleUserMapper = roleUserMapper;
        this.rolePermissionMapper = rolePermissionMapper;
    }


    @Override
    public Response<Object> login(UserLoginReq userLoginReq) {
        CurrentUser user = (CurrentUser) loadUserByUsername(userLoginReq.getUsername());

        if(!passwordEncoder.matches(prefix + userLoginReq.getPassword(), user.getPassword())){
            throw new BizException("用户名或者密码错误");
        }

        Map<String, String> data = new HashMap<>();

        data.put("user_id", String.valueOf(user.getId()));
        data.put("username", user.getUsername());
        String authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        data.put(WebSecurityConfig.AuthorityClaimsName, authorities);

        String jwt = jwtHelper.createJwtForClaims(userLoginReq.getUsername(), data);
        return ResultUtil.success(new LoginResp(jwt));
    }

    @Override
    public Response<Object> logout(UserLoginReq userLoginReq) {
        UserDetails user = loadUserByUsername(userLoginReq.getUsername());
        return null;
    }

    @Override
    public Response<Object> page(Page page) {
        QueryWrapper<UserEntity> queryWrapper = this.getPageQueryWrapper(page);
        List<UserEntity> userEntityList = userMapper.selectList(queryWrapper);
        List<UserResp> resp = BeanUtil.copyToList(userEntityList, UserResp.class);

        //获得所有角色
        for(UserResp userResp : resp){
            List<RoleEntity> roleEntityList = roleUserMapper.getRoleByUserId(userResp.getId());
            List<RoleResp> roleRespList = BeanUtil.copyToList(roleEntityList, RoleResp.class);
            userResp.setRoleIds(roleRespList);
        }

        int count = userMapper.selectCount(this.getDefaultQuery());
        return ResultUtil.success(PageResp.build(count, resp));
    }

    @Override
    public Response<Object> add(UserReq userReq) {
        UserEntity user = BeanUtil.copyProperties(userReq, UserEntity.class);
        //TODO 敏感数据加密
        String password = passwordEncoder.encode(prefix + this.password);
        user.setPassword(password);
        user.created(LocalDateTime.now(),1);
        userMapper.insert(user);

        // 添加用户和角色的关系的表
        updateRoleUserRelation(userReq, user);

        return ResultUtil.success();
    }

    @Override
    public Response<Object> edit(UserReq userReq) {
        //默认用户admin禁止编辑
        if(userReq.getId() == CommonConstant.DEFAULT_USER_ADMIN_ID){
            return ResultUtil.error(ErrorEnum.ACCESS_DENIED);
        }

        UserEntity user = BeanUtil.copyProperties(userReq, UserEntity.class);
        user.updated(LocalDateTime.now(), 1);
        userMapper.updateById(user);

        //先删除
        roleUserMapper.deleteByUserId(user.getId(), 1);

        // 添加用户和角色的关系的表
        updateRoleUserRelation(userReq, user);

        return ResultUtil.success();
    }

    private void updateRoleUserRelation(UserReq userReq, UserEntity user){
        // 添加用户和角色的关系的表
        for(Integer roleId : userReq.getRoleIds()){
            roleUserMapper.insert(RoleUserEntity.builder()
                    .userId(user.getId())
                    .roleId(roleId)
                    .build());
        }
    }

    @Override
    public Response<Object> delete(Integer id) {
        //默认用户admin禁止删除
        if(id == CommonConstant.DEFAULT_USER_ADMIN_ID){
            return ResultUtil.error(ErrorEnum.ACCESS_DENIED);
        }

        UserEntity user = userMapper.selectById(id);
        user.deleted(LocalDateTime.now(), 1);
        userMapper.updateById(user);

        //同时删除关联表中信息
        roleUserMapper.deleteByUserId(user.getId(), 1);

        return ResultUtil.success();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserEntity> queryWrapper = this.getDefaultQuery();
        queryWrapper.eq("username", username);

        UserEntity user = userMapper.selectOne(queryWrapper);
        List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user.getId());

        return new CurrentUser(user.getId(),
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities);
    }

    public List<GrantedAuthority> getGrantedAuthorities(Integer userId) {
        List<RoleEntity> roleEntityList = roleUserMapper.getRoleByUserId(userId);
        List<GrantedAuthority> list = new ArrayList<>(roleEntityList.size());
        for(RoleEntity role : roleEntityList){
            list.add(new SimpleGrantedAuthority(String.valueOf(role.getId())));
        }
        return list;
    }
}
