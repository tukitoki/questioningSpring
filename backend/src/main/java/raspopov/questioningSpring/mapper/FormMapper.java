package raspopov.questioningSpring.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import raspopov.questioningSpring.dto.FormDto;
import raspopov.questioningSpring.entity.ChoiceEntity;
import raspopov.questioningSpring.entity.FormEntity;
import raspopov.questioningSpring.entity.QuestionEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FormMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public FormDto toDto(FormEntity formEntity) {
        return modelMapper.map(formEntity, FormDto.class);
    }

    public FormEntity toEntity(FormDto formDto) {
        FormEntity form = modelMapper.map(formDto, FormEntity.class);
        form.setQuestions(new ArrayList<>());
        formDto.getQuestions().forEach(questionDto -> {
            QuestionEntity question = modelMapper.map(questionDto, QuestionEntity.class);
            List<ChoiceEntity> choices = questionDto.getChoices().stream()
                    .map(choice -> modelMapper.map(choice, ChoiceEntity.class))
                    .toList();
            choices.forEach(choiceEntity -> {
                choiceEntity.setQuestion(question);
            });
            question.setChoices(choices);
            question.setForm(form);
            form.getQuestions().add(question);
        });
        return form;
    }

}
