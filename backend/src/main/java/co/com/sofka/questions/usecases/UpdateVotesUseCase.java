package co.com.sofka.questions.usecases;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.UpdateVoteDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.usecases.interfaces.SaveAnswersVotes;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Service
@Validated
public class UpdateVotesUseCase implements SaveAnswersVotes {

    private final QuestionRepository questionRepository;
    private final GetUseCase getUseCase;

    public UpdateVotesUseCase(QuestionRepository questionRepository,
                              GetUseCase getUseCase) {
        this.questionRepository = questionRepository;
        this.getUseCase = getUseCase;
    }

    @Override
    public Mono<QuestionDTO> updateVotes(UpdateVoteDTO updateVoteDTO) {
        Objects.requireNonNull(updateVoteDTO.getIdQuestion(), "idQuestion is required");
        Objects.requireNonNull(updateVoteDTO.getIdVoter(), "idVoter is required");
        return questionRepository.findById(updateVoteDTO.getIdQuestion())
                .flatMap(question -> {
                    question.getAnswersVotes().put(
                            updateVoteDTO.getIdVoter(),
                            updateVoteDTO.getIdAnswer());
                    return questionRepository.save(question);
                })
                .flatMap(question -> (getUseCase.apply(question.getId())));
    }

}
