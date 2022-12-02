package raspopov.questioningSpring.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import raspopov.questioningSpring.dto.InterviewedDto;
import raspopov.questioningSpring.entity.FormEntity;
import raspopov.questioningSpring.entity.InterviewedEntity;
import raspopov.questioningSpring.repository.ChoiceRepo;
import raspopov.questioningSpring.repository.FormRepo;
import raspopov.questioningSpring.repository.QuestionRepo;


@Component
@RequiredArgsConstructor
public class InterviewedMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    private final QuestionRepo questionRepo;

    private final ChoiceRepo choiceRepo;

    private final FormMapper formMapper;
    public InterviewedDto toDto(InterviewedEntity interviewed) {
        InterviewedDto interviewedDto = modelMapper.map(interviewed, InterviewedDto.class);
        FormEntity form = questionRepo
                .findById(choiceRepo.findById(interviewed.getInterviewedChoices().get(0).getChoice()
                        .getChoiceId()).get().getQuestion()
                        .getQuestionId()).get().getForm();
        interviewedDto.setForm(formMapper.toDto(form));
        return interviewedDto;
    }

    public InterviewedEntity toEntity(InterviewedDto interviewed) {
        InterviewedEntity interviewedEntity =  modelMapper.map(interviewed, InterviewedEntity.class);
        interviewedEntity.getInterviewedChoices().forEach(interviewedChoiceEntity -> {
            interviewedChoiceEntity.setInterviewed(interviewedEntity);
        });
        return interviewedEntity;
    }
}
