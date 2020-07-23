package Steps;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.is;

public class Usuario {
    RequestSpecification requestSpecification = RestAssured.given();

    @Dado("^a requisicao contem (\\d+) \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" (\\d+)$")
    public void a_requisicao_contem(int id, String username, String firstname, String lastname, String email, String password, String phone, int userStatus) throws Throwable {
        requestSpecification.given()
            .contentType("application/json")
            .log().all()
            .body("{" +
                    "  \"id\": " + id + "," +
                    "  \"username\": \"" + username + "\"," +
                    "  \"firstname\": \"" + firstname + "\"," +
                    "  \"lastname\": \"" + lastname + "\"," +
                    "  \"email\": \"" + email + "\"," +
                    "  \"password\": \"" + password + "\"," +
                    "  \"phone\": \"" + phone + "\"," +
                    "  \"userStatus\": " + userStatus + "" +
                "}");
    }

    @Quando("^conecto com a uri da PetShop$")
    public void conecto_com_a_uri_da_PetShop() throws Throwable {
        requestSpecification.when()
            .post("https://petstore.swagger.io/v2/user");
    }

    @Entao("^valido (\\d+) \"([^\"]*)\" \"([^\"]*)\"$")
    public void valido(int code, String type, String message) throws Throwable {
        requestSpecification.then()
            .log().all()
            .statusCode(200)
            .body("code", is(code))
            .body("type", is(type))
            .body("message", is(message));
    }
}