package raspopov.questioningSpring.entity;

import com.vladmihalcea.hibernate.type.basic.Inet;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLInetType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Form")
@TypeDef(
        name = "cidr",
        typeClass = PostgreSQLInetType.class,
        defaultForType = Inet.class
)
public class FormEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formId;

    @NotBlank(message = "Form description should not be empty")
    private String description;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    @Size(min = 2, max = 8, message = "Question count should be >= 2 and <= 8")
    private List<QuestionEntity> questions;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<InterviewedEntity> interviewedEntities;
}
