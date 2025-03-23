package com.cathay.java.entity;

import com.cathay.java.listener.EntityListener;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(EntityListener.class)
public class BaseEntity {

    @Column(name = "create_name", nullable = false)
    private String createName;

    @CreationTimestamp // 插入時自動填充
    @Column(name = "create_time", updatable = false)
    private LocalDateTime createTime;

    @Column(name = "update_name")
    private String updateName;

    @UpdateTimestamp // 更新時自動填充
    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
