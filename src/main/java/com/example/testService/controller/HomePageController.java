package com.example.testService.controller;

import com.example.testService.HomePageContent;
import com.example.testService.service.HomePageContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomePageController
{
    @Autowired
    HomePageContentService homePageContentService;

    @GetMapping("home")
    public HomePageContent getHomePage(){
        return homePageContentService.getHomePageContent();
    }
}
