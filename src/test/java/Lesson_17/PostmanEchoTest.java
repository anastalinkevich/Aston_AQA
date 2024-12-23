package Lesson_17;

import io.restassured.http.ContentType;
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
        .then().log().body().statusCode(200)
                .and().body("args.foo1", equalTo("bar1"))
                .and().body("args.foo2", equalTo("bar2"))
                .and().body("headers.host", equalTo("postman-echo.com"))
                .and().body("headers.x-request-start", equalTo("t1734966710.725"))
                .and().body("headers.connection", equalTo("close"))
                .and().body("headers.x-forwarded-proto", equalTo("https"))
                .and().body("headers.x-forwarded-port", equalTo("443"))
                .and().body("headers.x-amzn-trace-id", equalTo("Root=1-67697db6-73caa1c20f2ca2546c410d15"))
                .and().body("headers.user-agent", equalTo("PostmanRuntime/7.43.0"))
                .and().body("headers.accept", equalTo("*/*"))
                .and().body("headers.cache-control", equalTo("no-cache"))
                .and().body("headers.postman-token", notNullValue())
                .and().body("headers.x-request-start", equalTo("postman-token"))
                .and().body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));
    }
}
