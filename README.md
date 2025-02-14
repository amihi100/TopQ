# TopQ
OpenProject add task
This automation framework  designed to test OpenProject task management functionality.
## Tech Stack
- Java
- TestNG
- Maven
- Selenium WebDriver
- Extent Reports
- Lombok

### Infra Layer
- **WaitUtils**: Handles driver  actions and waits
- **Configuration**: Manages test configuration properties
- **ExtentManager**: Handles test reporting
- **LogUtils**: Provides logging functionality

### Test Layer
- **BaseTest**: Base class for all test classes with common setup/teardown
- **Page Objects**: Represent web pages (LoginPage, MainPage, MyPage)
- **Test Classes**: Contain actual test cases (LoginTest)
The main test class of running the login and creating task is: com/automation/tests/**OpenProjectTest.java**
The `config.properties` file contains global test parameters.

### Rpeportin
target/extent-reports/

Thanks.
