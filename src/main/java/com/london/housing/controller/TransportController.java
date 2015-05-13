package com.london.housing.controller;

import com.london.housing.model.Statistics;
import com.london.housing.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author smith
 */
@Controller("transport")
public class TransportController {

    @Autowired
    private TransportService transportService;

    @RequestMapping
    public String test() {
        return "rest";
    }

    @RequestMapping("statistics")
    public @ResponseBody Statistics loadTransportStatistics(@RequestParam("code") String codes) throws Exception {
        return transportService.loadTransportData(codes.split(","));
    }
}
