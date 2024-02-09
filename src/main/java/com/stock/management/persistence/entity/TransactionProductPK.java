package com.stock.management.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Data;

@Embeddable
@Data
public class TransactionProductPK implements Serializable {

    @Column(name = "id_transaction")
    private Integer idTransaction;

    @Column(name = "id_product")
    private Integer idProduct;
}
