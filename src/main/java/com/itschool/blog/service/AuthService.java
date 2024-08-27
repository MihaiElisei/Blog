package com.itschool.blog.service;

import com.itschool.blog.model.LoginDTO;
import com.itschool.blog.model.RegisterDTO;

public interface AuthService {

    String login(LoginDTO loginDTO);

    String register(RegisterDTO registerDTO);
}
