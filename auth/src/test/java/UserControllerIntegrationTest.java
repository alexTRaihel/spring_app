import com.auth.app.controller.AuthController;
import com.auth.app.domain.UserCredentials;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
public class UserControllerIntegrationTest {

    @LocalServerPort
    Integer serverPort = null;

//    @Test
//    public void test() {

//        WebClient webClient = WebClient.builder()
//                .baseUrl("http://localhost:${serverPort}")
//                .build();
//
//        // действие
//        webClient.put()
//                .uri("/user/signup")
//                .bodyValue(UserCredentials("new@example.com", "pw"))
//                .exchange()
//                .block();
//        WebClient loginResponse = webClient.post()
//                .uri("/user/login")
//                .bodyValue(UserCredentials("new@example.com", "pw"))
//                .exchange()
//                .block() ?: throw RuntimeException("Should have gotten a response")
//        val responseCookies = loginResponse.cookies()
//                .map { it.key to it.value.map { cookie -> cookie.value } }
//				.toMap()
//
//        val response = webClient.get()
//                .uri("/user")
//                .cookies { it.addAll(LinkedMultiValueMap(responseCookies)) }
//				.exchange()
//                .block()
//
//        // утверждение
//        assertThat(response?.statusCode()).isEqualTo(HttpStatus.OK)
//        assertThat(response?.bodyToFlux(User::class.java)?.blockFirst()).isEqualTo(User("new@example.com"))
//    }
}
