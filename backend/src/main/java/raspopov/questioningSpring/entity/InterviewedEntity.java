package raspopov.questioningSpring.entity;

import com.vladmihalcea.hibernate.type.basic.Inet;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLInetType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "interviewed")
@TypeDef(
        name = "cidr",
        typeClass = PostgreSQLInetType.class,
        defaultForType = Inet.class
)
public class InterviewedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interviewedId;

    private Timestamp interviewedDate;

    @Column(
            name = "interviewed_ip",
            columnDefinition = "inet"
    )
    private Inet interviewedIp;

    @OneToMany(mappedBy = "interviewed", cascade = CascadeType.MERGE)
    private List<InterviewedChoiceEntity> interviewedChoices;

    public String getInterviewedIp() {
        return this.interviewedIp.getAddress();
    }

    public void setInterviewedIp(String userIp) {
        this.interviewedIp = new Inet(userIp);
    }
}
