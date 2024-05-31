package com.stock.management.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions_products")
public class TransactionProduct {

    @EmbeddedId
    private TransactionProductPK id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_transaction", insertable = false, updatable = false)
    @JsonIncludeProperties({ "idTransaction", "dateTime" })
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "id_product", insertable = false, updatable = false)
    @JsonIncludeProperties({ "idProduct", "name" })
    private Product product;

    public TransactionProductPK getId() {
        return id;
    }

    public void setId(TransactionProductPK id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
