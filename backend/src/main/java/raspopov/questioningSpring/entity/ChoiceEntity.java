package raspopov.questioningSpring.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Choice")
public class ChoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long choiceId;

    @NotBlank(message = "Choice description should not be empty")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    @OneToMany(mappedBy = "choice", cascade = CascadeType.ALL)
    private List<InterviewedChoiceEntity> interviewedChoices;
}
