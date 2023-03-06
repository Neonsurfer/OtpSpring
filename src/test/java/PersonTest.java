import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;


public class PersonTest {

    WebTestClient client;

    @BeforeEach
    public void setUpWebTestClient() {
        client = WebTestClient.bindToServer()
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Test
    public void listEmployeesTest() {
        client.get()
                .uri("/")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(String.class);
    }

}
