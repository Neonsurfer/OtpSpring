package ContactApiTest;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import otp.entity.Contact;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateContactTest {

    HttpStatus expectedStatus;
    private Map<String, Object> givenQueryParams;

    @BeforeEach
    public void initQueryParamsAndStatus() {
        expectedStatus = HttpStatus.CREATED;
        givenQueryParams = new HashMap<>();
    }

    @Test
    public void success() {
        givenQueryParams.put("email", "test2@gmail.com");
        givenQueryParams.put("phoneNum", "111-222");
        givenQueryParams.put("faxNum", "061-423-4298");
        givenQueryParams.put("mobileNum", "06201234567");
        givenQueryParams.put("addressId", 3);

        Contact thenContact = whenRequestSuccessful();
        Assertions.assertTrue(thenContact.getId() > 0);
        Assertions.assertEquals("test2@gmail.com", thenContact.getEmail());
        Assertions.assertEquals(3, thenContact.getAddressId());
    }


    private ValidatableResponse whenRequest() {
        return given()
                .queryParams(givenQueryParams)
                .when()
                .post("contact/create")
                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(expectedStatus.value());
    }

    private Contact whenRequestSuccessful() {
        return whenRequest().extract().body().as(Contact.class);
    }
}
