package com.london.housing.controller;

import org.springframework.stereotype.Controller;

/**
 * @author smith
 */
@Controller("")
public class HomeController {

//    @RequestMapping
    public String home() {
        return "index";
    }
}
