package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.UpdateVoteDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.routers.EmailSenderService;
import co.com.sofka.questions.utilties.MapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AddAnswerUseCaseTest {

    AddAnswerUseCase addAnswerUseCase;
    GetUseCase getUseCase;
    AnswerRepository answerRepository;
    QuestionRepository questionRepository;
    MapperUtils mapperUtils;
    EmailSenderService emailSenderService;

    @BeforeEach
    void setup() {
        emailSenderService = mock(EmailSenderService.class);
        mapperUtils = new MapperUtils();
        answerRepository = mock(AnswerRepository.class);
        questionRepository = mock(QuestionRepository.class);
        getUseCase = mock(GetUseCase.class);
        addAnswerUseCase = new AddAnswerUseCase(
                mapperUtils,
                getUseCase,
                answerRepository,
                questionRepository,
                emailSenderService);
    }

    @Test
    void apply() {
        when(getUseCase.apply(any())).thenReturn(Mono.just(questionDTO()));
        when(answerRepository.save(any())).thenReturn(Mono.just(answer()));
        doNothing().when(emailSenderService).sendSimpleEmail(any(),any(),any());
        StepVerifier.create(addAnswerUseCase.apply(answerDTO()))
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getAnswers().get(2).equals(answerDTO());
                    return true;
                }).verifyComplete();
    }

    private Answer answer(){
        var answer = new Answer();
        answer.setQuestionId("question001");
        answer.setUserId("User003");
        answer.setAnswer("Respuesta3 a la question001");
        return answer;
    }

    private AnswerDTO answerDTO() {
        return new AnswerDTO(
                "question001",
                "User003",
                "Respuesta3 a la question001",
                "answer003");
    }


    private QuestionDTO questionDTO() {
        Map<String,String> map = new HashMap<>();
        var questionDTO = new QuestionDTO(
                "question001",
                "user001",
                "¿de qe color es el cielo?",
                "tipo",
                "categoria",
                "correo@test.com",
                map
        );
        questionDTO.setAnswers(answers());
        return questionDTO;
    }

    private List<AnswerDTO> answers() {
        List<AnswerDTO> answersDTO = new ArrayList<>();

        var answerDTO1 = new AnswerDTO(
                "question001",
                "User001",
                "Respuest1 a la question001",
                "answer01");
        answerDTO1.setCantidadVotos(3);

        var answerDTO2 = new AnswerDTO(
                "question001",
                "User002",
                "Respuesta2 a la question001",
                "answer02");
        answerDTO2.setCantidadVotos(1);

        answersDTO.add(answerDTO1);
        answersDTO.add(answerDTO2);

        return answersDTO;
    }
}