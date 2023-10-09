package com.example.testService.controller;

import com.example.testService.Question;
import com.example.testService.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/getAll")
    public List<Question> getAll(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/getQuestion/{id}")
    public Optional<Question> getQuestion(@PathVariable Integer id){
        return questionService.getQuestionById(id);
    }

    @GetMapping("/quizType/{quizType}")
    public List<Question> getQuestionByType(@PathVariable String quizType){
        return questionService.getQuestionByType(quizType);
    }

    @PostMapping("/add")
    @ResponseBody
    public String add(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public String update(@PathVariable Integer id, @RequestBody Question question){
        return questionService.updateQuestion(id, question);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
    }
}
