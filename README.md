# 🛍️ AliExpress Test Automation

<div align="center">

[![Java](https://img.shields.io/badge/Java-21-orange.svg?style=for-the-badge&logo=java)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.16.1-green.svg?style=for-the-badge&logo=selenium)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.8.0-orange.svg?style=for-the-badge&logo=testng)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.11.0-red.svg?style=for-the-badge&logo=apache-maven)](https://maven.apache.org/)
[![Allure](https://img.shields.io/badge/Allure-2.24.0-yellow.svg?style=for-the-badge&logo=qameta)](http://allure.qatools.ru/)
[![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-CI/CD-blue.svg?style=for-the-badge&logo=github-actions)](https://github.com/features/actions)
[![Log4j2](https://img.shields.io/badge/Log4j2-2.22.0-blue.svg?style=for-the-badge&logo=apache)](https://logging.apache.org/log4j/2.x/)

  <img src="https://www.selenium.dev/images/selenium_logo_square_green.png" alt="Selenium Logo" width="100"/>

</div>

> Modern web test automation framework for AliExpress using Selenium WebDriver, TestNG, and Allure Reports

## ✨ Features

- Page Object Model (POM) design pattern
- TestNG test framework with parallel execution support
- Selenium WebDriver 4 with modern features
- GitHub Actions for CI/CD with matrix testing
- Allure Reports for detailed test reporting and analytics
- Branch-based reporting with video recordings
- Log4j2 for comprehensive logging
- Chrome in headless mode for CI/CD

## 🔧 Prerequisites

- Java JDK 21 or higher
- Maven 3.11.0 or higher
- Chrome browser (latest version)
- Git

## 🚀 Getting Started

1. Clone the repository:
```bash
git clone https://github.com/hakantetik44/ProjectGithubActions.git
```

2. Navigate to project directory:
```bash
cd ProjectGithubActions
```

3. Run tests:
```bash
mvn clean test
```

## 🏗️ Project Structure

```
src
├── test
│   ├── java
│   │   ├── pages          # Page Object classes
│   │   │   └── AliExpressHomePage.java
│   │   ├── tests          # Test classes
│   │   │   └── AliExpressTest.java
│   │   └── utils          # Utility classes
│   │       └── AliExpressConstants.java
│   └── resources
│       └── log4j2.xml     # Logging configuration
```

## 🧪 Test Cases

Currently implemented test scenarios:
- Search functionality test
  - Navigate to homepage
  - Handle cookie and notification popups
  - Search for a product
  - Verify search results with multiple validation points
  - Capture screenshots and video recordings

## 📊 Test Reports

Test reports are automatically generated and deployed:
- Allure reports are available per branch
- Video recordings of test execution
- Screenshots for failed tests
- Access reports at: `https://{username}.github.io/{repo-name}/`

## 🔄 Continuous Integration

This project uses GitHub Actions for CI/CD with the following features:
- Triggers on all branch pushes and PRs to main
- Runs tests in headless mode with video recording
- Generates and deploys Allure reports
- Maintains separate reports for each branch
- Uploads test artifacts including videos and screenshots

## 📝 Configuration

Key configuration files:
- `pom.xml`: Maven dependencies and build settings
- `.github/workflows/test.yml`: GitHub Actions workflow
- `src/test/resources/log4j2.xml`: Logging configuration
- `src/test/java/utils/AliExpressConstants.java`: Test constants

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m '✨ Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
