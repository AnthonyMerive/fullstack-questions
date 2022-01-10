package co.com.sofka.questions.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;


@Document
public class Question {

    @Id
    private String id;
    private String userId;
    private String question;
    private String type;
    private String category;
    private String email;
    private Map<String, String> answersVotes;

/*
    private Boolean tieneRespuesta; //Propuesta para trabajar el punto5
    */

    public Question() {
        this.answersVotes = new HashMap<>();
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, String> getAnswersVotes() {
        return answersVotes;
    }

    public void setAnswersVotes(Map<String, String> answersVotes) {
        this.answersVotes = answersVotes;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", question='" + question + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", email='" + email + '\'' +
                ", answersVotes=" + answersVotes +
                '}';
    }
}
