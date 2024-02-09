package com.stock.management.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Integer idProduct;

    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String description;

    @ManyToMany(mappedBy = "products")
    @JsonIncludeProperties({"idCategory", "name"})
    private List<Category> categories;

    @ManyToMany(mappedBy = "products")
    @JsonIgnoreProperties({ "products", "users" })
    private List<Store> stores;
}
