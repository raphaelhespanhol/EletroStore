package com.raphaelhespanhol.eletrostore.presentation.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author RaphaelHespanhol
 * Presentation Layer - Converts and response on XML or JSON format
 */
@Controller
public class MainController {
	
	// inject via application.properties
    @Value("${service.app.name}")
    private String appName;
	
    @Value("${service.app.version}")
    private String appVersion;
	
    @Value("${service.app.about}")
    private String appAbout;
    
    @GetMapping("/")
    public String index(Model model) {
    	// View index.html through a thymeleaf variable
        model.addAttribute("appName", appName);
        model.addAttribute("appVersion", appVersion);
        model.addAttribute("appAbout", appAbout);
        
        // this just means render index.html from template folder
        return "index";
    }
}
