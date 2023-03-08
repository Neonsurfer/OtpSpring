package PersonApiTest;

import Util.DbUtil;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import otp.exceptions.NotFoundException;

import static io.restassured.RestAssured.given;

public class DeletePersonTest {

    HttpStatus expectedStatus;
    StringBuilder COUNT_BY_ID = new StringBuilder()
            .append("SELECT COUNT(1) AS id\n")
            .append("FROM person \n")
            .append("WHERE 1=1 \n");

    @BeforeEach
    public void initQueryParamsAndStatus() {
        expectedStatus = HttpStatus.NO_CONTENT;
    }

    @Test
    public void error_idNotExists() {
        expectedStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        Assertions.assertEquals("Ezzel az azonosítóval nem található személy! \n",
                whenRequest(-1).extract().as(NotFoundException.class).getMessage());
    }

    @Test
    public void success_deletePersonTest() {
        Assertions.assertEquals(1, DbUtil.getRecordSetFromDb(COUNT_BY_ID.append("AND person.id = 1").toString()).get(1).get("id"));
        whenRequest(1);
        Assertions.assertEquals(0, DbUtil.getRecordSetFromDb(COUNT_BY_ID.append("AND person.id = 1").toString()).get(1).get("id"));
    }

    private ValidatableResponse whenRequest(int id) {
        return given()
                .queryParam("id", id)
                .when()
                .post("person/delete")
                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(expectedStatus.value());
    }

}
