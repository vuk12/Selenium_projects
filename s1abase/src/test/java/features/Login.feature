Feature: Login.feature

Scenario: Homepage displayed after login
Given User is on "qa-auto-ac20" login page
When User logs in with "qtp@config.net" and "password4qa2"
Then Homepage is displayed