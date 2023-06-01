package se.jfokus.workshop.simulation;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import se.jfokus.workshop.BaseApiConfiguration;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.apache.http.HttpStatus.SC_OK;

public class SimulationsTest extends BaseApiConfiguration {

    @Test
    void shouldRetrieveSimulation() {
        given ()
                .pathParam("id", "66414919004")
        .when()
                .get("/simulations/{id}")
        .then()
                .statusCode(SC_OK)
                .body("name", Matchers.is("Tom"));
    }

    @Test
    void shouldRetrieveAllSimulations() {
        when()
                .get("/simulations/")
        .then()
                .statusCode(SC_OK)
                .body("[0].name", Matchers.is("Tom"))
                .body("[1].name", Matchers.is("John"));
    }

    @Test
    void shouldRetrieveAllSimulations2() {
        when()
                .get("/simulations/")
                .then()
                .statusCode(SC_OK)
                .body(
                        "[0].name", Matchers.is("Unknown"),
                        "[0].installments", Matchers.is(0)
                );
    }

    @Test
    void shouldDeleteExistingSimulation() {
        given()
                .pathParam("cpf", "17822386034")
        .when()
                .delete("/simulations/{cpf}")
        .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    void shouldRetrieveAllSimulationsCheckingSize() {
        when()
                .get("/simulations/")
        .then()
                .statusCode(SC_OK)
                .body("$", Matchers.hasSize(2))
                .body("size()", Matchers.is(2));
    }

}
