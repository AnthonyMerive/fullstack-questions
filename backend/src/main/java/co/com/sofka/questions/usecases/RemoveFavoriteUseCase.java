package co.com.sofka.questions.usecases;

import co.com.sofka.questions.model.FavoriteDTO;
import co.com.sofka.questions.repositories.FavoriteRepository;
import co.com.sofka.questions.utilties.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class RemoveFavoriteUseCase {
    private final FavoriteRepository favoriteRepository;
    private final MapperUtils mapperUtils;

    public RemoveFavoriteUseCase(FavoriteRepository favoriteRepository, MapperUtils mapperUtils) {
        this.favoriteRepository = favoriteRepository;
        this.mapperUtils = mapperUtils;
    }

    public Mono<FavoriteDTO> apply(FavoriteDTO favoriteDTO){
        Objects.requireNonNull(favoriteDTO.getUserId(), "Id of user is requerid");
        Objects.requireNonNull(favoriteDTO.getQuestionId(), "Id of question is requerid");
        return favoriteRepository.findByUserId(favoriteDTO.getUserId())
                .switchIfEmpty(Mono.error(()-> new Throwable("Usuario no cuenta con favoritos")))
                .flatMap(favorite -> {
                    favorite.getFavoriteQuestionsId().removeIf(id -> id.equals(favoriteDTO.getQuestionId()));
                    return favoriteRepository.save(favorite).flatMap(favorite1 -> (
                            Mono.just(mapperUtils.mapEntityToFavoriteDTO().apply(favorite1))
                    ));
                });
    }

}
