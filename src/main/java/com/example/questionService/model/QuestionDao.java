package com.example.questionService.model;

import com.example.questionService.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByQuizType(String quizType);
}
