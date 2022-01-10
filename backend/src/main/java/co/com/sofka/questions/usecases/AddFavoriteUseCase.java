package co.com.sofka.questions.usecases;


import co.com.sofka.questions.model.FavoriteDTO;
import co.com.sofka.questions.repositories.FavoriteRepository;
import co.com.sofka.questions.usecases.interfaces.SaveFavorite;
import co.com.sofka.questions.utilties.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Validated
public class AddFavoriteUseCase implements SaveFavorite {

    private final FavoriteRepository favoriteRepository;
    private final MapperUtils mapperUtils;

    public AddFavoriteUseCase(FavoriteRepository favoriteRepository, MapperUtils mapperUtils) {
        this.favoriteRepository = favoriteRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<FavoriteDTO> apply(FavoriteDTO favoriteDTO) {
        Objects.requireNonNull(favoriteDTO.getUserId(), "Id of user is requerid");
        Objects.requireNonNull(favoriteDTO.getQuestionId(), "Id of question is requerid");
        return favoriteRepository.findByUserId(favoriteDTO.getUserId())
                .switchIfEmpty(Mono.just(mapperUtils.mapperFavoriteDTOToEntity().apply(favoriteDTO)))
                .flatMap(favorite -> {
                    if(favorite.getFavoriteQuestionsId() == null){
                        List<String> favoriteQuestionsId = new ArrayList<>();
                        favorite.setFavoriteQuestionsId(favoriteQuestionsId);
                    }
                    favorite.getFavoriteQuestionsId().add(favoriteDTO.getQuestionId());
                    return favoriteRepository.save(favorite).flatMap(favorite1 -> (
                            Mono.just(mapperUtils.mapEntityToFavoriteDTO().apply(favorite1))
                    ));
                });
    }
}


