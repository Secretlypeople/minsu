package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ViewController {

    @RequestMapping("toViewName")
    public String toViewName(String viewName) {

        return viewName;
    }
}
