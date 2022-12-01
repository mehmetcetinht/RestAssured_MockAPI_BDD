package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class ApiSteps extends Utils {

    RequestSpecification res;
    ResponseSpecification resSpec;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String id;

    //<-----------------------------MockE2E------------------------------>
    @Given("add product payload to {string} with {string} {string} {int}")
    public void add_product_payload_with(String url, String title, String description, int price) throws IOException {

        System.out.println("\nTest data is being created with the provided values!");

        if(url.equalsIgnoreCase("negativeURL")){
        req = null;
        }
        res = given().spec(requestSpecification(url))
                .body(data.addProductAsWhole(title, description, price));

        System.out.println("Test data has been created!");
    }

    @Given("update product payload with {double} {int} {string} {string}")
    public void update_product_payload_with(double rating, int stock, String isActive, String brand) throws IOException {

        System.out.println("\nTest data is being created with the provided values!");


        res = given().spec(requestSpecification("baseUrl"))
                .body(data.updateProduct(rating, stock, isActive, brand));

        System.out.println("Test data has been created!");
    }


    @When("user calls {string} with {string} http method")
    public void user_calls_with_http_method(String resource, String requestMethod) {

        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());

        resSpec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        System.out.println("The user calls " + resource + " to make a " + requestMethod + " request!");
        String resourceWithID = resourceAPI.getResource() + id;

        if (!requestMethod.equalsIgnoreCase("POST"))
            System.out.println("Resource with id: " + resourceWithID);

        if (requestMethod.equalsIgnoreCase("POST"))
            response = res.when().post(resourceAPI.getResource());
        else if (requestMethod.equalsIgnoreCase("GET"))
            response = res.when().get(resourceWithID);
        else if (requestMethod.equalsIgnoreCase("GET(All)"))
            response = res.when().get(resourceAPI.getResource());
        else if (requestMethod.equalsIgnoreCase("DELETE"))
            response = res.when().delete(resourceWithID);
        else if (requestMethod.equalsIgnoreCase("PUT"))
            response = res.when().put(resourceWithID);
    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(int int1) {
        // To use JUnit assertion directly, static library should be added
        // import static org.junit.Assert.*;

        assertEquals(int1, response.getStatusCode());
        System.out.println("the API call is success with status code " + int1);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {

        assertEquals(expectedValue, getJsonPath(response, keyValue));
        System.out.println(keyValue + " in response body is '" + expectedValue + "'");
    }

    @Then("verify id created for product: {string} using {string}")
    public void verify_product_id_created_by_using(String expectedTitle, String resource) throws IOException {

        APIResources resourceAPI = APIResources.valueOf(resource);

        id = getJsonPath(response, "id");
        System.out.println("id: " + id);

        String resourceWithID = resourceAPI.getResource() + id;
        System.out.println("Resource with id: " + resourceWithID);

        response = res.when().get(resourceWithID);

        String actualTitle = getJsonPath(response, "title");

        System.out.println("Expected title: " + expectedTitle + " , Actual title: " + actualTitle);
        assertEquals(expectedTitle, actualTitle);

    }

    @Given("I prepare request spec")
    public void prepare_request_spec() throws IOException {

        res = given().spec(requestSpecification("baseUrl"));

        System.out.println("Request spec without any specific payload is created!");

    }


    //<---------------------------MockResponse--------------------------->
    static JsonPath jsonPath;

    @Given("prepare a whole test data from payload with {string} {string} {int}")
    public void prepare_whole_test_data_with(String title, String description, int price) {

        System.out.println("Test data is being created with the provided values!");

        jsonPath = new JsonPath(TestDataBuild.getOneProduct(title, description, price));

        System.out.println("Mock API response data: " + TestDataBuild.getOneProduct(title, description, price));

    }

    @Given("prepare test data with multiple elements from payload")
    public void prepare_test_data_with_multiple_elements_from_payload() {
        System.out.println("Test data with multiple products is being created!");

        jsonPath = new JsonPath(TestDataBuild.getMultipleProduct());

        System.out.println("Mock API response data: " + TestDataBuild.getMultipleProduct());
    }

    @When("{string} in product {int} is {string}")
    public void in_product_is(String key, int int1, String expectedValue) {

        System.out.println("Verify that " + key + " of product " + int1 + " has the expected value: " + expectedValue);

        String actualValue = (jsonPath.get("getProducts[" + (int1 - 1) + "]." + key)).toString();

        assertEquals(expectedValue, actualValue);

    }

    @Then("verify item count in response is {int}")
    public void verify_item_count_in_response_is(int int1) {

        int productSize = jsonPath.getInt("getProducts.size()");

        System.out.println("Item count is: " + productSize);

        assertEquals(int1, productSize);
    }

}
