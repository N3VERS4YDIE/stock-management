package com.stock.management.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "stores")
@Data
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_store")
    private Integer idStore;

    private String name;

    @ManyToMany
    @JoinTable(
        name = "stores_products",
        joinColumns = @JoinColumn(name = "id_store"),
        inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    @JsonIgnoreProperties("stores")
    @JsonIncludeProperties({ "idProduct", "name" })
    private List<Product> products;

    @ManyToMany
    @JoinTable(
        name = "stores_categories",
        joinColumns = @JoinColumn(name = "id_store"),
        inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    @JsonIncludeProperties({ "idCategory", "name" })
    private List<Category> categories;

    @OneToMany(mappedBy = "store")
    @JsonIgnoreProperties("id")
    private List<StoreUser> users;

    @ManyToMany
    @JoinTable(
        name = "stores_transactions",
        joinColumns = @JoinColumn(name = "id_store"),
        inverseJoinColumns = @JoinColumn(name = "id_transaction")
    )
    @JsonIncludeProperties({ "idTransaction", "type", "dateTime" })
    private List<Transaction> transactions;
}
