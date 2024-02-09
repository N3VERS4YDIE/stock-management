package com.stock.management.persistence.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "stores_categories")
@Data
public class StoreCategory {

    @EmbeddedId
    private StoreCategoryPK id;

    @ManyToOne
    @JoinColumn(name = "id_store", insertable = false, updatable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "id_category", insertable = false, updatable = false)
    private Category category;
}
