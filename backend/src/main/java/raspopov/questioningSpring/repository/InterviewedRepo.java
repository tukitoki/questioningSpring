package raspopov.questioningSpring.repository;

import org.springframework.data.repository.CrudRepository;
import raspopov.questioningSpring.entity.InterviewedEntity;

public interface InterviewedRepo extends CrudRepository<InterviewedEntity, Long> {
}
