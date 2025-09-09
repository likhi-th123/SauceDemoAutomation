Feature: Checkout functionality on SauceDemo

  Background:
    Given user is logged in with "standard_user" and "secret_sauce"

  @checkout_success
  Scenario: Complete checkout with valid data
    Given user adds "Sauce Labs Backpack" to the cart
    When user enters "John" "Doe" and "560001"
    And clicks continue
    And clicks finish
    Then order success message "Thank you for your order!" should be displayed

  @checkout_summary
  Scenario: Verify order summary before finishing checkout
    Given user adds "Sauce Labs Backpack" to the cart
    When user enters "John" "Doe" and "560001"
    And clicks continue
    Then order summary should display product "Sauce Labs Backpack"
    
  @checkout_price
  Scenario: Verify price summary is displayed during checkout
  	Given user adds "Sauce Labs Backpack" to the cart
  	When user enters "John" "Doe" and "560001"
  	And clicks continue
  	Then price summary should be displayed


