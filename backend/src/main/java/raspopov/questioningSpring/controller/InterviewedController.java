package raspopov.questioningSpring.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raspopov.questioningSpring.dto.InterviewedDto;
import raspopov.questioningSpring.service.InterviewedService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping()
public class InterviewedController {

    private final InterviewedService interviewedService;

    @PostMapping("/attempts")
    public ResponseEntity<InterviewedDto> saveAttempt(@RequestBody InterviewedDto interviewed) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(interviewedService.saveAttempt(interviewed));
    }

    @GetMapping("/attempts")
    public ResponseEntity<List<InterviewedDto>> getAttempts() {
        return ResponseEntity.status(HttpStatus.OK).body(interviewedService.getAttempts());
    }

    @GetMapping("/attempts/{attemptId}")
    public ResponseEntity<InterviewedDto> getAttempt(@PathVariable Long attemptId) {
        return ResponseEntity.status(HttpStatus.OK).body(interviewedService.getAttempt(attemptId));
    }
}
