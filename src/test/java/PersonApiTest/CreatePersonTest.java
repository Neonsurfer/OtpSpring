package PersonApiTest;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import otp.entity.Person;
import otp.exceptions.UnprocessableEntityException;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreatePersonTest {

    HttpStatus expectedStatus;
    private Map<String, Object> givenQueryParams;

    @BeforeEach
    public void initQueryParamsAndStatus() {
        expectedStatus = HttpStatus.CREATED;
        givenQueryParams = new HashMap<>();
    }

    @Test
    public void error_idNumberExists() {
        expectedStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        givenQueryParams.put("name", "Kossuth Lajos");
        givenQueryParams.put("idNumber", "123456AB");

        Assertions.assertEquals("Ezzel az személyazonosítóval már létezik személy! \n",
                whenRequest().extract().as(UnprocessableEntityException.class).getMessage());
    }

    @Test
    public void success_createPersonTest() {
        givenQueryParams.put("name", "Kossuth Lajos");
        givenQueryParams.put("idNumber", "193752HU");

        Person thenPerson = whenRequestSuccessful();
        Assertions.assertTrue(thenPerson.getId() > 0);
        Assertions.assertEquals("Kossuth Lajos", thenPerson.getName());
        Assertions.assertEquals("193752HU", thenPerson.getIdNumber());
    }


    private ValidatableResponse whenRequest() {
        return given()
                .queryParams(givenQueryParams)
                .when()
                .post("person/create")
                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(expectedStatus.value());
    }

    private Person whenRequestSuccessful() {
        return whenRequest().extract().body().as(Person.class);
    }
}
