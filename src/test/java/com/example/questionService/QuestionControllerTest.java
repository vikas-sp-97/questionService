package com.example.questionService;

import com.example.questionService.controller.QuestionController;
import com.example.questionService.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class QuestionControllerTest {

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private QuestionController questionController;

    Question Q1 = new Question(1, "GK", "q1", "oq1", "oq2", "oq3", "oq4", "aq1");
    Question Q2 = new Question(2, "GK", "q2", "oq1", "oq2", "oq3", "oq4", "aq2");
    Question Q3 = new Question(3, "GK", "q3", "oq1", "oq2", "oq3", "oq4", "aq3");
    Question Q4 = new Question(4, "GK", "q4", "oq1", "oq2", "oq3", "oq4", "aq4");

    @Before
    public void setUp(){
        MockitoJUnit.rule();
        this.mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
    }

    @Test
    public void getAllQuestions_s() throws Exception {
        List<Question> records = new ArrayList<>(Arrays.asList(Q1, Q2, Q3, Q4));

        Mockito.when(questionService.getAllQuestions()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/question/getAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].quizType").value("GK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(4)));
    }

    @Test
    public void getQuestionByType_s() throws Exception {

        List<Question> records = new ArrayList<>(Arrays.asList(Q1, Q2));

        Mockito.when(questionService.getQuestionByType(Q1.getQuizType())).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/question/quizType/{quizType}", Q1.getQuizType())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void getQuestion_s() throws Exception {

        Mockito.when(questionService.getQuestionById(Q3.getId()))
                .thenReturn(java.util.Optional.of(Q3));

        mockMvc.perform(MockMvcRequestBuilders.get("/question/getQuestion/{id}", Q3.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.quizType").value("GK"));

    }

    @Test
    public void add() throws Exception {
        Question record = Question.builder()
                .id(7)
                .quizType("GLOBAL")
                .question("Largest country in world")
                .option1("x")
                .option2("y")
                .option3("z")
                .option4("v")
                .correctAnswer("x")
                .build();

        String content = objectWriter.writeValueAsString(record);

        Mockito.when(questionService.addQuestion(record)).thenReturn("success");

        MockHttpServletRequestBuilder m = MockMvcRequestBuilders.post("/question/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(m)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string("success"));
    }

    @Test
    public void updateQuestion_s() throws Exception {

        Question updatedRecord = Question.builder()
                .id(1)
                .quizType("GK")
                .question("q1")
                .option1("o1q1")
                .option2("o2q1")
                .option3("o3q1")
                .option4("o4q1")
                .correctAnswer("aq1")
                .build();

//        Mockito.when(questionService.getQuestionById(updatedRecord.getId())).thenReturn(java.util.Optional.ofNullable(Q1));

        Mockito.when(questionService.updateQuestion(updatedRecord.getId(), updatedRecord)).thenReturn("Succuessfully updated questions for id: "+updatedRecord.getId());

        MockHttpServletRequestBuilder m = MockMvcRequestBuilders.put("/question/update/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(updatedRecord));

        mockMvc.perform(m)
                .andExpect(status().isOk())
                .andExpect(content().string("Succuessfully updated questions for id: "+updatedRecord.getId()));
    }

    @Test
    public void deleteQuestion_s() throws Exception {
//        Mockito.when(questionService.getQuestionById(1)).thenReturn(java.util.Optional.ofNullable(Q1));

        Mockito.when(questionService.deleteQuestion(1)).thenReturn("Successfully deleted question id: "+1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/question/delete/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Successfully deleted question id: "+1));
    }
}
