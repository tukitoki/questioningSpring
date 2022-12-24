package raspopov.questioningSpring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import raspopov.questioningSpring.entity.FormEntity;
import raspopov.questioningSpring.entity.InterviewedEntity;

public interface InterviewedRepo extends PagingAndSortingRepository<InterviewedEntity, Long> {
}
