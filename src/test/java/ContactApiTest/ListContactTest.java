package ContactApiTest;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import otp.entity.Contact;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ListContactTest {

    HttpStatus expectedStatus;

    @BeforeEach
    public void initQueryParamsAndStatus() {
        expectedStatus = HttpStatus.OK;
    }

    @Test
    public void listContacts() {
        List<Contact> personList = whenRequestSuccessful();
        Assertions.assertFalse(personList.isEmpty());
    }

    private ValidatableResponse whenRequest() {
        return given()
                .when()
                .get("contact/list")
                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(expectedStatus.value());
    }

    private List<Contact> whenRequestSuccessful() {
        return whenRequest().extract().body().jsonPath().getList("$", Contact.class);
    }
}
