package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.utilties.MapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateQuestionUseCaseTest {

    QuestionRepository questionRepository;
    MapperUtils mapperUtils;
    CreateQuestionUseCase createQuestionUseCase;

    @BeforeEach
    void setup(){
        mapperUtils = new MapperUtils();
        questionRepository = mock(QuestionRepository.class);
        createQuestionUseCase = new CreateQuestionUseCase(
                mapperUtils,
                questionRepository);
    }

    @Test
    void apply() {
        when(questionRepository.save(any())).thenReturn(Mono.just(question()));
        StepVerifier.create(createQuestionUseCase.apply(questionDTO()))
                .expectNextMatches(idQuestion -> idQuestion.equals("question001"))
                .verifyComplete();
        verify(questionRepository).save(any());
    }


    private QuestionDTO questionDTO() {
        Map<String,String> map = new HashMap<>();
        var questionDTO = new QuestionDTO(
//                "question001",
                "user001",
                "¿de qe color es el cielo?",
                "tipo",
                "categoria",
                "correo@test.com",
                map
        );
        return questionDTO;
    }

    private Question question(){
        return mapperUtils.mapperToQuestion("question001").apply(questionDTO());
    }



}