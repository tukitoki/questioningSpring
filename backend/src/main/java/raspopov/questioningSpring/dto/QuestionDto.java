package raspopov.questioningSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {

    private Long questionId;

    @NotBlank(message = "Question description should not be empty")
    private String description;

    @Min(value = 1, message = "Number of choices should >= than 1")
    @Max(value = 8, message = "Number of choices should <= than 8")
    private int numberOfChoices;

    @Size(min = 2, max = 8, message = "Choices count should be >= 2 and <= 8")
    @Valid
    private List<ChoiceDto> choices;
}
