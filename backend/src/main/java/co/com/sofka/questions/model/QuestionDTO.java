package co.com.sofka.questions.model;


import javax.validation.constraints.NotBlank;
import java.util.*;

public class QuestionDTO {
    private String id;
    @NotBlank
    private String userId;
    @NotBlank
    private String question;
    @NotBlank
    private String type;
    @NotBlank
    private String category;
    private String email;
    private List<AnswerDTO> answers;
    private Map<String,String> answerVotes;

    public QuestionDTO() {

    }

    public QuestionDTO(String userId,
                       String question,
                       String type,
                       String category,
                       String email,
                       Map<String,String> answerVotes) {
        this.userId = userId;
        this.question = question;
        this.type = type;
        this.category = category;
        this.email = email;
        this.answerVotes = answerVotes;
    }

    public QuestionDTO(String id,
                       String userId,
                       String question,
                       String type,
                       String category,
                       String email,
                       Map<String,String> answerVotes) {
        this.id = id;
        this.userId = userId;
        this.question = question;
        this.type = type;
        this.category = category;
        this.email = email;
        this.answerVotes = answerVotes;
    }

    public List<AnswerDTO> getAnswers() {
        this.answers = Optional.ofNullable(answers).orElse(new ArrayList<>());
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
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

    public Map<String, String> getAnswerVotes() {
        return answerVotes;
    }

//    public void setAnswerVotes(String idVoter, String idAnswer) {
//        Map<String, String> votos = Optional.ofNullable(this.answerVotes).orElse(new HashMap<>());
//        votos.put(idVoter,idAnswer);
//        this.answerVotes = votos;
//    }


    public void setAnswerVotes(Map<String, String> answerVotes) {
        this.answerVotes = answerVotes;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", question='" + question + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", answers=" + answers +
                ", answerVotes=" + answerVotes +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDTO that = (QuestionDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
