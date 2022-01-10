package co.com.sofka.questions.model;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

public class FavoriteDTO {

    private String id;
    @NotBlank
    private String userId;
    @NotBlank
    private String questionId;
    private List<String> favoriteQuestionsId;

    public FavoriteDTO() {
    }

    public FavoriteDTO(String id,
                       String userId,
                       List<String> favoriteQuestionsId) {
        this.id = id;
        this.userId = userId;
        this.favoriteQuestionsId = favoriteQuestionsId;
    }

    public FavoriteDTO(String userId,
                       String questionId) {
        this.userId = userId;
        this.questionId = questionId;
    }

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

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public List<String> getFavoriteQuestionsId() {
        return favoriteQuestionsId;
    }

    public void setFavoriteQuestionsId(List<String> favoriteQuestionsId) {
        this.favoriteQuestionsId = favoriteQuestionsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteDTO that = (FavoriteDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(questionId, that.questionId) &&
                Objects.equals(favoriteQuestionsId, that.favoriteQuestionsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                userId,
                questionId,
                favoriteQuestionsId);
    }

    @Override
    public String toString() {
        return "FavoriteDTO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", favoriteQuestionsId=" + favoriteQuestionsId +
                '}';
    }
}
