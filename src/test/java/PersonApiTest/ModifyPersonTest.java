package PersonApiTest;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import otp.entity.Person;
import otp.exceptions.NotFoundException;
import otp.exceptions.UnprocessableEntityException;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ModifyPersonTest {

    HttpStatus expectedStatus;
    private Map<String, Object> givenQueryParams;

    @BeforeEach
    public void initQueryParamsAndStatus() {
        expectedStatus = HttpStatus.OK;
        givenQueryParams = new HashMap<>();
    }

    @Test
    public void error_idNotExists() {
        expectedStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        givenQueryParams.put("id", -1);
        givenQueryParams.put("name", "Kossuth Lajos");
        givenQueryParams.put("idNumber", "193752HU");

        Assertions.assertEquals("Ezzel az azonosítóval nem található személy! \n",
                whenRequest().extract().as(NotFoundException.class).getMessage());
    }

    @Test
    public void error_idNumberExists() {
        expectedStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        givenQueryParams.put("id", 3);
        givenQueryParams.put("name", "Kossuth Lajos");
        givenQueryParams.put("idNumber", "123456AB");

        Assertions.assertEquals("Ezzel az személyazonosítóval már létezik személy! \n",
                whenRequest().extract().as(UnprocessableEntityException.class).getMessage());
    }

    @Test
    public void success_modifyPerson() {
        givenQueryParams.put("id", 3);
        givenQueryParams.put("name", "Kossuth Lajos");
        givenQueryParams.put("idNumber", "193752HU");
        Person givenPerson = new Person(3, "Kossuth Lajos", "193752HU");

        Person thenPerson = whenRequestSuccessful();
        Assertions.assertEquals(thenPerson, givenPerson);
    }

    private ValidatableResponse whenRequest() {
        return given()
                .queryParams(givenQueryParams)
                .when()
                .post("person/modify")
                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(expectedStatus.value());
    }

    private Person whenRequestSuccessful() {
        return whenRequest().extract().body().as(Person.class);
    }
}
