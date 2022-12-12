package raspopov.questioningSpring.dto;

import com.vladmihalcea.hibernate.type.basic.Inet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InterviewedDto {

    private FormDto form;

    private Long interviewedId;

    private Timestamp interviewedDate;
    private Inet interviewedIp;

    private List<InterviewedChoiceDto> interviewedChoices;

    private List<InterviewedChoiceDto> allInterviewedChoices;

    public String getInterviewedIp() {
        return this.interviewedIp.getAddress();
    }

    public void setInterviewedIp(String userIp) {
        this.interviewedIp = new Inet(userIp);
    }
}
