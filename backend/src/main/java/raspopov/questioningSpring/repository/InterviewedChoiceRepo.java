package raspopov.questioningSpring.repository;

import org.springframework.data.repository.CrudRepository;
import raspopov.questioningSpring.entity.InterviewedChoiceEntity;
import raspopov.questioningSpring.entity.InterviewedChoiceId;

public interface InterviewedChoiceRepo extends CrudRepository<InterviewedChoiceEntity, InterviewedChoiceId> {

}
