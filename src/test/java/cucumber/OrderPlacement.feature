@tag
Feature: Order Placement functionality of DotFoods application

  Background:
  Given Landed on the Dot Foods URL

  @OrderPlacement
  Scenario Outline: Positive test of placing the order
    Given Logged in with username <name> and password <password>
    When Navigate to PLP Page
    When Search for the product <dotNum>
    Then Verify the search result for <dotNum>
    When AddProduct to Cart
    When Navigate to Cart Page
    Then Verify the product in cart for <dotNum>
    When Checkout to checkoutPage
    Then PlaceOrder for the Product
    And Verify the orderPlacement Message <message>

    Examples: 
      | name                           |  password        |  dotNum | message      |
      | dotexpresswayhelp@dotfoods.com |  Ericisthebest!1 |  555983 | Order Placed |