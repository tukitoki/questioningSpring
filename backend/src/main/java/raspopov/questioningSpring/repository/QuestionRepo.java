package raspopov.questioningSpring.repository;

import org.springframework.data.repository.CrudRepository;
import raspopov.questioningSpring.entity.QuestionEntity;

import java.util.List;

public interface QuestionRepo extends CrudRepository<QuestionEntity, Long> {

}
