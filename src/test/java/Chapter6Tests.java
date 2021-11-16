import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Chapter6Tests {

    @Test
    public void requestUsZipCode90210_checkPlaceInResponseBody_expectBeverlyHills() {
        Location location =
                given()

                        .when()
                        .get("http://api.zippopotam.us/us/90210")
                        .as(Location.class);

        assertEquals("Beverly Hills", location.getPlaces().get(0).getPlaceName());
    }

    @Test
    public void sendSvqZipCode41008_checkStatusCode_expect200() {
        Location location = new Location();
        location.setCountry("France");

        given()
                .contentType(ContentType.JSON)
                .body(location)
                .log().body()

                .when()
                .post("http://vee17.mocklab.io/svq/41008")

                .then()
                .assertThat()
                .statusCode(200);
    }
}
