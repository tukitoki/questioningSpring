package raspopov.questioningSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormDto {

    private long formId;

    @NotBlank(message = "Form description should not be empty")
    private String description;

    @Size(min = 2, max = 8, message = "Question count should be >= 2 and <= 8")
    @Valid
    private List<QuestionDto> questions;
}
