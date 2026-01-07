# Harel Travel — UI Automation

A Java Selenium (Maven) project developed for the Harel take-home test. This suite utilizes TestNG to exercise and validate the travel insurance policy flow.

Requirements
- Java 11
- Maven
- Chrome browser (WebDriverManager is integrated for automatic driver management)

Quick start
1. git clone https://github.com/Ido1024/harel-auto/
2. cd harel
3. mvn -DskipTests=true compile
4. mvn test

Running in IntelliJ
- Open the project by selecting the pom.xml file (Import as a Maven project).
- Set Project SDK to Java 11 (File > Project Structure > Project SDK).
- Run tests by right-clicking HarelTravelTest and selecting Run, or use the Maven tool window to execute the test goal.
- To run headless in IntelliJ, add -Dheadless=true to the Run Configuration's VM options.

Headless / CI
Set -Dheadless=true and add a small check in SeleniumUtils.initDriver() to enable --headless=new when the system property headless is true.

Allure Reporting
The project generates detailed execution reports using Allure. To view the report, you must provide the full system path to the results directory.

Command:
allure serve <FULL_SYSTEM_PATH_TO_PROJECT>\target\allure-results

Example:
allure serve C:\Users\Username\IdeaProjects\harel\target\allure-results

Test overview
Main Test Scenario:
1. Navigates to the Harel Travel Policy page.
2. Selects the travel destination and relevant dates.
3. Submits the form and verifies the successful transition to the Travelers Information page.

Contributing
Open issues or submit pull requests for any improvements or bug fixes.

License
No license specified.