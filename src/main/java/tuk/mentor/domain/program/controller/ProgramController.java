package tuk.mentor.domain.program.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tuk.mentor.domain.program.dto.request.ProgramRegisterRequest;
import tuk.mentor.domain.program.dto.response.ProgramListResponse;
import tuk.mentor.domain.program.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/program")
@RequiredArgsConstructor
public class ProgramController {
    private final ProgramService programService;

    @PostMapping
    public ResponseEntity<Void> registerProgram(@RequestBody ProgramRegisterRequest programRegisterRequest,
                                                HttpServletRequest httpServletRequest) {
        programService.registerProgram(programRegisterRequest, httpServletRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProgramListResponse>> getProgramList(@RequestParam("keyword") String keyword) {
        List<ProgramListResponse> response = programService.getProgramList(keyword);
        return ResponseEntity.ok().body(response);
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CustomResponse> selectProgramDetail(@PathVariable("id") int program_no) {
//        CustomResponse response = programService.selectProgramDetail(id);
//        return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
//    }
//
//    @PostMapping("/parti")
//    public ResponseEntity<CustomResponse> participateProgramForMentee(@RequestBody ParticipateProgramRequest participateProgramRequest) {
//        CustomResponse response = programService.participateProgramForMentee(participateProgramRequest);
//        return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
//    }

//    @GetMapping("/pdf")
//    public ResponseEntity<CustomResponse> createPdf() throws DocumentException, IOException {
//        PDFCreator creator = new PDFCreator();
//        creator.createPdf();
//        CustomResponse response = new CustomResponse();
//        return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
//    }

}
