package com.example.questionService.model;

import com.example.questionService.HomePageContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HomePageContentDao {
    @Autowired
    HomePageContent homePageContent;


    public HomePageContent getHomePageContent() {
        homePageContent.setHeader("Welcome to the Website:");
        homePageContent.setContent("This is the body of Web page!");
        return homePageContent;
    }
}
