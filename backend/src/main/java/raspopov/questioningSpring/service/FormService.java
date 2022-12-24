package raspopov.questioningSpring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raspopov.questioningSpring.dto.FormDto;
import raspopov.questioningSpring.entity.ChoiceEntity;
import raspopov.questioningSpring.entity.FormEntity;
import raspopov.questioningSpring.entity.QuestionEntity;
import raspopov.questioningSpring.mapper.FormMapper;
import raspopov.questioningSpring.repository.ChoiceRepo;
import raspopov.questioningSpring.repository.FormRepo;
import raspopov.questioningSpring.repository.InterviewedRepo;
import raspopov.questioningSpring.repository.QuestionRepo;

import java.awt.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FormService {

    private final FormRepo formRepo;

    private final FormMapper formMapper;

    private final InterviewedRepo interviewedRepo;

    private final QuestionRepo questionRepo;
    private final ChoiceRepo choiceRepo;

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

    public Page<FormDto> findAllForms(String description, int page, int size) {
        Page<FormEntity> page1;
        if (description == null || description.isBlank()) {
            page1 = formRepo.findAll(PageRequest.of(page, size));
        } else {
            page1 = formRepo.findByDescription(description, PageRequest.of(page, size));
        }
        return page1.map(formMapper::toDto);
    }

    @Transactional
    public void updateForm(Long id, FormDto newFormDto) {
        FormEntity oldForm = formRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such form id"));
        FormEntity newForm = formMapper.toEntity(newFormDto);
        BeanUtils.copyProperties(newForm, oldForm, "questions", "interviewedEntities");
        oldForm.getInterviewedEntities().clear();
        oldForm.getQuestions().clear();
        for (QuestionEntity newQuestion : newForm.getQuestions()) {
            Optional<QuestionEntity> optionalQuestionEntity;
            Long questionId = newQuestion.getQuestionId();
            if (questionId != null && (optionalQuestionEntity = questionRepo.findById(questionId)).isPresent()) {
                QuestionEntity oldQuestion = copyQuestion(newQuestion, optionalQuestionEntity.get());
                oldForm.getQuestions().add(oldQuestion);
            } else {
                oldForm.getQuestions().add(newQuestion);
            }
        }
        formRepo.save(oldForm);
    }

    private QuestionEntity copyQuestion(QuestionEntity from, QuestionEntity to) {
        BeanUtils.copyProperties(from, to, "choices");
        to.getChoices().clear();
        to.getChoices().addAll(from.getChoices());
        return to;
    }

    public FormDto findForm(Long formId) {
        return formMapper.toDto(formRepo.findById(formId)
                .orElseThrow(() -> new NoSuchElementException("No such form id")));
    }
}
