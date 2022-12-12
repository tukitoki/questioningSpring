package raspopov.questioningSpring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import raspopov.questioningSpring.entity.FormEntity;

public interface FormRepo extends PagingAndSortingRepository<FormEntity, Long> {

    @Query(value = "SELECT f FROM FormEntity f WHERE f.description LIKE %?1%")
    Page<FormEntity> findByDescription(String description, Pageable pageable);
}
