import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredTests {

    private static String requestBody = "Put the Body in the Pet Store API";

    @Before
    public static void setup() {
        RestAssured.baseURI = "https://petstore3.swagger.io/api/v3";
    }

    @Test
    public void addPet() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/pet")
                .then()
                .extract().response();

        Assert.assertEquals(200, response.statusCode());
//        Assert.assertEquals(requestBody.getString("id"), response.jsonPath().getString("id"));
//        Assert.assertEquals(requestBody.getString("name"), response.jsonPath().getString("name"));
//        Assert.assertEquals(requestBody.getString("status"),response.jsonPath().getString("status"));

    }

    @Test
    public void updatePet() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .put("/pet")
                .then()
                .extract().response();

        Assert.assertEquals(200, response.statusCode());
//        Assert.assertEquals("1", response.jsonPath().getString("id"));
//        Assert.assertEquals("Tom", response.jsonPath().getString("name"));
//        Assert.assertEquals("available", response.jsonPath().getString("status"));
    }


    	@Test
	public void findPetByStatus(){
		Response response = given()
			.header("Content-type",
					"application/json")
                	.and()
                	.body(requestBody)
                	.when()
                	.get("/pet/findBystatus/{status}")
                	.then()
                	.extract().response();
		JSONArray pets = response;
  	for (int i = 0; i < pets.length(); i++) {             
        JSONObject pet= pets.getJSONObject(i);  
 	Assert.assertTrue(status.contains(pet.getString("status")));	
             
        }      
		
	}

    @Test
    public void findpetbyID() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .get("/pet/{petID}")
                .then()
                .extract().response();

        Assert.assertEquals(200, response.statusCode());
       // Assert.assertEquals(request.getString("id"), response.jsonPath().getString("id"));
    }
 @Test
    public void updatePetByFormData() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/pet/{petid}")
                .then()
                .extract().response();

        Assert.assertEquals(200, response.getStatusCode());
    }
@Test
    public void deletePet() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .delete("/pet/{petid}")
                .then()
                .extract().response();
    

        Assert.assertEquals(200, response.statusCode());
    }
}