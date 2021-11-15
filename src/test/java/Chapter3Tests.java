import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Chapter3Tests {

    public  static Object[][] zipCodesAndPlaces() {
        return new Object[][] {
                {"us", "90210", "Beverly Hills"},
                {"us", "12345", "Schenectady"},
                {"ca", "B2R", "Waverley"},
                {"nl", "1001", "Amsterdam"}
        };
    }

    @ParameterizedTest
    @MethodSource("zipCodesAndPlaces")
    public void requestZipCodesFromCollection_checkPlaceNameInResponseBody_expectSpecifiedPlaceName(String countryCode, String zipCode, String expectedPlaceName) {
        given()
                .pathParam("countryCode", countryCode).pathParam("zipCode", zipCode)

                .when()
                .get("http://zippopotam.us/{countryCode}/{zipCode}")

                .then()
                .assertThat()
                .body("places[0].'place name'", equalTo(expectedPlaceName));
    }

}
