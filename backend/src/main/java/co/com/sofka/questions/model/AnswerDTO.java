package co.com.sofka.questions.model;


import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Optional;

public class AnswerDTO {
    @NotBlank
    private String questionId;
    private String answerId;
    @NotBlank(message = "Debe existir el userId para este objeto")
    private String userId;
    @NotBlank
    private String answer;
    private Integer cantidadVotos;

    public AnswerDTO() {
    }

    public AnswerDTO(@NotBlank String questionId,
                     @NotBlank String userId,
                     @NotBlank String answer,
                     @NotBlank String answerId) {
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
        this.answerId = answerId;
    }

    public Integer getCantidadVotos() {
        return Optional.ofNullable(cantidadVotos).orElse(0);
    }

    public void setCantidadVotos(Integer cantidadVotos) {
        this.cantidadVotos = cantidadVotos;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDTO answerDTO = (AnswerDTO) o;
        return Objects.equals(userId, answerDTO.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "AnswerDTO{" +
                "questionId='" + questionId + '\'' +
                ", answerId='" + answerId + '\'' +
                ", userId='" + userId + '\'' +
                ", answer='" + answer + '\'' +
                ", cantidadVotos=" + cantidadVotos +
                '}';
    }
}
