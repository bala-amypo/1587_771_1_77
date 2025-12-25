package com.example.demo.service;

import com.example.demo.entity.Auth;
import java.util.List;

public interface AuthService {

    Auth register(Auth auth);

    Auth getById(Long id);

    Auth findByEmail(String email);

    List<Auth> listInstructors();
}
