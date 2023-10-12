package com.example.questionService.controller;

import com.example.questionService.HomePageContent;
import com.example.questionService.service.HomePageContentService;
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
