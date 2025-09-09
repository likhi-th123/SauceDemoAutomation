Feature: SauceDemo Product Page

  Scenario: Verify product page title
    Given user is logged in with "standard_user" and "secret_sauce"
    Then the product page title should be "Products"

  Scenario: Verify total number of products displayed
    Given user is logged in with "standard_user" and "secret_sauce"
    Then the product page should display "6" products
