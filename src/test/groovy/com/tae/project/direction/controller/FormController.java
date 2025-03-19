package com.tae.project.direction.controller;


import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class FormController {

    @GetMapping("/")
    public String main() {
        return "main";
    }

}
