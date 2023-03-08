package PersonApiTest;

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

public class DeletePersonAddressConnectionTest {

    HttpStatus expectedStatus;
    StringBuilder COUNT_BY_PERSON_ADDRESS_ID = new StringBuilder()
            .append("SELECT COUNT(1) AS id\n")
            .append("FROM person_address personAddress \n")
            .append("WHERE 1=1 \n");
    private Map<String, Object> givenQueryParams;

    @BeforeEach
    public void initQueryParamsAndStatus() {
        expectedStatus = HttpStatus.CREATED;
        givenQueryParams = new HashMap<>();
    }

    @Test
    public void error_idNotExists() {
        expectedStatus = HttpStatus.NOT_FOUND;
        givenQueryParams.put("id", -1);
        givenQueryParams.put("addressId", 1);

        Assertions.assertEquals("Ezzel az azonosítóval nem található személy! \n",
                whenRequest().extract().as(NotFoundException.class).getMessage());
    }

    @Test
    public void error_addressIdNotExists() {
        expectedStatus = HttpStatus.NOT_FOUND;
        givenQueryParams.put("id", 1);
        givenQueryParams.put("addressId", -1);

        Assertions.assertEquals("Ezzel az azonosítóval nem található cím! \n",
                whenRequest().extract().as(NotFoundException.class).getMessage());
    }

    @Test
    public void success_deletePersonAddressConnection() {
        givenQueryParams.put("id", 1);
        givenQueryParams.put("addressId", 1);

        Assertions.assertEquals(1, DbUtil.getRecordSetFromDb(COUNT_BY_PERSON_ADDRESS_ID
                .append("AND personAddress.person_id = 1 AND personAddress.address_id = 1").toString()).get(1).get("id"));
        whenRequest();
        Assertions.assertEquals(0, DbUtil.getRecordSetFromDb(COUNT_BY_PERSON_ADDRESS_ID
                .append("AND personAddress.person_id = 1 AND personAddress.address_id = 1").toString()).get(1).get("id"));
    }

    private ValidatableResponse whenRequest() {
        return given()
                .queryParams(givenQueryParams)
                .when()
                .post("person/deleteconn")
                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(expectedStatus.value());
    }
}
