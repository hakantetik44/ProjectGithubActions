# 🛍️ AliExpress Test Automation

<div align="center">

[![Java](https://img.shields.io/badge/Java-21-orange.svg?style=for-the-badge&logo=java)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.16.1-green.svg?style=for-the-badge&logo=selenium)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.8.0-orange.svg?style=for-the-badge&logo=testng)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.8.1-red.svg?style=for-the-badge&logo=apache-maven)](https://maven.apache.org/)
[![Allure](https://img.shields.io/badge/Allure-2.24.0-yellow.svg?style=for-the-badge&logo=qameta)](http://allure.qatools.ru/)
[![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-CI/CD-blue.svg?style=for-the-badge&logo=github-actions)](https://github.com/features/actions)
[![Log4j2](https://img.shields.io/badge/Log4j2-2.20.0-blue.svg?style=for-the-badge&logo=apache)](https://logging.apache.org/log4j/2.x/)

  <img src="https://www.selenium.dev/images/selenium_logo_square_green.png" alt="Selenium Logo" width="100"/>

</div>

> Automated testing framework for AliExpress using Selenium WebDriver and TestNG

## ✨ Features

- Page Object Model design pattern
- TestNG test framework
- Selenium WebDriver for browser automation
- GitHub Actions for CI/CD
- Allure Reports for test reporting
- Log4j for logging

## 🔧 Prerequisites

- Java JDK 21 or higher
- Maven 3.6 or higher
- Chrome browser
- Git

## 🚀 Getting Started

1. Clone the repository:
```bash
git clone https://github.com/yourusername/aliexpress-test-automation.git
```

2. Navigate to project directory:
```bash
cd aliexpress-test-automation
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
│   │   ├── tests          # Test classes
│   │   └── utils          # Utility classes and constants
│   └── resources
│       └── log4j2.xml     # Logging configuration
```

## 🧪 Test Cases

Currently implemented test scenarios:
- Search functionality test
  - Navigate to homepage
  - Search for a product
  - Verify search results

## 📊 Test Reports

Test reports are automatically generated after each test run:
- Allure reports are available in `target/site/allure-maven-plugin`
- TestNG reports are available in `target/surefire-reports`

## 🔄 Continuous Integration

This project uses GitHub Actions for continuous integration. The workflow:
- Triggers on push to main/master branch
- Runs tests in headless mode
- Uploads test results as artifacts

## 📝 Configuration

Key configuration files:
- `pom.xml`: Maven dependencies and build configuration
- `.github/workflows/test.yml`: GitHub Actions workflow
- `src/test/resources/log4j2.xml`: Logging configuration

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
