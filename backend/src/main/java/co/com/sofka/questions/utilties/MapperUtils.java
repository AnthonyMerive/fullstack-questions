package co.com.sofka.questions.utilties;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Favorite;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.FavoriteDTO;
import co.com.sofka.questions.model.QuestionDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtils {

    public Function<AnswerDTO, Answer> mapperToAnswer() {
        return updateAnswer -> {
            var answer = new Answer();
            answer.setQuestionId(updateAnswer.getQuestionId());
            answer.setUserId(updateAnswer.getUserId());
            answer.setAnswer(updateAnswer.getAnswer());
            return answer;
        };
    }

    public Function<QuestionDTO, Question> mapperToQuestion(String id) {

        return updateQuestion -> {
            var question = new Question();
            question.setId(id);
            question.setUserId(updateQuestion.getUserId());
            question.setCategory(updateQuestion.getCategory());
            question.setQuestion(updateQuestion.getQuestion());
            question.setUserId(updateQuestion.getUserId());
            question.setType(updateQuestion.getType());
            question.setEmail(updateQuestion.getEmail());
            question.setAnswersVotes(updateQuestion.getAnswerVotes());
            return question;
        };
    }

    public Function<FavoriteDTO, Favorite> mapperFavoriteDTOToEntity() {
        return updateFavorite -> {
            var favorite = new Favorite();
            favorite.setId(updateFavorite.getId());
            favorite.setUserId(updateFavorite.getUserId());
            favorite.setFavoriteQuestionsId(updateFavorite.getFavoriteQuestionsId());
            return favorite;
        };
    }

    public Function<Question, QuestionDTO> mapEntityToQuestion() {

        return entity -> new QuestionDTO(
                entity.getId(),
                entity.getUserId(),
                entity.getQuestion(),
                entity.getType(),
                entity.getCategory(),
                entity.getEmail(),
                entity.getAnswersVotes()
        );

    }

    public Function<Answer, AnswerDTO> mapEntityToAnswer() {
        return entity -> new AnswerDTO(
                entity.getQuestionId(),
                entity.getUserId(),
                entity.getAnswer(),
                entity.getId()
        );
    }

    public Function<Favorite, FavoriteDTO> mapEntityToFavoriteDTO() {
        return entity -> new FavoriteDTO(
                entity.getId(),
                entity.getUserId(),
                entity.getFavoriteQuestionsId()
        );
    }
}
