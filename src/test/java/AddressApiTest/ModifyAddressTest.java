package AddressApiTest;

import Util.DbUtil;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import otp.exceptions.NotFoundException;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ModifyAddressTest {

    HttpStatus expectedStatus;
    StringBuilder COUNT_BY_ADDRESS_ID = new StringBuilder()
            .append("SELECT COUNT(1) AS id\n")
            .append("FROM address \n")
            .append("WHERE 1=1 \n");
    private Map<String, Object> givenQueryParams;

    @BeforeEach
    public void initQueryParamsAndStatus() {
        expectedStatus = HttpStatus.OK;
        givenQueryParams = new HashMap<>();
    }

    @Test
    public void error_idNotExists() {
        expectedStatus = HttpStatus.NOT_FOUND;

        Assertions.assertEquals("Ezzel az azonosítóval nem létezik cím! \n",
                whenRequest().extract().as(NotFoundException.class).getMessage());
    }

    @Test
    public void success() {
        givenQueryParams.put("id", 1);
        givenQueryParams.put("city", "Biri");
        givenQueryParams.put("state", "Szabolcs-Szatmar-Bereg");
        givenQueryParams.put("country", "Hungary");
        givenQueryParams.put("addressLine", "4235");
        givenQueryParams.put("isPermanent", 0);

        Assertions.assertEquals(0, DbUtil.getRecordSetFromDb(COUNT_BY_ADDRESS_ID
                .append("AND address.id = 1 AND address.city LIKE 'BIRI'").toString()).get(1).get("id"));
        whenRequest();
        Assertions.assertEquals(1, DbUtil.getRecordSetFromDb(COUNT_BY_ADDRESS_ID
                .append("AND address.id = 1 AND address.city LIKE 'BIRI'").toString()).get(1).get("id"));
    }

    private ValidatableResponse whenRequest() {
        return given()
                .queryParams(givenQueryParams)
                .when()
                .post("address/modify")
                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(expectedStatus.value());
    }
}
