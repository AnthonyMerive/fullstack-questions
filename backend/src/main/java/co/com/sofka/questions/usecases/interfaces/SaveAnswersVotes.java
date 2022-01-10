package co.com.sofka.questions.usecases.interfaces;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.UpdateVoteDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveAnswersVotes {

    Mono<QuestionDTO> updateVotes(@Valid UpdateVoteDTO updateVoteDTO);

}
