import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Chapter2Tests {

    @Test
    public void requestUsZipCode90210_checkStatusCode_expectHttp200() {
        given()
        .when()
                .get("http://zippopotam.us/us/90210")
        .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void requestUsZipCode90210_checkContentType_expectApplicationJson() {
        given()
        .when()
                .get("http://zippopotam.us/us/90210")
        .then()
                .assertThat()
                .contentType(ContentType.JSON);
    }

    @Test
    public void requestUsZipCode90210_logRequestAndResponseDetails() {
        given()
                .log().all()

                .when()
                .get("http://zippopotam.us/us/90210")

                .then()
                .log().body();
    }

    @Test
    public void requestUsZipCode90210_checkStateNameInResponseBody_expectCalifornia() {
        given()

                .when()
                .get("http://zippopotam.us/us/90210")

                .then()
                .assertThat()
                .body("places[0].state", equalTo("California"));
    }

    @Test
    public void requestUsZipCode90210_checkListOfPlaceNamesInResponseBody_expectContainsBeverlyHills() {
        given()
                .when()
                .get("http://zippopotam.us/us/90210")

                .then()
                .assertThat()
                .body("places.'place name'", hasItem("Beverly Hills")); // to assert that a collection does NOT have an item, we just write not(hasItem("<name of the item>"))
    }

    @Test
    public void requestUsZipCode90210_checkNumberOfPlaceNamesInResponseBody_expectOne() {
        given()
                .when()
                .get("http://zippopotam.us/us/90210")

                .then()
                .assertThat()
                .body("places.'place name'", hasSize(1));
    }

}
