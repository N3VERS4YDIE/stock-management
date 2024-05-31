package com.stock.management.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "stores_users")
@Data
public class StoreUser {

    @EmbeddedId
    private StoreUserPK id;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @ManyToOne
    @JoinColumn(name = "id_store", insertable = false, updatable = false)
    @JsonIncludeProperties({ "idStore", "name" })
    private Store store;

    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    @JsonIncludeProperties({ "idUser", "name" })
    private User user;
}
