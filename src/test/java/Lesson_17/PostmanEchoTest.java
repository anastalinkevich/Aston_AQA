package Lesson_17;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostmanEchoTest {
    @BeforeEach
    void sitUp(){
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    @DisplayName("Метод GET")
    public void givenStatus(){
        given().log().all()
        .when().get("/get?foo1=bar1&foo2=bar2")
        .then().log().body().statusCode(200)
                .assertThat()
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", notNullValue())
                .body("headers.connection", equalTo("close"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", notNullValue())
                //.body("headers.content-type", equalTo("application/json"))
                //.body("headers.user-agent", notNullValue())
                .body("headers.accept", equalTo("*/*"))
                .body("headers.postman-token", not(""))
                //.body("headers.accept-encoding", equalTo("gzip, deflate, br"))
                //.body("headers.cookie", equalTo(null))
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));
                //.statusCode(200);
    }

    @Test
    @DisplayName("Проверка Post Raw Text")
    public void postRawTextTest() {
//        RestAssured.baseURI = "https://postman-echo.com";
        given()
            .baseUri("https://postman-echo.com")
            .contentType("application/json")
            .body("This is expected to be sent back as part of response body.")
        .when()
            .post("/post")
        .then().log().body()
            .body("args", equalTo(Map.of()))
            .body("data", equalTo("This is expected to be sent back as part of response body."))
            .body("files", equalTo(Map.of()))
            .body("form", equalTo(Map.of()))
            .body("headers.host", equalTo("postman-echo.com"))
            .body("headers.x-request-start", notNullValue())
            .body("headers.connection", equalTo("close"))
            .body("headers.content-length", equalTo("58"))
            .body("headers.x-forwarded-proto", equalTo("https"))
            .body("headers.x-forwarded-port", equalTo("443"))
            .body("headers.x-amzn-trace-id", notNullValue())
//          .body("headers.content-type", equalTo("text/plain"))    // проверить
            .body("headers.user-agent", notNullValue())
            .body("headers.accept", equalTo("*/*"))
            //.body("headers.postman-token", equalTo(null))
            //.body("headers.accept-encoding", equalTo("gzip,deflate"))
            //.body("headers.cookie", equalTo(null))
            .body("json", equalTo(null))
            .body("url", equalTo("https://postman-echo.com/post"))
            .statusCode(200);
}

    @Test
    @DisplayName("Проверка Post Form data")
    public void postFromDataTest() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
        .when()
                .post("/post")
        .then().log().body()
                .body("args", equalTo(Map.of()))
                .body("data", equalTo(""))
                .body("files", equalTo(Map.of()))
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", notNullValue())
                .body("headers.connection", equalTo("close"))
                .body("headers.content-length", equalTo("19"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.content-type", equalTo("application/x-www-form-urlencoded; charset=utf-8"))
                .body("headers.user-agent", notNullValue())
                .body("headers.accept", equalTo("*/*"))
                //cache-control
                //postman-token
                //.body("headers.accept-encoding", equalTo("gzip,deflate"))
                //cookie
                .body("json.foo1", equalTo("bar1"))
                .body("json.foo2", equalTo("bar2"))
                .body("url", equalTo("https://postman-echo.com/post"))
                .statusCode(200);
    }
    @Test
    @DisplayName("Проверка put ")
    public void putRequest() {
        given()
                .baseUri("https://postman-echo.com")
                //.contentType(ContentType.JSON)
                .body("This is expected to be sent back as part of response body.")
        .when().post("/put")
        .then().log().body()
                .body("args", equalTo(Map.of()))
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", equalTo(Map.of()))
                .body("form", equalTo(Map.of()))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", notNullValue())
                .body("headers.connection", equalTo("close"))
                .body("headers.content-length", equalTo("58"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.content-type", equalTo("text/plain"))
                .body("headers.user-agent", notNullValue())
                .body("headers.accept", equalTo("*/*"))
                //.body("headers.cache-control",equalTo("no-cache"))
                //.body("headers.postman-token",  notNullValue())
                //.body("headers.accept-encoding", equalTo("gzip,deflate"))
                //.body("headers.cookie", notNullValue())
                .body("json", equalTo(null))
                .body("url", equalTo("https://postman-echo.com/put"))
                .statusCode(200);
    }

    @Test
    @DisplayName("Метод Patch")
    public void patchRequest(){
        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
                .body("This is expected to be sent back as part of response body.")
                .when().post("/put")
                .then().log().body();
    }


    @Test
    @DisplayName("Метод Delete")
    public void deleteRequest(){
        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
                .body("This is expected to be sent back as part of response body.")
                .when().post("/put")
                .then().log().body();
    }
}
