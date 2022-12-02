package raspopov.questioningSpring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raspopov.questioningSpring.dto.FormDto;
import raspopov.questioningSpring.entity.FormEntity;
import raspopov.questioningSpring.entity.InterviewedEntity;
import raspopov.questioningSpring.mapper.FormMapper;
import raspopov.questioningSpring.repository.FormRepo;
import raspopov.questioningSpring.repository.InterviewedRepo;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FormService {

    private final FormRepo formRepo;

    private final FormMapper formMapper;

    private final InterviewedRepo interviewedRepo;

    public FormDto createForm(FormDto formDto) {
        FormEntity form = formMapper.toEntity(formDto);
        form = formRepo.save(form);
        return formMapper.toDto(form);
    }

    public void deleteForm(Long formId) {
        FormEntity form = formRepo.findById(formId)
                .orElseThrow(() -> new NoSuchElementException("No such form id"));
        formRepo.delete(form);
        interviewedRepo.findAll().forEach(interviewed -> {
            if (interviewed.getInterviewedChoices().size() == 0) {
                interviewedRepo.delete(interviewed);
            }
        });
    }

    public List<FormDto> findAllForms() {
        List<FormDto> list = new ArrayList<>();
        formRepo.findAll().forEach(form -> {
            list.add(formMapper.toDto(form));
        });
        return list;
    }

    public void updateForm(Long id, FormDto newFormDto) {
        FormEntity oldForm = formRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such form id"));
        FormEntity newForm = formMapper.toEntity(newFormDto);
        Set<InterviewedEntity> interviewedEntitySet = new HashSet<>();
        oldForm.getQuestions().forEach(questionEntity -> {
            questionEntity.getChoices().forEach(choiceEntity -> {
                choiceEntity.getInterviewedChoices().forEach(interviewedChoiceEntity -> {
                    interviewedEntitySet.add(interviewedChoiceEntity.getInterviewed());
                });
            });
        });
        interviewedRepo.deleteAll(interviewedEntitySet);
        oldForm.setQuestions(newForm.getQuestions());
        oldForm.setDescription(newForm.getDescription());
        formRepo.save(oldForm);
    }

    public FormDto findForm(Long formId) {
        return formMapper.toDto(formRepo.findById(formId)
                .orElseThrow(() -> new NoSuchElementException("No such form id")));
    }
}
