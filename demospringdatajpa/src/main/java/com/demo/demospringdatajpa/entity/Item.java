package com.demo.demospringdatajpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Item extends BaseTimeEntity implements Persistable<String> {
    @Id
    private String id;

    public Item(String id){
        this.id=id;
    }

    @Override
    public boolean isNew() {
        return null==getCreatedDate();
    }
}
