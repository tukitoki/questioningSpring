package raspopov.questioningSpring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raspopov.questioningSpring.dto.ChoiceDto;
import raspopov.questioningSpring.dto.InterviewedChoiceDto;
import raspopov.questioningSpring.dto.InterviewedDto;
import raspopov.questioningSpring.dto.QuestionDto;
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
