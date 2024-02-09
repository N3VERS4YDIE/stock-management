package com.stock.management.persistence.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "stores_transactions")
@Data
public class StoreTransaction {

    @EmbeddedId
    private StoreTransactionPK id;

    @ManyToOne
    @JoinColumn(name = "id_store", insertable = false, updatable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "id_transaction", insertable = false, updatable = false)
    private Transaction transaction;
}
