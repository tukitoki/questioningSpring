package raspopov.questioningSpring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class InterviewedChoiceId implements Serializable {

    private Long interviewed;
    private Long choice;
}
