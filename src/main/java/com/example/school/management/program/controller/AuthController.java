package com.example.school.management.program.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/dashboard")
    public String defaultAfterLogin(Authentication authentication){
        for (GrantedAuthority authority:authentication.getAuthorities()){
            String role = authority.getAuthority();
            if (role.equals("ROLE_ADMIN")){
                return "redirect:admin/dashboard";
            } else if (role.equals("ROLE_TEACHER")) {
                return "redirect:teacher/dashboard";

                
            } else if (role.equals("ROLE_STUDENT")) {
                return "redirect:student/dashboard";
                
            }
        }
        return "redirect:/login?error";

    }

}
