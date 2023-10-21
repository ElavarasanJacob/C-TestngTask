@changeProfilePic
Feature: Profile Picture Change Module API Automation

  Scenario: Verify to change profile picture in the application through API
    Given User should add headers and bearer authorization and multipart/form-data for accessing change profile picture endpoints
    When User send "POST" request to change profile Picture
    Then User should verify the status code is 200
    And User should verify the response body message for change profile picture as "Profile updated Successfully"
