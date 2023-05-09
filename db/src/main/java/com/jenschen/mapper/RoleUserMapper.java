package com.jenschen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.entity.RoleEntity;
import com.jenschen.entity.RoleUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleUserMapper extends BaseMapper<RoleUserEntity> {

    /**
     * 根据用户ID获得所有的角色集合
     * @param userId 用户ID
     * @return 角色集合
     */
    List<RoleEntity> getRoleByUserId(Integer userId);

    /**
     * 根据用户ID,删除关联表中所有角色
     * @param userId 用户ID
     * @param operationId 操作人ID
     */
    int deleteByUserId(Integer userId, Integer operationId);

    /**
     * 根据角色ID,删除关联表中所有用户
     * @param roleId  角色ID
     * @param operationId 操作人ID
     */
    int deleteByRoleId(Integer roleId, Integer operationId);
}
