package com.stock.management.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.management.persistence.repository.TransactionRepository;
import com.stock.management.persistence.entity.Transaction;

@RestController
@RequestMapping("/transactions")
public class TransactionController extends WebController<Transaction, TransactionRepository> {}
