package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.utilties.MapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GetUseCaseTest {


    QuestionRepository questionRepository;
    AnswerRepository answerRepository;
    MapperUtils mapperUtils;
    GetUseCase getUseCase;

    @BeforeEach
    void setup(){
        mapperUtils = new MapperUtils();
        questionRepository = mock(QuestionRepository.class);
        answerRepository = mock(AnswerRepository.class);
        getUseCase = new GetUseCase(
                mapperUtils,
                questionRepository,
                answerRepository);
    }

    @Test
    void apply() {

        when(questionRepository.findById((String) any())).thenReturn(Mono.just(question()));
        when(answerRepository.findAllByQuestionId(any())).thenReturn(Flux.fromIterable(answers()));

        StepVerifier.create(getUseCase.apply("User"))
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getId().equals("IdQuestion001");
                    assert questionDTO.getUserId().equals("UserId001");
                    assert questionDTO.getQuestion().equals("De que color es el cielo");
                    assert questionDTO.getType().equals("Tipo");
                    assert questionDTO.getCategory().equals("Categoria");
                    assert questionDTO.getEmail().equals("correo@correo.com");
                    assert questionDTO.getAnswerVotes().get("User003").equals("answer02");
                    assert questionDTO.getAnswerVotes().get("User002").equals("answer02");
                    assert questionDTO.getAnswers().get(0).getCantidadVotos().equals(0);
                    assert questionDTO.getAnswers().get(1).getCantidadVotos().equals(0);
                    return true;
                }).verifyComplete();
        verify(questionRepository).findById((String) any());
        verify(answerRepository).findAllByQuestionId(any());
    }



    private Question question() {
        Map<String,String> answersVotes = new HashMap<>();
        answersVotes.put("User003","answer02");
        answersVotes.put("User002","answer02");

        var question = new Question();
        question.setId("IdQuestion001");
        question.setUserId("UserId001");
        question.setQuestion("De que color es el cielo");
        question.setType("Tipo");
        question.setCategory("Categoria");
        question.setEmail("correo@correo.com");
        question.setAnswersVotes(answersVotes);
        return question;
    }

    private List<Answer> answers(){
        var answer1 = new Answer();
        answer1.setQuestionId("question001");
        answer1.setUserId("User003");
        answer1.setAnswer("Respuesta3 a la question001");

        var answer2 = new Answer();
        answer2.setQuestionId("question001");
        answer2.setUserId("User002");
        answer2.setAnswer("Respuesta3 a la question001");

        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);

        return answers;
    }







}