package com.stock.management.web.controller;

import com.stock.management.persistence.entity.User;
import com.stock.management.persistence.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends WebController<User, UserRepository> {}
