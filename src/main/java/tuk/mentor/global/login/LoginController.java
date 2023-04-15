package tuk.mentor.global.login;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

//    @PostMapping
//    public ResponseEntity<LoginResponse> login(HttpServletRequest httpServletRequest, @RequestBody LoginRequest loginRequest) {
//        LoginResponse response = loginService.login(loginRequest, httpServletRequest);
//        return ResponseEntity.ok().body(response);
//    }
}
