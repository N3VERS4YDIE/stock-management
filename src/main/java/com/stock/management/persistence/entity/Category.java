package com.stock.management.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategory;

    private String name;

    @ManyToMany
    @JoinTable(
        name = "categories_products",
        joinColumns = @JoinColumn(name = "id_category"),
        inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    @JsonIncludeProperties({ "idProduct", "name", "price" })
    private List<Product> products;

    @ManyToMany(mappedBy = "categories")
    @JsonIncludeProperties({ "idStore", "name" })
    private List<Store> stores;
}
