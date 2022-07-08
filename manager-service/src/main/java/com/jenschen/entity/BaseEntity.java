package com.jenschen.entity;


import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    private Long id;
    private Boolean isDeleted;
    private Date deletedAt;
    private Long deletedBy;
    private Date createdAt;
    private Long createdBy;
    private Date updatedAt;
    private Long updatedBy;


    public void deleted(Date now, Long user){
        setIsDeleted(true);
        setDeletedAt(now);
        setDeletedBy(user);
        updated(now, createdBy);
    }

    public void created(Date now, Long user){
        setCreatedAt(now);
        setCreatedBy(user);
        updated(now, createdBy);
    }

    public void updated(Date now, Long user){
        setCreatedAt(now);
        setCreatedBy(user);
    }

}