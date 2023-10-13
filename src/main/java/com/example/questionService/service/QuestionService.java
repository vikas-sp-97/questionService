package com.example.questionService.service;

import com.example.questionService.Question;
import com.example.questionService.model.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
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

    public String getContent() {
        String questionPageContent = "#question.size()";
        return evaluateExpression(questionPageContent);
    }

    public String evaluateExpression(String content){
        Optional<List<Question>> optionalQuestion = Optional.of(questionDao.findAll());

        if(optionalQuestion.stream().findAny().isPresent()){
            List<Question> question = optionalQuestion.get();
            ExpressionParser expressionParser = new SpelExpressionParser();
            StandardEvaluationContext context = new StandardEvaluationContext();
            context.setVariable("question", question);
            Expression parsedExpression = expressionParser.parseExpression(content);
            return parsedExpression.getValue(context, String.class);
        }

        return  "No questions found";
    }
}
