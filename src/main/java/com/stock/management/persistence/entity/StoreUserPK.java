package com.stock.management.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Data;

@Embeddable
@Data
public class StoreUserPK implements Serializable {

    @Column(name = "id_store")
    private Integer idStore;

    @Column(name = "id_user")
    private Integer idUser;
}
