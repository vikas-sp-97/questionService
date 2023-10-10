package com.example.testService.service;

import com.example.testService.Question;
import com.example.testService.model.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public String addQuestion(Question question) {
        Optional<Integer> id = Optional.ofNullable(question.getId());
        if(id.isPresent()){
            Optional<Question> questionFromDb = questionDao.findById(question.getId());
            if(questionFromDb.isPresent()){
                return String.format("Question with ID: %s already present", question.getId());
            }
        }


        // Return the id of the saved Item.
        Question res = questionDao.save(question);
        return "success";

    }

    public List<Question> getQuestionByType(String quizType) {
        return questionDao.findByQuizType(quizType);
    }

    public String updateQuestion(Integer id, Question question) {
        Optional<Question> questionFromDb = questionDao.findById(id);
        if(questionFromDb.isPresent() &&
                questionFromDb.get().getId().equals(question.getId())){
            questionDao.save(question);
            return "Succuessfully updated questions for id: "+id;
        }
        else{
            return "Question not found OR ID provided missmatch";
        }
    }

    public Optional<Question> getQuestionById(Integer id) {
        return questionDao.findById(id);
    }

    public String deleteQuestion(Integer id) {
        Optional<Question> questionFromDB = questionDao.findById(id);
        if(questionFromDB.isPresent()){
            questionDao.deleteById(id);
            return "Successfully deleted question id: "+id;
        }else{
            return String.format("Question with id: %s not found in DB",id);
        }
    }
}
