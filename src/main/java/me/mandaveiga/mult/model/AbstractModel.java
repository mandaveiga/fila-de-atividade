package me.mandaveiga.mult.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class AbstractModel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date created;

    @LastModifiedDate
    @Column
    private Date modified;

    public AbstractModel() {
        this.created = new Date();
        this.modified = new Date();
    }
}
