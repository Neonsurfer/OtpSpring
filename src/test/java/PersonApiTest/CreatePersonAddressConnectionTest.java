package PersonApiTest;

import Util.DbUtil;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import otp.exceptions.NotFoundException;
import otp.exceptions.UnprocessableEntityException;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreatePersonAddressConnectionTest {

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
    public void error_hasTwoAddresses() {
        expectedStatus = HttpStatus.NOT_FOUND;
        givenQueryParams.put("id", 1);
        givenQueryParams.put("addressId", 4);

        Assertions.assertEquals("Ehhez a személyhez már tartozik két cím! \n",
                whenRequest().extract().as(UnprocessableEntityException.class).getMessage());
    }

    @Test
    public void success_createPersonAddressConnection() {
        givenQueryParams.put("id", 2);
        givenQueryParams.put("addressId", 6);

        Assertions.assertEquals(0, DbUtil.getRecordSetFromDb(COUNT_BY_PERSON_ADDRESS_ID
                .append("AND personAddress.person_id = 2 AND personAddress.address_id = 6").toString()).get(1).get("id"));
        whenRequest();
        Assertions.assertEquals(1, DbUtil.getRecordSetFromDb(COUNT_BY_PERSON_ADDRESS_ID
                .append("AND personAddress.person_id = 2 AND personAddress.address_id = 6").toString()).get(1).get("id"));
    }

    private ValidatableResponse whenRequest() {
        return given()
                .queryParams(givenQueryParams)
                .when()
                .post("person/createconn")
                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(expectedStatus.value());
    }

}
