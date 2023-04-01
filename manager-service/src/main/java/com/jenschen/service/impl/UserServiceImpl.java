package com.jenschen.service.impl;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
import com.jenschen.entity.QuestionEntity;
import com.jenschen.entity.UserEntity;
import com.jenschen.exception.BizException;
import com.jenschen.helper.JwtHelper;
import com.jenschen.mapper.UserMapper;
import com.jenschen.request.UserLoginReq;
import com.jenschen.response.LoginResp;
import com.jenschen.service.AbstractService;
import com.jenschen.service.UserService;
import com.jenschen.util.ResultUtil;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends AbstractService<UserEntity> implements UserService, UserDetailsService {

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtHelper jwtHelper;

    /**
     * 用户密码前缀
     */
    @Value("${spring.security.prefix}")
    private String prefix;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, PasswordEncoder passwordEncoder, JwtHelper jwtHelper){
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtHelper = jwtHelper;
    }


    @Override
    public Response<Object> login(UserLoginReq userLoginReq) {
        UserDetails user = loadUserByUsername(userLoginReq.getUsername());

        if(!passwordEncoder.matches(prefix + userLoginReq.getPassword(), user.getPassword())){
            throw new BizException("用户名或者密码错误");
        }

        Map<String, String> data = new HashMap<>();

        data.put("username", user.getUsername());
        String authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        data.put("authorities", authorities);

        String jwt = jwtHelper.createJwtForClaims(userLoginReq.getUsername(), data);
        return ResultUtil.success(new LoginResp(jwt));
    }

    @Override
    public Response<Object> logout(UserLoginReq userLoginReq) {
        UserDetails user = loadUserByUsername(userLoginReq.getUsername());
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserEntity> queryWrapper = this.getDefaultQuery();
        queryWrapper.eq("username", username);
        UserEntity user = userMapper.selectOne(queryWrapper);
        List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities();
        return new User(user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                grantedAuthorities);
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
        return Collections.EMPTY_LIST;
    }
}
