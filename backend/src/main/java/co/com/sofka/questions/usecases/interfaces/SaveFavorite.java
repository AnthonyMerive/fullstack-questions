package co.com.sofka.questions.usecases.interfaces;

import co.com.sofka.questions.model.FavoriteDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveFavorite {
    Mono<FavoriteDTO> apply(@Valid FavoriteDTO favoriteDTO);
}
