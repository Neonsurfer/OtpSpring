package AddressApiTest;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import otp.entity.Address;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateAddressTest {

    HttpStatus expectedStatus;
    private Map<String, Object> givenQueryParams;

    @BeforeEach
    public void initQueryParamsAndStatus() {
        expectedStatus = HttpStatus.CREATED;
        givenQueryParams = new HashMap<>();
    }

    @Test
    public void success() {
        givenQueryParams.put("city", "Biri");
        givenQueryParams.put("state", "Szabolcs-Szatmar-Bereg");
        givenQueryParams.put("country", "Hungary");
        givenQueryParams.put("addressLine", "4235");
        givenQueryParams.put("isPermanent", 0);


        Address thenAddress = whenRequestSuccessful();
        Assertions.assertTrue(thenAddress.getId() > 0);
        Assertions.assertEquals("Biri", thenAddress.getCity());
    }


    private ValidatableResponse whenRequest() {
        return given()
                .queryParams(givenQueryParams)
                .when()
                .post("address/create")
                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(expectedStatus.value());
    }

    private Address whenRequestSuccessful() {
        return whenRequest().extract().body().as(Address.class);
    }
}
