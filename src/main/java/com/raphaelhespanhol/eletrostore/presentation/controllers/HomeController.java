package com.raphaelhespanhol.eletrostore.presentation.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RaphaelHespanhol
 * Presentation Layer - Converts and response on XML or JSON format
 */
@RestController
@RequestMapping(value = "api/sysinfo")
public class HomeController {
	
	// inject via application.properties
    @Value("${service.app.name}")
    private String appName;
	
    @Value("${service.app.version}")
    private String appVersion;
	
    @Value("${service.app.about}")
    private String appAbout;
    
    @GetMapping
    public String sysInfo() {
        return appName + " -> Tools(" + appVersion + ") by " + appAbout;
    }
}
