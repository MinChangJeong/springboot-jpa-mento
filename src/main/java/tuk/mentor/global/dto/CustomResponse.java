package tuk.mentor.global.dto;

import lombok.Data;

@Data
public class CustomResponse {
    // HttpStatus
    private String status;
    // Http Default Message
    private String message;
}
