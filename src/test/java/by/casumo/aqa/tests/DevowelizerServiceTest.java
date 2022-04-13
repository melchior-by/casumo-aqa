package by.casumo.aqa.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.*;


public class DevowelizerServiceTest {

    //we definitely need expected result "should return the [service_name] without the vowels" service here
    private static final String NPT = ":npt";

    @BeforeClass
    public void setup() {
        String port = System.getProperty("server.port");
        RestAssured.port = port == null ? 8080 : Integer.parseInt(port);

        String baseHost = System.getProperty("server.host");
        if (baseHost == null) {
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;
    }

    @Test(description = "Devowelizer service works reliably")
    public void getInputWorksCorrectly() {
        given()
                .when()
                .get("/:input")
                .then()
                .log().everything()
                .statusCode(HTTP_OK)
                .body(is(NPT));
    }
}
