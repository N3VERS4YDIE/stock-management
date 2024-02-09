package com.stock.management.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Integer idTransaction;

    String type;

    @CreationTimestamp
    @Column(name = "date_time")
    private LocalDateTime dateTime = LocalDateTime.now();

    @ManyToMany(mappedBy = "transactions")
    @JsonIncludeProperties({ "idStore", "name" })
    private List<Store> stores;

    @OneToMany(mappedBy = "transaction")
    @JsonIgnoreProperties("id")
    private List<TransactionProduct> products;
}
