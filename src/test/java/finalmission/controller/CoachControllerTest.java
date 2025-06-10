package finalmission.controller;

import finalmission.domain.Coach;
import finalmission.dto.request.CoachLoginRequest;
import finalmission.repository.CoachRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Optional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Transactional
class CoachControllerTest {

    @Autowired
    private CoachRepository coachRepository;

    @Disabled
    @DisplayName("코치 로그인을 성공하면 토큰을 반환한다.")
    @Test
    void login() {
        // given
        String authId = "1234";
        String password = "1234";
        Coach coach = Coach.builder()
                .authId(authId)
                .password(password)
                .build();
        coachRepository.save(coach);
        CoachLoginRequest coachLoginRequest = new CoachLoginRequest(authId, password);

        // when
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(coachLoginRequest)
                .post("/coaches/login")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().response();

        // then
        System.out.println(Optional.ofNullable(response.getBody().jsonPath().get("token")));
//        assertThat(response.getBody().jsonPath().get("token")).isNotNull();
    }
}
