package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    ApiSteps s = new ApiSteps();

    @Before("@DeleteProduct or @PutProduct or @GetProduct or @NegativePayload")
    public void beforeScenario() throws IOException {


        if (ApiSteps.id == null) {
            s.add_product_payload_with("baseUrl","Balzam", "Latvian Beverage", 13);
            s.user_calls_with_http_method("addProductAPI", "post");
            s.verify_product_id_created_by_using("Balzam", "getProductWithID");
        }
    }

    @Before("@GetProduct")
    public void beforeGetProduct() throws IOException {

        ApiSteps.id = "1";
        s.update_product_payload_with(4.9, 1, "true", "Get All product");
        s.user_calls_with_http_method("getProductWithID","put");
    }
}
