package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Favorite;
import co.com.sofka.questions.repositories.FavoriteRepository;
import co.com.sofka.questions.utilties.MapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GetFavoritesUseCaseTest {

    FavoriteRepository favoriteRepository;
    MapperUtils mapperUtils;
    GetFavoritesUseCase getFavoritesUseCase;


    @BeforeEach
    void setup(){
        mapperUtils = new MapperUtils();
        favoriteRepository = mock(FavoriteRepository.class);
        getFavoritesUseCase = new GetFavoritesUseCase(favoriteRepository,mapperUtils);
    }

    @Test
    void apply() {
        when(favoriteRepository.findByUserId(any())).thenReturn(Mono.just(favorite()));
        StepVerifier.create(getFavoritesUseCase.apply("User001"))
                .expectNextMatches(favoriteDTO -> {
                    assert favoriteDTO.getUserId().equals("User001");
                    assert favoriteDTO.getFavoriteQuestionsId().get(0).equals("question001");
                    assert favoriteDTO.getFavoriteQuestionsId().get(1).equals("question002");
                    return true;
                }).verifyComplete();
        verify(favoriteRepository).findByUserId(any());
    }


    private Favorite favorite(){
        List<String> listFavoritos = new ArrayList<>();
        listFavoritos.add("question001");
        listFavoritos.add("question002");

        var favorite = new Favorite();
        favorite.setId("Favorite001");
        favorite.setUserId("User001");
        favorite.setFavoriteQuestionsId(listFavoritos);

        return favorite;
    }






}