import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Chapter4Tests {

    private static RequestSpecification requestSpecification;
    private static ResponseSpecification responseSpecification;

    @BeforeAll
    public static void createRequestAndResponseSpecification() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://api.zippopotam.us")
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void requestUsZipCode90210_expectHttpStatus200_withRequestSpecification() {
        given()
                .spec(requestSpecification)

                .when()
                .get("us/90210")

                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills_withResponseSpecification() {
        given()
                .spec(requestSpecification)

                .when()
                .get("http://zippopotam.us/us/90210")

                .then()
                .spec(responseSpecification)

                .and()
                .assertThat()
                .body("places[0].'place name'", equalTo("Beverly Hills"));
    }

   @Test
   public void requestUsZipCode90210_extractPlaceNameFromResponseBody_assertEqualToBeverlyHills() {
        String placeName =

                given()
                    .spec(requestSpecification)

                    .when()
                    .get("http://zippopotam.us/us/90210")

                    .then()
                    .extract()
                    .path("places[0].'place name'");

        assertEquals(placeName, "Beverly Hills");
   }

}
