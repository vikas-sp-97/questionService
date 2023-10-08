package com.example.testService;

import org.springframework.stereotype.Component;

@Component
public class HomePageContent
{
    private String Header;
    private String Content;

    public String getHeader() {
        return Header;
    }

    public void setHeader(String header) {
        Header = header;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
