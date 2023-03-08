package ContactApiTest;

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

public class ModifyContactTest {

    HttpStatus expectedStatus;
    StringBuilder COUNT_BY_CONTACT_ID = new StringBuilder()
            .append("SELECT COUNT(1) AS id\n")
            .append("FROM contact \n")
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

        Assertions.assertEquals("Ezzel az azonosítóval nem létezik elérhetőség! \n",
                whenRequest().extract().as(NotFoundException.class).getMessage());
    }

    @Test
    public void success() {
        givenQueryParams.put("id", 1);
        givenQueryParams.put("email", "test2@gmail.com");
        givenQueryParams.put("phoneNum", "112-322");
        givenQueryParams.put("faxNum", "061-423-4292");
        givenQueryParams.put("mobileNum", "06201234560");
        givenQueryParams.put("addressId", 7);

        Assertions.assertEquals(0, DbUtil.getRecordSetFromDb(COUNT_BY_CONTACT_ID
                .append("AND contact.address_id = 7").toString()).get(1).get("id"));
        whenRequest();
        Assertions.assertEquals(1, DbUtil.getRecordSetFromDb(COUNT_BY_CONTACT_ID
                .append("AND contact.address_id = 7").toString()).get(1).get("id"));
    }

    private ValidatableResponse whenRequest() {
        return given()
                .queryParams(givenQueryParams)
                .when()
                .post("contact/modify")
                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(expectedStatus.value());
    }
}
