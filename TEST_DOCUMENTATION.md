# Test Documentation

## Problem Statement

The login test was failing with the following error:
```
org.opentest4j.AssertionFailedError: Le message doit indiquer une connexion réussie ==> 
Expected :true
Actual   :false
```

### Root Cause

The test was checking the login message immediately after clicking the login button, but the JavaScript `validateLogin()` function in `connexion.html` needs time to execute and set the message content via `innerText`. Without proper waiting, the test was reading an empty message.

## Solution

### 1. Created Proper Test Structure

- Created `src/test/java/com/emsi/Tp2/` directory for JUnit 5 tests
- Added JUnit 5 dependencies to `pom.xml`
- Added Maven Surefire plugin for test execution

### 2. Key Fix: Explicit Wait for JavaScript Execution

In `TestLoginValide.java`, line 84:
```java
// Wait for the message to contain text (JavaScript needs time to execute)
wait.until(ExpectedConditions.textToBePresentInElement(messageElement, "Connexion"));
```

This ensures the test waits for the JavaScript to update the DOM before validating the message content.

### 3. Test Files Created

1. **TestLoginValide.java**
   - Tests valid login (user/password)
   - Tests invalid login
   - Uses WebDriverWait with ExpectedConditions
   - Runs in headless mode for CI/CD

2. **PlanTestRechercheGoogle.java**
   - Google search test plan
   - Demonstrates browser automation for search functionality

3. **RapportTestGenerator.java**
   - Generates HTML test reports
   - Can execute test suites programmatically

## Running Tests

### Run specific test class:
```bash
mvn test -Dtest=TestLoginValide
```

### Run all tests:
```bash
mvn test
```

### Generate test report:
```bash
cd src/test/java
javac -cp .:../../../target/test-classes:~/.m2/repository/... com/emsi/Tp2/RapportTestGenerator.java
java -cp .:../../../target/test-classes:~/.m2/repository/... com.emsi.Tp2.RapportTestGenerator
```

## Expected Test Output

When tests pass successfully:
```
[TC-LOGIN-001] Test connexion valide
   ✓ Identifiants saisis : user / password
   ✓ Message : Connexion réussie !
   ✓ Classe CSS : success
```

## CDP Version Warning

The warning about CDP implementation matching Chrome 143 is informational only and does not affect test functionality:
```
AVERTISSEMENT: Unable to find CDP implementation matching 143
```

This can be resolved by adding a specific CDP dependency matching your Chrome version, but it's optional for basic Selenium functionality.

## Test Configuration

- **Browser**: Chrome (headless mode)
- **Wait Timeout**: 10 seconds
- **Test Framework**: JUnit 5
- **Build Tool**: Maven
- **Selenium Version**: 4.25.0
