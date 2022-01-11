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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AddFavoriteUseCaseTest {

    AddFavoriteUseCase addFavoriteUseCase;
    FavoriteRepository favoriteRepository;
    MapperUtils mapperUtils;

    @BeforeEach
    void setup(){
        mapperUtils = new MapperUtils();
        favoriteRepository = mock(FavoriteRepository.class);
        addFavoriteUseCase = new AddFavoriteUseCase(
                favoriteRepository,
                mapperUtils);
    }

    @Test
    void apply(){
        when(favoriteRepository.findByUserId(any())).thenReturn(Mono.empty());
        when(favoriteRepository.save(any())).thenReturn(Mono.just(favorite()));
        StepVerifier.create(addFavoriteUseCase.apply(favoriteDTO()))
                .expectNextMatches(favoriteDTO -> {
                    assert favoriteDTO.getId().equals("Favorite001");
                    assert favoriteDTO.getUserId().equals("User001");
                    assert favoriteDTO.getFavoriteQuestionsId().get(0).equals("question001");
                    return true;
                }).verifyComplete();
        verify(favoriteRepository).findByUserId(any());
        verify(favoriteRepository).save(any());
    }

    private FavoriteDTO favoriteDTO() {
        var favoriteDTO = new FavoriteDTO();
        favoriteDTO.setUserId("User001");
        favoriteDTO.setQuestionId("question001");
        return favoriteDTO;
    }

    private Favorite favorite(){
        List<String> listFavoritos = new ArrayList<>();
        listFavoritos.add("question001");
        var favorite = new Favorite();
        favorite.setId("Favorite001");
        favorite.setUserId("User001");
        favorite.setFavoriteQuestionsId(listFavoritos);
        return favorite;
    }


}