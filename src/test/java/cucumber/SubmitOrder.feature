@tag
  Feature: Purchase The Order from Ecommerce website
    I want to use this template for my feature file
    Background:
      Given I landed on Ecommerce Page
  @tag2
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with the username <name> and password <password>
    When  I add product <productname> to  Cart
    And checkout <productname> and submit the order
    Then "Thankyou for the order." is Displayed on Conformation page

    Examples:
      | name                          | password          | productname |
      | chirlasowjanyareddy@gmail.com | Sowjanyareddy@123 | ZARA COAT 3 |
