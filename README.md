<div align="center">
  <img src="https://www.selenium.dev/images/selenium_logo_square_green.png" alt="Selenium Logo" width="100"/>
  <h1>🌟 Amazon E2E Test Automation Framework</h1>

[![Java](https://img.shields.io/badge/Java-17-orange.svg?logo=java)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.16.1-green.svg?logo=selenium)](https://www.selenium.dev/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.14.0-brightgreen.svg?logo=cucumber)](https://cucumber.io/)
[![Maven](https://img.shields.io/badge/Maven-3.8.1-red.svg?logo=apache-maven)](https://maven.apache.org/)
[![Allure](https://img.shields.io/badge/Allure-2.24.0-yellow.svg?logo=qameta)](http://allure.qatools.ru/)
[![GitLab CI](https://img.shields.io/badge/GitLab%20CI-Pipeline-orange.svg?logo=gitlab)](https://gitlab.com/)
[![Log4j2](https://img.shields.io/badge/Log4j2-Logging-blue.svg?logo=apache)](https://logging.apache.org/log4j/2.x/)

</div>

## 📑 İçindekiler
- [🎯 Proje Hakkında](#-proje-hakkında)
- [⚡ Hızlı Başlangıç](#-hızlı-başlangıç)
- [🛠️ Teknolojiler & Araçlar](#️-teknolojiler--araçlar)
- [📂 Proje Yapısı](#-proje-yapısı)
- [🚀 Test Senaryoları](#-test-senaryoları)
- [📊 Raporlama](#-raporlama)
- [🔄 CI/CD Pipeline](#-cicd-pipeline)
- [📱 Cross Browser Testing](#-cross-browser-testing)
- [📝 Test Logları](#-test-logları)
- [🐛 Hata Ayıklama](#-hata-ayıklama)
- [📚 Dokümantasyon](#-dokümantasyon)

## 🎯 Proje Hakkında
Bu framework, Amazon web sitesinin end-to-end testlerini otomatize etmek için geliştirilmiştir. 
Modern test otomasyonu araçları ve best practice'leri kullanılarak oluşturulmuştur.

### 🌟 Özellikler
- ✨ Page Object Model (POM) tasarım deseni
- 🔄 Cross-browser testing desteği
- 📱 Responsive test yeteneği
- 🎯 BDD yaklaşımı
- 📊 Kapsamlı raporlama
- 🔍 Otomatik screenshot
- 📝 Detaylı loglama

## ⚡ Hızlı Başlangıç

### 📋 Ön Gereksinimler
- ☕ Java 17 JDK
- 📦 Maven 3.8+
- 🌐 Chrome/Firefox Browser
- 🔧 Git

### 🔨 Kurulum
```bash
# Repository'yi klonlayın
git clone https://gitlab.com/your-username/amazon-test-automation.git

# Proje dizinine gidin
cd amazon-test-automation

# Bağımlılıkları yükleyin
mvn clean install
```

## 🚀️ Teknolojiler & Araçlar
| Kategori        | Teknoloji/Araç                                              |
|-----------------|-------------------------------------------------------------|
| 💻 Dil          | ![Java](https://img.shields.io/badge/Java-17-orange)        |
| 🌐 Otomasyon    | ![Selenium](https://img.shields.io/badge/Selenium-4.16.1-green) |
| 🥒 BDD          | ![Cucumber](https://img.shields.io/badge/Cucumber-7.14.0-brightgreen) |
| 📦 Build        | ![Maven](https://img.shields.io/badge/Maven-3.8.1-red)      |
| 📊 Raporlama    | ![Allure](https://img.shields.io/badge/Allure-2.24.0-yellow) |
| 📝 Loglama      | ![Log4j2](https://img.shields.io/badge/Log4j2-Latest-blue)  |
| ✅ Assertions   | ![JUnit](https://img.shields.io/badge/JUnit-4.13-blue)      |
| 🔄 CI/CD        | ![GitLab CI](https://img.shields.io/badge/GitLab%20CI-Pipeline-orange) |

## 📂 Proje Yapısı
```
📦 amazon-test-automation
 ┣ 📂 src/test/java/com/amazon
 ┃ ┣ 📂 pages
 ┃ ┃ ┣ 📜 BasePage.java
 ┃ ┃ ┣ 📜 HomePage.java
 ┃ ┃ ┗ 📜 SearchResultPage.java
 ┃ ┣ 📂 steps
 ┃ ┃ ┣ 📜 Hooks.java
 ┃ ┃ ┗ 📜 SearchSteps.java
 ┃ ┣ 📂 utils
 ┃ ┃ ┣ 📜 BrowserUtils.java
 ┃ ┃ ┣ 📜 ConfigReader.java
 ┃ ┃ ┗ 📜 Driver.java
 ┃ ┗ 📂 runners
 ┃   ┗ 📜 TestRunner.java
 ┣ 📂 src/test/resources
 ┃ ┣ 📂 features
 ┃ ┃ ┗ 📜 amazon_search.feature
 ┃ ┣ 📜 config.properties
 ┃ ┗ 📜 log4j2.xml
 ┗ 📜 pom.xml
```

## 🚀 Test Senaryoları

### 🔍 Arama Fonksiyonu
```gherkin
Feature: Amazon Arama Fonksiyonu
  
  @smoke @regression
  Scenario: Kullanici Amazon'da urun arayabilmeli
    Given Kullanici Amazon ana sayfasina gider
    When Kullanici arama kutusuna "laptop" yazar
    And Kullanici arama butonuna tiklar
    Then Kullanici arama sonuclarini görmeli
    And Sonuclarda "laptop" kelimesi bulunmali
```

### 🏃 Testleri Çalıştırma
```bash
# Tüm testleri çalıştır
mvn clean test

# Belirli tag'leri çalıştır
mvn test -Dcucumber.filter.tags="@smoke"

# Paralel test çalıştır
mvn test -Dparallel=methods -DthreadCount=4
```

## 📊 Raporlama

### 📈 Allure Raporu
```bash
# Raporu oluştur
mvn allure:report

# Raporu görüntüle
mvn allure:serve
```

### 🥒 Cucumber Raporu
- 📊 HTML: `target/cucumber-reports/report.html`
- 📋 JSON: `target/cucumber-reports/cucumber.json`
- 📝 JUnit: `target/surefire-reports/TEST-*.xml`

## 🔄 CI/CD Pipeline

### 📋 Pipeline Aşamaları
1. 🔨 Build
   - Maven bağımlılıklarını yükle
   - Projeyi derle

2. 🧪 Test
   - Selenium Grid'i başlat
   - Testleri çalıştır
   - Test sonuçlarını kaydet

3. 📊 Report
   - Allure raporu oluştur
   - Cucumber raporu oluştur
   - Test özetini hazırla

4. 📤 Deploy
   - Raporları GitLab Pages'e yükle
   - Artifact'ları arşivle

## 📱 Cross Browser Testing
- 🌐 Chrome
- 🦊 Firefox
- 🧭 Edge
- 🎯 Safari

## 📝 Test Logları
```log
[INFO] Tests running...
[INFO] ✅ Homepage loaded successfully
[INFO] ✅ Search box found and clicked
[INFO] ✅ Search results displayed
[ERROR] ❌ Element not found: product-price
```

## 🐛 Hata Ayıklama
```java
// Debug modu için
mvn test -Dmaven.surefire.debug

// Remote debugging
-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005
```

## 🤝 Katkıda Bulunma
1. 🍴 Fork yapın
2. 🌿 Feature branch oluşturun
3. 💾 Değişikliklerinizi commit edin
4. 📤 Branch'inizi push edin
5. 📫 Pull Request gönderin

## 📞 İletişim
<div align="center">
  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue?style=for-the-badge&logo=linkedin)](https://linkedin.com/in/yourusername)
[![Email](https://img.shields.io/badge/Email-Contact-red?style=for-the-badge&logo=gmail)](mailto:your.email@example.com)
[![Twitter](https://img.shields.io/badge/Twitter-Follow-blue?style=for-the-badge&logo=twitter)](https://twitter.com/yourusername)

</div>

---

<div align="center">
  <strong>🌟 Bu proje ile ilgili detaylı bilgi için <a href="https://your-documentation-link.com">dokümantasyonu</a> inceleyebilirsiniz.</strong>
  
  Made with ❤️ by [Your Name]
</div>
