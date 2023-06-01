package se.jfokus.workshop;

import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.config.JsonPathConfig;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseApiConfiguration {

    @BeforeAll
    static void mainConfiguration() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api/v1";
        RestAssured.port= 8088;

        RestAssured.config = RestAssuredConfig.newConfig().
                jsonConfig(JsonConfig.jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL));

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();;
    }
}
