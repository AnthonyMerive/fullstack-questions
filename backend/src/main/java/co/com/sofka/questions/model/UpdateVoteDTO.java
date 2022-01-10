package co.com.sofka.questions.model;

import java.util.Objects;

public class UpdateVoteDTO {

    private String idQuestion;
    private String idVoter;
    private String idAnswer;

    public UpdateVoteDTO() {
    }

    public UpdateVoteDTO(String idQuestion,
                         String idVoter,
                         String idAnswer) {
        this.idQuestion = idQuestion;
        this.idVoter = idVoter;
        this.idAnswer = idAnswer;
    }

    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getIdVoter() {
        return idVoter;
    }

    public void setIdVoter(String idVoter) {
        this.idVoter = idVoter;
    }

    public String getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(String idAnswer) {
        this.idAnswer = idAnswer;
    }

    @Override
    public String toString() {
        return "UpdateVoteDTO{" +
                "idQuestion='" + idQuestion + '\'' +
                ", idVoter='" + idVoter + '\'' +
                ", idAnswer='" + idAnswer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateVoteDTO that = (UpdateVoteDTO) o;
        return Objects.equals(idQuestion, that.idQuestion) && Objects.equals(idVoter, that.idVoter) && Objects.equals(idAnswer, that.idAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idQuestion, idVoter, idAnswer);
    }
}
