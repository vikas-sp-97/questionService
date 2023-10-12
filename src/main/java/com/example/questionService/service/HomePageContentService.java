package com.example.questionService.service;

import com.example.questionService.HomePageContent;
import com.example.questionService.model.HomePageContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomePageContentService {
    @Autowired
    HomePageContentDao homePageContentDao;

    public HomePageContent getHomePageContent() {
        return homePageContentDao.getHomePageContent();
    }

}
