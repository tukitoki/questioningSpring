package raspopov.questioningSpring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raspopov.questioningSpring.dto.FormDto;
import raspopov.questioningSpring.entity.FormEntity;
import raspopov.questioningSpring.service.FormService;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping()
public class FormController {

    private final FormService formService;

    @PostMapping("/forms")
    public ResponseEntity<FormDto> createQuestioning(@RequestBody @Valid FormDto formDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(formService.createForm(formDto));
    }

    @DeleteMapping("/forms/{formId}")
    public ResponseEntity<Void> deleteForm(@PathVariable Long formId) {
        formService.deleteForm(formId);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PutMapping("/forms/{formId}")
    public ResponseEntity<Void> updateForm(@PathVariable Long formId,
                                                 @RequestBody @Valid FormDto formDto) {
        formService.updateForm(formId, formDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/forms")
    public ResponseEntity<Page<FormDto>> getAllForms(String description, int page, int size) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(formService.findAllForms(description, page, size));
    }

    @GetMapping("/forms/{formId}")
    public ResponseEntity<FormDto> getForm(@PathVariable Long formId) {
        return ResponseEntity.status(HttpStatus.OK).body(formService.findForm(formId));
    }
}
