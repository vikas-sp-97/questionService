package com.example.testService.service;

import com.example.testService.HomePageContent;
import com.example.testService.model.HomePageContentDao;
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
