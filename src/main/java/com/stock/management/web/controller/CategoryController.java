package com.stock.management.web.controller;

import com.stock.management.persistence.entity.Category;
import com.stock.management.persistence.repository.CategoryRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController extends WebController<Category, CategoryRepository> {}
