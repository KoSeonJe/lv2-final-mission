package finalmission.controller;

import finalmission.dto.request.CoachLoginRequest;
import finalmission.dto.response.CoachLoginResponse;
import finalmission.dto.response.AllCoachResponse;
import finalmission.dto.response.CoachResponse;
import finalmission.service.CoachService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coaches")
@RequiredArgsConstructor
public class CoachController {

    private final CoachService coachService;

    @PostMapping("/login")
    public CoachLoginResponse login(@RequestBody CoachLoginRequest coachLoginRequest) {
        return coachService.login(coachLoginRequest);
    }

    @GetMapping
    public List<AllCoachResponse> getAll() {
        return coachService.getAll();
    }

    @GetMapping("/{id}")
    public CoachResponse getById(@PathVariable("id") Long id) {
        return coachService.getById(id);
    }
}
