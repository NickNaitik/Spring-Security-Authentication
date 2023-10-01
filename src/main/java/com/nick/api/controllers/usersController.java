package com.nick.api.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1")
public class usersController {

    @GetMapping("/public")
    public String getPublicMsg() {
        return "JAI MATA DI!";
    }

    @GetMapping("/currentUser")
    public String getLoggedInUser(Principal principal) {
        return principal.getName();
    }
    
    @PreAuthorize("hasRole('Manager')")
    @GetMapping("/managerData")
    public String getManagerData() {
		return "MOJ KRDI !!";	
    }
    
    @PreAuthorize("hasRole('Developer')")
    @GetMapping("/devloperData")
    public String getDevData() {
        return "HUMSE NA HO PYEGA !!";
    }
}
