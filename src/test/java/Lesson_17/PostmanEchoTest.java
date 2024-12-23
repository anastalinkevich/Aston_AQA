package Lesson_17;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PostmanEchoTest {

    @Test
    @DisplayName("Метод GET")
    public void givenStatus(){
        given().log().all()
                .when().get("https://postman-echo.com/get?foo1=bar1&foo2=bar2")
                .then().log().body()
                .statusCode(200)
                .and().body("args.foo1", Matchers.is("bar1"))
                .and().body("args.foo2", Matchers.is("bar2"))
                .and().body("headers.host", Matchers.is("postman-echo.com"))
                .and().body("headers.x-request-start", notNullValue())
                .and().body("headers.connection", Matchers.is("close"))
                .and().body("headers.x-forwarded-proto", Matchers.is("https"))
                .and().body("headers.x-forwarded-port", Matchers.is("443"))
                .and().body("headers.x-amzn-trace-id", notNullValue())
                .and().body("headers.user-agent", Matchers.is("PostmanRuntime/7.43.0"))
                .and().body("headers.accept", Matchers.is("*/*"))
                .and().body("headers.cache-control", Matchers.is("no-cache"))
                .and().body("headers.postman-token", notNullValue())
                .and().body("headers.accept-encoding", Matchers.is("gzip, deflate, br"))
                .and().body("headers.cookie", notNullValue() )
                .and().body("url", Matchers.is("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));
    }
}
