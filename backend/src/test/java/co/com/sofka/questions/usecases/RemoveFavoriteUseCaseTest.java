package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Favorite;
import co.com.sofka.questions.model.FavoriteDTO;
import co.com.sofka.questions.repositories.FavoriteRepository;
import co.com.sofka.questions.utilties.MapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RemoveFavoriteUseCaseTest {

    FavoriteRepository favoriteRepository;
    MapperUtils mapperUtils;
    RemoveFavoriteUseCase removeFavoriteUseCase;

    @BeforeEach
    void setup(){
        mapperUtils = new MapperUtils();
        favoriteRepository = mock(FavoriteRepository.class);
        removeFavoriteUseCase = new RemoveFavoriteUseCase(favoriteRepository,mapperUtils);
    }

    @Test
    void apply() {
        when(favoriteRepository.findByUserId(any())).thenReturn(Mono.just(favorite()));
        when(favoriteRepository.save(any())).thenReturn(Mono.just(favorite2()));
        StepVerifier.create(removeFavoriteUseCase.apply(favoriteDTO()))
                .expectNextMatches(favoriteDTO -> {
                    assert favoriteDTO.getUserId().equals("User001");
                    assert favoriteDTO.getFavoriteQuestionsId().get(0).equals("question002");
                    assert favoriteDTO.getFavoriteQuestionsId().get(1).equals("question003");
                    assert favoriteDTO.getFavoriteQuestionsId().get(2).equals("question004");
                    assert favoriteDTO.getFavoriteQuestionsId().get(3).equals("question005");
                    return true;
                }).verifyComplete();
        verify(favoriteRepository).findByUserId(any());
        verify(favoriteRepository).save(any());
    }


    private Favorite favorite(){
        List<String> favoritos = new ArrayList<>();
        favoritos.add("question001");
        favoritos.add("question002");
        favoritos.add("question003");
        favoritos.add("question004");
        favoritos.add("question005");

        var favorite = new Favorite();
        favorite.setId("Favorites001");
        favorite.setUserId("User001");
        favorite.setFavoriteQuestionsId(favoritos);
        return favorite;
    }

    private Favorite favorite2(){
        List<String> favoritos = new ArrayList<>();
        favoritos.add("question002");
        favoritos.add("question003");
        favoritos.add("question004");
        favoritos.add("question005");

        var favorite = new Favorite();
        favorite.setId("Favorites001");
        favorite.setUserId("User001");
        favorite.setFavoriteQuestionsId(favoritos);

        return favorite;
    }

    private FavoriteDTO favoriteDTO(){
       return new FavoriteDTO(
                "User001",
                "question001"
        );
    }

}