package AddressApiTest;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import otp.entity.Address;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ListAddressTest {
    HttpStatus expectedStatus;

    @BeforeEach
    public void initQueryParamsAndStatus() {
        expectedStatus = HttpStatus.OK;
    }

    @Test
    public void listAddresses() {
        List<Address> addressList = whenRequestSuccessful();
        Assertions.assertFalse(addressList.isEmpty());
    }

    private ValidatableResponse whenRequest() {
        return given()
                .when()
                .get("address/list")
                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(expectedStatus.value());
    }

    private List<Address> whenRequestSuccessful() {
        return whenRequest().extract().body().jsonPath().getList("$", Address.class);
    }
}
