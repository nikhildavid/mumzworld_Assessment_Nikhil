# Automation Testing Framework

## Overview
This automation testing framework is designed to streamline the testing process for web applications, providing a modular and scalable approach to Quality Assurance (QA). It utilizes Selenium WebDriver for automation and Extent Reports for reporting. The framework uses TestNG as the testing framework to organize and execute the test cases effectively.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Report Generation](#report-generation)

## Prerequisites
Before running the framework, ensure that you have the following installed on your machine:
- Java JDK (version 8 or higher)
- Maven (version 3.6.0 or higher)
- IDE (e.g., Eclipse, IntelliJ IDEA)
- WebDriver for the browser you wish to test (e.g., ChromeDriver, GeckoDriver)
- TestNG dependency in your `pom.xml`

## Installation
1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd <repository-directory>

2.Install dependencies: Navigate to the project directory and run
   mvn install

## Project Structure

├── src
│   ├── main
│   │   └── java
│   │       ├── 
│   │           ├── report
│   │           ├── pages
│   │           └── utils
│   │           
│   └── test
│       └── java
│       │      └── tests
│       │      ├── BaseTest.java
│       │      └── TestCases
│       └── resources   
│              ├── Test Data 
│              └── config.properties
│              
├── pom.xml     
└── testing.xml 

## Configuration

Configure your test settings in src/main/resources/config.properties

Eg: 
baseUrl=https://example.com
browser=chrome

## Running Tests

mvn test

You can run a specific test class using:
mvn -Dtest=YourTestClassName test

## Running Tests in IDE

If you are using an IDE like Eclipse or IntelliJ IDEA, you can right-click on your test class or suite and select "Run as TestNG Test.

## Report Generation

After running the tests, Extent Reports will generate an HTML report. You can find the report in the reports directory.

