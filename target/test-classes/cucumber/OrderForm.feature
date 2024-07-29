@tag
Feature: Order Form Functionality

  Background:
  Given Landed on the Dot Foods URL

  @OrderForm
  Scenario Outline: OrderForm Functionality
    Given Logged in with username <name> and password <password>
    When Navigate to Order From page
    When Specify the DotNumber <dotNumber1> and quantity <qty>
    When Add new Row and specify the dotNumber <dotNumber2> and quantity <qty>
    Then Verify the Display of product result
    When Add all products to cart
    And Verify the product in cart page for <dotNumber1>
    And Verify the product in cart page for <dotNumber2>
  
      Examples: 
      | name                           |  password        |  dotNumber1 |  dotNumber2  | qty  |
      | dotexpresswayhelp@dotfoods.com |  Ericisthebest!1 |   646764    |   733108     |  2   |
