package com.jenschen.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 公共实例类
 */
@Data
public class BaseEntity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private int id;

    /**
     * 是否删除
     */
    private Boolean isDeleted;

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;

    /**
     * 删除人
     */
    private int deletedBy;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 创建人
     */
    private int createdBy;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 更信任
     */
    private int updatedBy;


    public void deleted(LocalDateTime now, int user){
        setIsDeleted(true);
        setDeletedAt(now);
        setDeletedBy(user);
        updated(now, createdBy);
    }

    public void created(LocalDateTime now, int user){
        setCreatedAt(now);
        setCreatedBy(user);
        updated(now, createdBy);
    }

    public void updated(LocalDateTime now, int user){
        setUpdatedAt(now);
        setUpdatedBy(user);
    }

}