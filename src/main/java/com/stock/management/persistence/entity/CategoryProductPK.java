package com.stock.management.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Data;

@Embeddable
@Data
public class CategoryProductPK implements Serializable {

    @Column(name = "id_category")
    private Integer idCategory;

    @Column(name = "id_product")
    private Integer idProduct;
}
