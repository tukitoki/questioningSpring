package raspopov.questioningSpring.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "interviewed_choice")
@IdClass(InterviewedChoiceId.class)
public class InterviewedChoiceEntity {

    @ManyToOne()
    @JoinColumn(name = "interviewed_id", nullable = false)
    @Id
    private InterviewedEntity interviewed;

    @ManyToOne()
    @JoinColumn(name = "choice_id", nullable = false)
    @Id
    private ChoiceEntity choice;

}
