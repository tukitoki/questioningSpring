package raspopov.questioningSpring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import raspopov.questioningSpring.dto.FormDto;
import raspopov.questioningSpring.entity.FormEntity;
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

    public Page<FormDto> findAllForms(String description, int page, int size) {
        Page<FormEntity> page1;
        if (description == null || description.isBlank()) {
            page1 = formRepo.findAll(PageRequest.of(page, size));
        } else {
            page1 = formRepo.findByDescription(description, PageRequest.of(page, size));
        }
        return page1.map(formMapper::toDto);
    }

    public void updateForm(Long id, FormDto newFormDto) {
        FormEntity oldForm = formRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such form id"));
        FormEntity newForm = formMapper.toEntity(newFormDto);
        interviewedRepo.deleteAll(oldForm.getInterviewedEntities());
        oldForm.setQuestions(newForm.getQuestions());
        oldForm.setDescription(newForm.getDescription());
        formRepo.save(oldForm);
    }

    public FormDto findForm(Long formId) {
        return formMapper.toDto(formRepo.findById(formId)
                .orElseThrow(() -> new NoSuchElementException("No such form id")));
    }
}
