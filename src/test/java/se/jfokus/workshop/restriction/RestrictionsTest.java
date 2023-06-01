package se.jfokus.workshop.restriction;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.jfokus.workshop.BaseApiConfiguration;

import static io.restassured.RestAssured.given;

class RestrictionsTest extends BaseApiConfiguration {

    @Test
    @DisplayName("Should query CPF without restriction")
    void shouldQueryCpfWithoutRestriction() {
        given()
                .pathParam("cpf", "97093236014")
                .when()
                .get("/restrictions/{cpf}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    void shouldReturnRestriction() {
            given()
                .pathParam("cpf", "84809766080")
            .when()
                .get("/restrictions/{cpf}")
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("message", Matchers.is("CPF 84809766080 has a restriction"));
    }
}
