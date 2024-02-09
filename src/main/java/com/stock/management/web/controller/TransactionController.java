package com.stock.management.web.controller;

import com.stock.management.persistence.entity.Transaction;
import com.stock.management.persistence.repository.TransactionRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController extends WebController<Transaction, TransactionRepository> {}
