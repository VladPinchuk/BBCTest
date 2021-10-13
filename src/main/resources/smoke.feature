Feature: Smoke
  As a user
  I want to write tests checking the main site functionality
  So that I can be shure that site works correctly
  # Task variant: BBC1
  # Author: Vladyslav Pinchuk

  Scenario: Check the correct name of headline article
    Given User opens 'https://www.bbc.com/' page
    When User clicks 'News' button
    Then User checks that name of the headline article is 'Al-Qaeda could threaten US in a year - general'


  Scenario Outline: Check that specified list of article titles is present on the page
    Given User opens 'https://www.bbc.com/' page
    When User clicks 'News' button
    Then User checks that News page contains articles with the following '<title>'

    Examples:
      | title                                            |
      | Facebook whistleblower reveals identity          |
      | Muhammad cartoonist dies in car crash - reports  |
      | North and South Korea talk on phone after months |
      | A bitter battle rages in Florida over vaccines   |

  Scenario: Check that the name of first article corresponds to search word
    Given User opens 'https://www.bbc.com/' page
    When User clicks 'News' button
    And User enters in the Search bar the stored text of the Category link of the headline article
    Then User checks that name of the first article is "One-minute World News"

  Scenario Outline: Check that user can submit a question to BBC
    Given User opens 'https://www.bbc.com/' page
    When User clicks 'News' button
    And User clicks 'Coronavirus' button
    And User clicks 'Your Coronavirus Stories' button
    And User clicks 'What questions do you have' button
    And User fills the form entering '<question text>' '<name>' '<email>' and selecting '<accept checkbox>'
    And User clicks 'Submit' button
    Then User checks that 'Error message' appears
    Examples:
      | question text               | name | email          | accept checkbox |
      | whats new about coronavirus |      | vlad@gmail.com | select          |
      | whats new about coronavirus | Vlad |                | select          |
      | whats new about coronavirus | Vlad | vlad@gmail.com |                 |
      |                             | Vlad | vlad@gmail.com | select          |