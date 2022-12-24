package raspopov.questioningSpring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import raspopov.questioningSpring.dto.*;
import raspopov.questioningSpring.entity.InterviewedChoiceId;
import raspopov.questioningSpring.entity.InterviewedEntity;
import raspopov.questioningSpring.mapper.InterviewedMapper;
import raspopov.questioningSpring.repository.InterviewedChoiceRepo;
import raspopov.questioningSpring.repository.InterviewedRepo;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InterviewedService {

    private final InterviewedMapper interviewedMapper;

    private final InterviewedRepo interviewedRepo;

    private final InterviewedChoiceRepo interviewedChoiceRepo;

    public InterviewedDto saveAttempt(InterviewedDto interviewed, HttpServletRequest request) {
        this.checkQuestionsChoices(interviewed);
        interviewed.setInterviewedIp(this.getUserIp(request));
        InterviewedEntity interviewedEntity = interviewedMapper.toEntity(interviewed);
        interviewedEntity = interviewedRepo.save(interviewedEntity);
        interviewedChoiceRepo.saveAll(interviewedEntity.getInterviewedChoices());
        return interviewedMapper.toDto(interviewedEntity);
    }

    public Page<InterviewedDto> getAttempts(int page, int size) {
        Page<InterviewedEntity> page1 = interviewedRepo.findAll(PageRequest.of(page, size));

        return page1.map(interviewedMapper::toDto);
    }

    public InterviewedDto getAttempt(Long interviewedId) {
        InterviewedEntity interviewedEntity = interviewedRepo.findById(interviewedId)
                .orElseThrow(() -> new NoSuchElementException("No such attempt id"));
        InterviewedDto interviewedDto = interviewedMapper.toDto(interviewedEntity);
        List<InterviewedChoiceDto> interviewedChoiceDtos = new ArrayList<>();
        int submitedChoices;
        FormDto formDto = interviewedDto.getForm();
        for (QuestionDto currQuestion : formDto.getQuestions()) {
            submitedChoices = 0;
            for (ChoiceDto choiceDto : currQuestion.getChoices()) {
                submitedChoices += interviewedChoiceRepo
                        .findPercentageOfChoice(choiceDto.getChoiceId());
            }
            for (ChoiceDto choiceDto : currQuestion.getChoices()) {
                long currChoiceId = choiceDto.getChoiceId();
                float counterOfCurrChoice = (float) interviewedChoiceRepo.findPercentageOfChoice(currChoiceId);
                interviewedChoiceDtos.add(new InterviewedChoiceDto(
                        new InterviewedChoiceId(interviewedId, currChoiceId),
                        counterOfCurrChoice / submitedChoices * 100));
            }
        }
        interviewedDto.setAllInterviewedChoices(interviewedChoiceDtos);
        return interviewedDto;
    }

    private void checkQuestionsChoices(InterviewedDto interviewed) {
        List<String> violations = new ArrayList<>();
        int index = 1;
        for (QuestionDto questionDto : interviewed.getForm().getQuestions()) {
            List<ChoiceDto> choiceDtos = questionDto.getChoices();
            boolean statement = false;
            List<InterviewedChoiceDto> interviewedChoiceDtos = interviewed.getInterviewedChoices();
            for (InterviewedChoiceDto interviewedChoiceDto : interviewedChoiceDtos) {
                for (ChoiceDto choiceDto : choiceDtos) {
                    if (interviewedChoiceDto.getInterviewedChoiceId()
                            .getChoice()
                            .equals(choiceDto.getChoiceId())) {
                        statement = true;
                        break;
                    }
                }
                if (statement) {
                    break;
                }
            }
            if (!statement) {
                violations.add("You should set choice at question №" + index);
            }
            index++;
        }
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException(violations.toString()
                    .replaceAll(",", ",\n"));
        }
    }

    private String getUserIp(HttpServletRequest request) {
        String clientIp = Optional.ofNullable(request.getHeader("X-FORWARDED-FOR"))
                .orElse(request.getRemoteAddr());
        if (clientIp.equals("0:0:0:0:0:0:0:1")) {
            clientIp = "127.0.0.1";
        }
        return clientIp;
    }

}
