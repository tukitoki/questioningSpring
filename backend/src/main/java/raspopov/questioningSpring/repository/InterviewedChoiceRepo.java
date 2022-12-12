package raspopov.questioningSpring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import raspopov.questioningSpring.entity.InterviewedChoiceEntity;
import raspopov.questioningSpring.entity.InterviewedChoiceId;

public interface InterviewedChoiceRepo extends CrudRepository<InterviewedChoiceEntity, InterviewedChoiceId> {

    @Query(value = "SELECT COUNT(intCh) FROM InterviewedChoiceEntity intCh " +
            "WHERE intCh.choice.choiceId = ?1")
    int findPercentageOfChoice(long choiceId);

}
