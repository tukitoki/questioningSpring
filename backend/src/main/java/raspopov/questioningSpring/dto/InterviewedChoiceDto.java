package raspopov.questioningSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raspopov.questioningSpring.entity.InterviewedChoiceId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewedChoiceDto {

    private InterviewedChoiceId interviewedChoiceId;

    private float percentileOfChoice;
}
