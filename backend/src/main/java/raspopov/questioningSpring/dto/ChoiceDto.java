package raspopov.questioningSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceDto {

    private Long choiceId;

    @NotBlank(message = "Choice description should not be empty")
    private String description;

    private InterviewedChoiceDto interviewedChoice;
}
