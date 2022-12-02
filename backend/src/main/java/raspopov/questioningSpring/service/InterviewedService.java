package raspopov.questioningSpring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raspopov.questioningSpring.dto.InterviewedDto;
import raspopov.questioningSpring.entity.InterviewedEntity;
import raspopov.questioningSpring.mapper.InterviewedMapper;
import raspopov.questioningSpring.repository.InterviewedChoiceRepo;
import raspopov.questioningSpring.repository.InterviewedRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InterviewedService {

    private final InterviewedMapper interviewedMapper;

    private final InterviewedRepo interviewedRepo;

    private final InterviewedChoiceRepo interviewedChoiceRepo;

    public InterviewedDto saveAttempt(InterviewedDto interviewed) {
        InterviewedEntity interviewedEntity = interviewedMapper.toEntity(interviewed);
        interviewedEntity = interviewedRepo.save(interviewedEntity);
        interviewedChoiceRepo.saveAll(interviewedEntity.getInterviewedChoices());
        return interviewedMapper.toDto(interviewedEntity);
    }

    public List<InterviewedDto> getAttempts() {
        List<InterviewedDto> interviewedDtos = new ArrayList<>();
        interviewedRepo.findAll().forEach(interviewed -> {
            interviewedDtos.add(interviewedMapper.toDto(interviewed));
        });
        return interviewedDtos;
    }

    public InterviewedDto getAttempt(Long interviewedId) {
        InterviewedEntity interviewedEntity = interviewedRepo.findById(interviewedId)
                .orElseThrow(() -> new NoSuchElementException("No such attempt id"));
        return interviewedMapper.toDto(interviewedEntity);
    }
}
