package com.example.testService.service;

import com.example.testService.Question;
import com.example.testService.model.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public String addQuestion(Question question) {
        questionDao.save(question);
        return "success";
    }

    public List<Question> getQuestionByType(String quizType) {
        return questionDao.findByQuizType(quizType);
    }
}
