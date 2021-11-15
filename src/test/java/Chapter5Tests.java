import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Chapter5Tests {

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {
        given()

                .when()
                .get("http://vee17.mocklab.io/us/90210")

                .then()
                .assertThat()
                .body("response.places.place.placeName", equalTo("Beverly Hills"));
    }
}
