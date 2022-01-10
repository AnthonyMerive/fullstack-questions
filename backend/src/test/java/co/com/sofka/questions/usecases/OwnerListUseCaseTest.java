package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.utilties.MapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

class OwnerListUseCaseTest {

    QuestionRepository questionRepository;
    MapperUtils mapperUtils;
    OwnerListUseCase ownerListUseCase;

    @BeforeEach
    public void setup() {
        mapperUtils = new MapperUtils();
        questionRepository = mock(QuestionRepository.class);
        ownerListUseCase = new OwnerListUseCase(mapperUtils, questionRepository);
    }

    @Test
    void ownerListUseCaseTest() {
        when(questionRepository.findByUserId(any())).thenReturn(Flux.fromIterable(questions()));
        StepVerifier.create(ownerListUseCase.apply("userId-1"))
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getId().equals("idQuestion-1");
                    assert questionDTO.getUserId().equals("userId-1");
                    assert questionDTO.getQuestion().equals("Pregunta 1");
                    assert questionDTO.getType().equals("Tipo");
                    assert questionDTO.getCategory().equals("Categoria");
                    assert questionDTO.getEmail().equals("correo@correo.com");
                    assert questionDTO.getAnswerVotes().get("User01").equals("answerID1");
                    assert questionDTO.getAnswerVotes().get("User02").equals("answerID1");
                    return true;
                })
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getId().equals("idQuestion-2");
                    assert questionDTO.getUserId().equals("userId-1");
                    assert questionDTO.getQuestion().equals("Pregunta 2");
                    assert questionDTO.getType().equals("Tipo");
                    assert questionDTO.getCategory().equals("Categoria");
                    assert questionDTO.getEmail().equals("correo@correo.com");
                    assert questionDTO.getAnswerVotes().get("User01").equals("answerID2");
                    assert questionDTO.getAnswerVotes().get("User02").equals("answerID2");
                    return true;
                })
                .verifyComplete();
        verify(questionRepository).findByUserId(any());
    }


    private List<Question> questions() {

        Map<String, String> votos = new HashMap<>();
        votos.put("User01", "answerID1");
        votos.put("User02", "answerID1");

        Map<String, String> votos2 = new HashMap<>();
        votos2.put("User01", "answerID2");
        votos2.put("User02", "answerID2");

        var question1 = new Question();
        question1.setId("idQuestion-1");
        question1.setUserId("userId-1");
        question1.setQuestion("Pregunta 1");
        question1.setType("Tipo");
        question1.setCategory("Categoria");
        question1.setEmail("correo@correo.com");
        question1.setAnswersVotes(votos);

        var question2 = new Question();
        question2.setId("idQuestion-2");
        question2.setUserId("userId-1");
        question2.setQuestion("Pregunta 2");
        question2.setType("Tipo");
        question2.setCategory("Categoria");
        question2.setEmail("correo@correo.com");
        question2.setAnswersVotes(votos2);

        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);

        return questions;
    }

}