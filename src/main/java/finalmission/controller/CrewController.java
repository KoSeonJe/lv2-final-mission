package finalmission.controller;

import finalmission.dto.request.CrewLoginRequest;
import finalmission.dto.request.CrewSignUpRequest;
import finalmission.dto.response.CrewLoginResponse;
import finalmission.service.CrewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/crews")
public class CrewController {

    private final CrewService crewService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public CrewLoginResponse login(@RequestBody CrewLoginRequest crewLoginRequest) {
        return crewService.login(crewLoginRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public void signUp(@RequestBody CrewSignUpRequest crewSignUpRequest) {
        crewService.signUp(crewSignUpRequest);
    }
}
