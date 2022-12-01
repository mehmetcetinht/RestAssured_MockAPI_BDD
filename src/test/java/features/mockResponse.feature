@MockResponse @MockAll
Feature: Consider Payloads as a Response

  Scenario Outline: Verify that single item with whole fields filled has right values
    Given prepare a whole test data from payload with "<title>" "<description>" <price>
    When "title" in product 1 is "<title>"
    And "description" in product 1 is "<description>"
    And "price" in product 1 is "<price>"
    Then "brand" in product 1 is "Apple"
    And "isActive" in product 1 is "false"
    And "stock" in product 1 is "1"
    And "rating" in product 1 is "2.19"
    Then verify item count in response is 1
    Examples:
      | title                                     | description  | price |
      | title1                                    | description1 | 1     |
      | Hepsi Home Pasta Tekli Sarkıt Avize Ahşap | Avize        | 110   |
      | Apple iPhone 12 64 GB                     | Apple        | 15000 |


  Scenario: Verify that multiple item with mixed fields has right values
    Given prepare test data with multiple elements from payload
    When "title" in product 1 is "Hepsi Home Pasta Tekli Sarkıt Avize Ahşap"
    And "description" in product 1 is "Avize"
    And "price" in product 1 is "110"
    Then "title" in product 2 is "Apple iPhone 12 64 GB"
    And "description" in product 2 is "Apple"
    And "price" in product 2 is "15000"
    Then verify item count in response is 2
