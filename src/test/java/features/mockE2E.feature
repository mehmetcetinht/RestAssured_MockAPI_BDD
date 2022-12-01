@MockAPI @MockAll
Feature: Mock E2E API Suite

  @AddProduct @positive
  Scenario Outline: Add product with all fields
    Given add product payload to "baseUrl" with "<title>" "<description>" <price>
    When user calls "addProductAPI" with "post" http method
    Then the API call is success with status code 201
    And "title" in response body is "<title>"
    And "description" in response body is "<description>"
    And "price" in response body is "<price>"
    And "rating" in response body is "2.19"
    And "isActive" in response body is "false"
    And "basketDiscountPercentage" in response body is "4"
    And "images[0]" in response body is "https://productimages.net/s/4/500/9655454531634.jpg"
    Then verify id created for product: "<title>" using "getProductWithID"

    Examples:
      | title      | description               | price |
      | test title | demo description for test | 100   |
      | iPhone 13  | Smart Phone               | 15000 |

  @PutProduct @positive
  Scenario: Update specific fields with PUT method
    Given update product payload with 4.5 1500 "true" "Samsung"
    When user calls "getProductWithID" with "put" http method
    Then the API call is success with status code 200
    And "rating" in response body is "4.5"
    And "stock" in response body is "1500"
    And "isActive" in response body is "true"
    And "brand" in response body is "Samsung"

  @GetProduct @positive
  Scenario: Verify that products are value of getProducts key
    Given I prepare request spec
    When user calls "getAllProducts" with "get(All)" http method
    Then the API call is success with status code 200
    And "getProducts[0].brand" in response body is "Get All product"

  @DeleteProduct @positive
  Scenario: Delete a product
    Given I prepare request spec
    When user calls "getProductWithID" with "delete" http method
    Then the API call is success with status code 200

  @NegativePayload @negative
  Scenario: Verify that PUT request gets error code 400 with wrong payload
    Given update product payload with 4.5 1500 "stringInsteadOfBoolean" "Samsung"
    When user calls "getProductWithID" with "put" http method
    Then the API call is success with status code 400

  @NegativePath @negative
  Scenario: Verify that GET request gets error code 404 with wrong path
    Given I prepare request spec
    When user calls "getAllProducts" with "get" http method
    Then the API call is success with status code 404

  @NegativeUrl @negative
  Scenario: Verify that POST request gets error code 400 with wrong url
    Given add product payload to "negativeURL" with "title negative" "negative test" 12
    When user calls "addProductAPI" with "post" http method
    Then the API call is success with status code 500