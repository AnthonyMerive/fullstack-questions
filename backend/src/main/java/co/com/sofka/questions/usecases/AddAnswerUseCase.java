package co.com.sofka.questions.usecases;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.routers.EmailSenderService;
import co.com.sofka.questions.usecases.interfaces.SaveAnswer;
import co.com.sofka.questions.utilties.MapperUtils;
import co.com.sofka.questions.utilties.VotacionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class AddAnswerUseCase implements SaveAnswer {

    @Autowired
    private EmailSenderService emailSenderService;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final MapperUtils mapperUtils;
    private final GetUseCase getUseCase;


    public AddAnswerUseCase(MapperUtils mapperUtils,
                            GetUseCase getUseCase,
                            AnswerRepository answerRepository,
                            QuestionRepository questionRepository) {

        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.getUseCase = getUseCase;
        this.mapperUtils = mapperUtils;
    }

    public Mono<QuestionDTO> apply(AnswerDTO answerDTO) {
        Objects.requireNonNull(answerDTO.getQuestionId(), "Id of the answer is required");
        return getUseCase.apply(answerDTO.getQuestionId()).flatMap(question ->
                answerRepository.save(mapperUtils.mapperToAnswer().apply(answerDTO))
                        .map(answer -> {
                            answerDTO.setAnswerId(answer.getId());
                            answerDTO.setCantidadVotos(
                                    VotacionUtils.contarVotos(question.getAnswerVotes(),
                                            answerDTO.getAnswerId()));
                            question.getAnswers().add(answerDTO);
                            return question;
                        }).doOnNext(questionDTO -> {
                            questionRepository.findById(answerDTO.getQuestionId());
                            sendEmail(questionDTO);
                        })
        );
    }

    private void sendEmail(QuestionDTO questionDTO) {
        emailSenderService.sendSimpleEmail(
                questionDTO.getEmail(),
                "Se ha añadido una nueva respuesta a la pregunta - ".concat(questionDTO.getQuestion()),
                "Nueva respuesta");
    }

}
