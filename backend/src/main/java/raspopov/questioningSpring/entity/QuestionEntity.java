package raspopov.questioningSpring.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Question")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @NotBlank(message = "Question description should not be empty")
    private String description;

    @Min(value = 1, message = "Number of choices should >= 1")
    @Max(value = 8, message = "Number of choices should <= 8")
    private int numberOfChoices;

    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    private FormEntity form;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min = 2, max = 8, message = "Choices count should be >= 2 and <= 8")
    private List<ChoiceEntity> choices;
}
