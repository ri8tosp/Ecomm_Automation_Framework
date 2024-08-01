# Overview

This project is a comprehensive Selenium automation framework built using Java 8+, TestNG, Cucumber, and Maven. The framework is designed for testing ecomm-web applications and supports BDD (Behavior Driven Development) through Cucumber. It also integrates with Jenkins for CI/CD, utilizes Extent Reports for reporting, and supports data-driven testing using JSON.


## Features


- Java 8+: Core programming language for the framework.
- Selenium: Used for web automation, interacting with web elements, and browser manipulation.
- TestNG: Manages test execution, parallel testing, and reporting.
- Cucumber: Facilitates BDD, allowing you to write tests in a more user / Business friendly language.
- Maven: Handles project dependencies and builds lifecycle management.
- Jenkins: Integrated for Continuous Integration and Continuous Deployment (CI/CD).
- Extent Reports: Provides detailed, interactive HTML reports for test execution.
- JSON for Data-driven Testing: Test data is managed using JSON files, allowing dynamic data injection into test scripts.


## Framework Structure

- src/main/java: Contains the core framework code, page objects, utilities / AbstractComponents.
- src/main/resources: Contains the configuration files.
- src/test/java: Contains test classes, feature files, step definitions and test data files (JSON).


## Setup Instructions

### Prerequisites

- Java 8 or higher installed
- Maven installed
- Jenkins installed and configured
- A web browser (Chrome, Edge, etc.) and corresponding WebDriver installed ( but not compulsory as Selenium 4.6.0, introduced a feature called the WebDriverManager integration, which automatically manages the WebDriver )



## Installation

1. Clone the Repository:
```
  git clone https://github.com/ri8tosp/Ecomm_Automation_Framework.git
```

3. 
