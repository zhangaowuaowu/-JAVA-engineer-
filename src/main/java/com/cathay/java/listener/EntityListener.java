package com.cathay.java.listener;

import com.cathay.java.entity.BaseEntity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class EntityListener {
    @PrePersist
    public void prePersist(BaseEntity entity) {
        entity.setCreateName("Andy");
        entity.setUpdateName("Andy");
    }

    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        entity.setUpdateName("Andy");
    }
}
