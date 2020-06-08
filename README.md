# What is this project about?
This project reproduces an issue with missing mappings if a test context is reused in a test setup with a common base 
test class.

# Project setup
The tests in this project use a `TestRestTemplate` to send HTTP requests. 
A WireMock stub is used to simulate the response.
To "simulate" different test contexts, the test classes are annotated with different active profiles.
There are two different setups. 
In the root package, the test classes contain the entire test setup.
In the subpackage `inheritance`, a base class is used to reduce code duplication / simplify the test setup.

# Observations
If you run all tests in package `com.example.demo`, the tests pass.
If you run all tests in package `com.example.demo.inheritance`, the test `ZTest` fails ("no request mapping was found").

This issue was introduced in version 2.2.3.RELEASE (2.2.2.RELEASE works as expected).

Note: [There is no way to specify the execution order of test classes](https://github.com/junit-team/junit5/issues/1948), 
but the package structure and names happen to suit the purpose of this demo.