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
        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
        .when()
                .get("/get?foo1=bar1&foo2=bar2")
        .then().log().body()
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", notNullValue())
                .body("headers.connection", equalTo("close"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.content-type", equalTo("application/json"))
                .body("headers.user-agent", notNullValue())
                .body("headers.accept", equalTo("*/*"))
                .body("headers.postman-token", equalTo(null))
                .body("headers.accept-encoding", equalTo("gzip, deflate, br"))
                //.body("headers.cookie", equalTo(null))
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"))
                .statusCode(200);
    }
    @Test
    @DisplayName("Метод POST")
    public void iolju(){
        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
        .when()
                .post("/post?орпа=имть")
        .then().log().body()
                .body("ars.орпа", equalTo("имть"))
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", equalTo("{}"))
                .body("form", equalTo("{}"))
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", notNullValue())
                .body("headers.connection", equalTo("close"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.content-type", equalTo("application/json"))
                .body("headers.user-agent", notNullValue())
                .body("headers.accept", equalTo("*/*"))
                .body("headers.postman-token", equalTo(null))
                .body("headers.accept-encoding", equalTo("gzip, deflate, br"))
                .body("headers.cookie", equalTo(null))
                .body("json", equalTo("null"))
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));
    }
    
}
