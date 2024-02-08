package com.stock.management.web.controller;

import com.stock.management.persistence.repository.StoreRepository;
import com.stock.management.persistence.entity.Store;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores")
public class StoreController extends WebController<Store, StoreRepository> {}
