Feature: Export.feature

Scenario: S1A-71: Abstracts titles are exported with title case rules
Given User is on "qa-auto-ac20" login page
When User logs in with "qtp@config.net" and "password4qa2"
And User submit few Abstracts
And User performs Data Export
Then Abstracts titles are exported with title case rules