import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.hamcrest.Matchers.is;

import static io.restassured.RestAssured.given;

public class Servico {

    public String lerJson (String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test
    public void tc_01_incluir_usuario() throws IOException {
        String resultado = "119890418";
        String jsonBody = lerJson("src/test/resources/usuario.json");
        given()
            .contentType("application/json")
            .log().all()
            .body(jsonBody)
            /*
            .body("{\n" +
                "  \"id\": 1,\n" +
                "  \"username\": \"AnaClara\",\n" +
                "  \"firstname\": \"Ana\",\n" +
                "  \"lastname\": \"Clara\",\n" +
                "  \"email\": \"anaclara@test.com\",\n" +
                "  \"password\": \"123456\",\n" +
                "  \"phone\": \"11999990001\",\n" +
                "  \"userStatus\": 1\n" +
                "}")
            */
        .when()
            .post("https://petstore.swagger.io/v2/user")
        .then()
            .log().all()
            .statusCode(200)
            .body("code", is(200))
            .body("type", is("unknown"))
            .body("message", is(resultado))
        ;
    }

    @Test
    public void tc_02_consultar_usuario() throws IOException {
        given()
            .contentType("application/json")
            .log().all()
        .when()
            .get("https://petstore.swagger.io/v2/user/AnaClaraTG")
        .then()
            .log().all()
            .statusCode(200)
        ;
    }

    @Test
    public void tc_03_alterar_usuario() throws IOException {
        String resultado = "119890418";
        String jsonBody = lerJson("src/test/resources/usuario2.json");
        given()
            .contentType("application/json")
            .log().all()
            .body(jsonBody)
        .when()
            .put("https://petstore.swagger.io/v2/user/AnaClaraTG")
        .then()
            .log().all()
            .statusCode(200)
            .body("code", is(200))
            .body("type", is("unknown"))
            .body("message", is(resultado))
        ;
    }

    @Test
    public void tc_04_consultar_alteracao_usuario() throws IOException {
        given()
            .contentType("application/json")
            .log().all()
        .when()
            .get("https://petstore.swagger.io/v2/user/AnaClaraTG")
        .then()
            .log().all()
            .statusCode(200)
        ;
    }

    @Test
    public void tc_05_excluir_usuario() throws IOException {
        String resultado = "AnaClaraTG";
        given()
            .contentType("application/json")
            .log().all()
        .when()
            .delete("https://petstore.swagger.io/v2/user/AnaClaraTG")
        .then()
            .log().all()
            .statusCode(200)
            .body("code", is(200))
            .body("type", is("unknown"))
            .body("message", is(resultado))
        ;
    }

    @Test
    public void tc_06_consultar_exclusao_usuario() throws IOException {
        given()
            .contentType("application/json")
            .log().all()
        .when()
            .get("https://petstore.swagger.io/v2/user/AnaClaraTG")
        .then()
            .log().all()
            .statusCode(404)
        ;
    }
}