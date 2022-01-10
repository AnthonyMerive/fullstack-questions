package co.com.sofka.questions.routers;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.FavoriteDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.UpdateVoteDTO;
import co.com.sofka.questions.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class QuestionRouter {

    @Bean
    public RouterFunction<ServerResponse> getAll(ListQuestionUseCase listQuestionUseCase) {
        return route(GET("/getAll"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listQuestionUseCase.get(), QuestionDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getOwnerAll(OwnerListUseCase ownerListUseCase) {
        return route(
                GET("/getOwnerAll/{userId}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                ownerListUseCase.apply(request.pathVariable("userId")),
                                QuestionDTO.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> create(CreateQuestionUseCase createQuestionUseCase) {
        Function<QuestionDTO, Mono<ServerResponse>> executor = questionDTO -> createQuestionUseCase.apply(questionDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));
        return route(
                POST("/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(QuestionDTO.class).flatMap(executor)
        );
    }


    @Bean
    public RouterFunction<ServerResponse> updateVote(UpdateVotesUseCase updateVotesUseCase) {
        Function<UpdateVoteDTO, Mono<ServerResponse>> executor = updateVoteDTO -> updateVotesUseCase.updateVotes(updateVoteDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));
        return route(
                PUT("/updatevote").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(UpdateVoteDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> get(GetUseCase getUseCase) {
        return route(
                GET("/get/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getUseCase.apply(
                                        request.pathVariable("id")),
                                QuestionDTO.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getFavorites(GetFavoritesUseCase getFavoritesUseCase) {
        return route(
                GET("/favorite/{id-user}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getFavoritesUseCase.apply(
                                        request.pathVariable("id-user")),
                                FavoriteDTO.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> addAnswer(AddAnswerUseCase addAnswerUseCase) {
        return route(POST("/add").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(AnswerDTO.class)
                        .flatMap(addAnswerDTO -> addAnswerUseCase.apply(addAnswerDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> addFavorite(AddFavoriteUseCase addFavoriteUseCase) {
            return route(POST("/favorite/add").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(FavoriteDTO.class)
                        .flatMap(favoriteDTO -> addFavoriteUseCase.apply(favoriteDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> delete(DeleteUseCase deleteUseCase) {
        return route(
                DELETE("/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteUseCase.apply(request.pathVariable("id")),
                                Void.class))
        );
    }


    @Bean
    public RouterFunction<ServerResponse> removeFavorite(RemoveFavoriteUseCase removeFavoriteUseCase) {
        return route(
                PUT("/favorite/remove").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(FavoriteDTO.class)
                        .flatMap(favoriteDTO -> removeFavoriteUseCase.apply(favoriteDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result)))
                        .onErrorResume(
                                throwable -> ServerResponse.badRequest().body(throwable.getMessage(),
                                String.class))
        );
    }
}
