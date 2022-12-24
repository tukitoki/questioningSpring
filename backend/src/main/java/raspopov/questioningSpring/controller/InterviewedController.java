package raspopov.questioningSpring.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raspopov.questioningSpring.dto.InterviewedDto;
import raspopov.questioningSpring.service.InterviewedService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping()
public class InterviewedController {

    private final InterviewedService interviewedService;

    @PostMapping("/attempts")
    public ResponseEntity<InterviewedDto> saveAttempt(@RequestBody InterviewedDto interviewed,
                                                      HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(interviewedService.saveAttempt(interviewed, request));
    }

    @GetMapping("/attempts")
    public ResponseEntity<Page<InterviewedDto>> getAttempts(int page, int size) {
        return ResponseEntity.status(HttpStatus.OK).body(interviewedService.getAttempts(page, size ));
    }

    @GetMapping("/attempts/{attemptId}")
    public ResponseEntity<InterviewedDto> getAttempt(@PathVariable Long attemptId) {
        return ResponseEntity.status(HttpStatus.OK).body(interviewedService.getAttempt(attemptId));
    }
}
