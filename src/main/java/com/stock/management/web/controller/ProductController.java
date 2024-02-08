package com.stock.management.web.controller;

import com.stock.management.persistence.repository.ProductRepository;
import com.stock.management.persistence.entity.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController extends WebController<Product, ProductRepository> {}
