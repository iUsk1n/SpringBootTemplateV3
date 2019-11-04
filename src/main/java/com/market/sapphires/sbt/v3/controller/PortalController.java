package com.market.sapphires.sbt.v3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.market.sapphires.sbt.v3.constants.Page;
import com.market.sapphires.sbt.v3.constants.Url;

@Controller
@RequestMapping(value = Url.PORTAL)
public class PortalController {

    @GetMapping(value = "")
    public String info(Model model) {
        return Page.PORTAL;
    }

}
