package co.com.sofka.questions.repositories;

import co.com.sofka.questions.collections.Favorite;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface FavoriteRepository extends ReactiveCrudRepository<Favorite, String> {
    Mono<Favorite> findByUserId(String s);
}
