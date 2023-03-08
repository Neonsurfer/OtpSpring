package PersonApiTest;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import otp.entity.Person;

import java.util.List;

import static io.restassured.RestAssured.given;


public class ListPersonTest {
    HttpStatus expectedStatus;

    @BeforeEach
    public void initQueryParamsAndStatus() {
        expectedStatus = HttpStatus.OK;
    }

    @Test
    public void listPersonsTest() {
        expectedStatus = HttpStatus.OK;

        List<Person> personList = whenRequestSuccessful();
        Assertions.assertFalse(personList.isEmpty());
    }

    private ValidatableResponse whenRequest() {
        return given()
                .when()
                .get("person/list")
                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(expectedStatus.value());
    }

    private List<Person> whenRequestSuccessful() {
        return whenRequest().extract().body().jsonPath().getList("$", Person.class);
    }

}
