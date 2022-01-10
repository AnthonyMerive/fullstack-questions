package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.UpdateVoteDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.utilties.MapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateVotesUseCaseTest {

    QuestionRepository questionRepository;
    GetUseCase getUseCase;
    UpdateVotesUseCase updateVotesUseCase;
    MapperUtils mapperUtils;

    @BeforeEach
    public void setup() {
        mapperUtils = new MapperUtils();
        questionRepository = mock(QuestionRepository.class);
        getUseCase = mock(GetUseCase.class);
        updateVotesUseCase = new UpdateVotesUseCase(questionRepository, getUseCase);
    }

    @Test
    void updateVotesTest() {
        when(questionRepository.findById((String) any())).thenReturn(Mono.just(question()));
        when(questionRepository.save(any())).thenReturn(Mono.just(updateQuestion()));
        when(getUseCase.apply(any())).thenReturn(Mono.just(questionDTO()));
        StepVerifier.create(updateVotesUseCase.updateVotes(updateVoteDTO()))
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getId().equals("XXXXX");
                    assert questionDTO.getUserId().equals("YYYYY");
                    assert questionDTO.getQuestion().equals("Pregunta");
                    assert questionDTO.getType().equals("Tipo");
                    assert questionDTO.getCategory().equals("Categoria");
                    assert questionDTO.getEmail().equals("correo@correo.com");
                    assert questionDTO.getAnswers().equals(answers());
                    assert questionDTO.getAnswerVotes().get("YYYYY").equals("answer01");
                    return true;
                }).verifyComplete();
        verify(questionRepository).findById("XXXXX");
        verify(questionRepository).save(any());
        verify(getUseCase).apply(any());
    }


    private Question question() {
        var question = new Question();
        question.setId("XXXXX");
        question.setUserId("YYYYY");
        question.setQuestion("Pregunta");
        question.setType("Tipo");
        question.setCategory("Categoria");
        question.setEmail("correo@correo.com");
        return question;
    }

    private Question updateQuestion() {
        var updateQuestion = question();
        Map<String, String> voto = new HashMap<>();
        voto.put(updateVoteDTO().getIdVoter(),updateVoteDTO().getIdAnswer());
        updateQuestion.setAnswersVotes(voto);
        return updateQuestion;
    }

    private UpdateVoteDTO updateVoteDTO() {
        return new UpdateVoteDTO(
                "XXXXX",
                "YYYYY",
                "answer01"
        );
    }

    private QuestionDTO questionDTO(){
        var questionDTO = mapperUtils.mapEntityToQuestion().apply(updateQuestion());
        questionDTO.setAnswers(answers());
        return questionDTO;
    }

    private List<AnswerDTO> answers() {
        List<AnswerDTO> answersDTO = new ArrayList<>();
        //
        var answerDTO1 = new AnswerDTO(
                "XXXXX",
                "YYYYY",
                "Respuesta 1",
                "answer01");
        answerDTO1.setCantidadVotos(3);
        //
        var answerDTO2 = new AnswerDTO(
                "XXXXX",
                "YYYYY",
                "Respuesta 2",
                "answer02");
        answerDTO2.setCantidadVotos(1);
        //
        answersDTO.add(answerDTO1);
        answersDTO.add(answerDTO2);
        //
        return answersDTO;
    }


}