package co.com.sofka.questions.usecases;


import co.com.sofka.questions.model.FavoriteDTO;
import co.com.sofka.questions.repositories.FavoriteRepository;
import co.com.sofka.questions.utilties.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class GetFavoritesUseCase {

    private final FavoriteRepository favoriteRepository;
    private final MapperUtils mapperUtils;

    public GetFavoritesUseCase(FavoriteRepository favoriteRepository,
                               MapperUtils mapperUtils) {
        this.favoriteRepository = favoriteRepository;
        this.mapperUtils = mapperUtils;
    }

    public Mono<FavoriteDTO> apply(String userID) {
        return favoriteRepository.findByUserId(userID)
                .map(mapperUtils.mapEntityToFavoriteDTO());
    }

}
