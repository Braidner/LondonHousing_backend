package com.london.housing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author smith
 */
@Controller("/aaaa")
public class HomeController {

//    @RequestMapping
    public String home() {
        return "index";
    }
}
