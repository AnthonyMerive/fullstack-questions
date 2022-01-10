package co.com.sofka.questions.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "favorite")
public class Favorite {

    @Id
    private String id;
    private String userId;
    private List<String> favoriteQuestionsId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getFavoriteQuestionsId() {
        return favoriteQuestionsId;
    }

    public void setFavoriteQuestionsId(List<String> favoriteQuestionsId) {
        this.favoriteQuestionsId = favoriteQuestionsId;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", favoriteQuestionsId=" + favoriteQuestionsId +
                '}';
    }
}
