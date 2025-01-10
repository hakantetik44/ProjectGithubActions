<div align="center">

# 🌟 Amazon E2E Test Automation Framework
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.16.1-green.svg)](https://www.selenium.dev/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.14.0-brightgreen.svg)](https://cucumber.io/)
[![Maven](https://img.shields.io/badge/Maven-3.8.1-red.svg)](https://maven.apache.org/)
[![Allure](https://img.shields.io/badge/Allure-2.24.0-yellow.svg)](http://allure.qatools.ru/)

<img src="https://raw.githubusercontent.com/cucumber/cucumber-jvm/main/cucumber-core/src/main/resources/io/cucumber/core/logging/banner.txt" alt="Cucumber Banner" width="600">

### 🚀  Automation Framework with Java 17 & Cucumber BDD
</div>

## 📋 İçerik Tablosu
- [Genel Bakış](#-genel-bakış)
- [Teknoloji Yığını](#-teknoloji-yığını)
- [Proje Yapısı](#-proje-yapısı)
- [Özellikler](#-özellikler)
- [Gereksinimler](#-gereksinimler)
- [Kurulum](#-kurulum)
- [Testleri Çalıştırma](#-testleri-çalıştırma)
- [Raporlama](#-raporlama)
- [CI/CD](#-cicd)

## 🎯 Genel Bakış
Bu framework, Amazon web uygulamasının end-to-end testlerini otomatize etmek için tasarlanmıştır. Modern Java 17 özellikleri, Cucumber BDD yaklaşımı ve Page Object Model (POM) tasarım desenini kullanır.

## 💻 Teknoloji Yığını
- **Java 17**: Programlama Dili
- **Selenium 4**: Web Otomasyon
- **Cucumber 7**: BDD Framework
- **Maven**: Bağımlılık Yönetimi
- **Allure**: Test Raporlama
- **Log4j2**: Loglama
- **GitLab CI/CD**: Sürekli Entegrasyon
- **JUnit**: Test Framework

## 📂 Proje Yapısı
```
src
├── test
│   ├── java
│   │   └── com.amazon
│   │       ├── pages          # Page Object Classes
│   │       ├── steps          # Step Definitions
│   │       └── runners        # Test Runners
│   └── resources
│       ├── features          # Cucumber Feature Files
│       └── log4j2.xml        # Logging Configuration
```

## ✨ Özellikler
- 🎯 Page Object Model mimarisi
- 📝 Cucumber BDD senaryoları
- 📊 Allure raporlama entegrasyonu
- 📝 Detaylı loglama sistemi
- 🔄 GitLab CI/CD entegrasyonu
- 📸 Hata durumunda ekran görüntüsü
- ⚡ Paralel test çalıştırma desteği

## 🔧 Gereksinimler
- Java 17 JDK
- Maven 3.8+
- Chrome/Firefox Browser
- Git

## 💿 Kurulum
```bash
# Projeyi klonlayın
git clone https://gitlab.com/your-username/amazon-test-automation.git

# Proje dizinine gidin
cd amazon-test-automation

# Bağımlılıkları yükleyin
mvn clean install
```

## 🚀 Testleri Çalıştırma
### Maven ile:
```bash
mvn clean test
```

### Script ile:
Windows:
```bash
run-tests.bat
```

Mac/Linux:
```bash
chmod +x run-tests.sh
./run-tests.sh
```

## 📊 Raporlama
### Allure Raporu
```bash
# Raporu oluştur ve aç
mvn allure:serve

# Sadece rapor oluştur
mvn allure:report
```

### Cucumber Raporu
- HTML: `target/cucumber-reports/report.html`
- JSON: `target/cucumber-reports/cucumber.json`

## 🔄 CI/CD
GitLab CI/CD pipeline'ı şunları içerir:
- 🔄 Otomatik test çalıştırma
- 📊 Rapor oluşturma
- 📨 Sonuç bildirimi
- 📁 Artifact saklama

## 🎯 Test Senaryosu Örneği
```gherkin
Feature: Amazon Arama Fonksiyonu

  Scenario: Kullanici Amazon'da urun arayabilmeli
    Given Kullanici Amazon ana sayfasina gider
    When Kullanici arama kutusuna "laptop" yazar
    And Kullanici arama butonuna tiklar
    Then Kullanici arama sonuclarini görmeli
    And Sonuclarda "laptop" kelimesi bulunmali
```

## 🤝 Katkıda Bulunma
1. Fork yapın
2. Feature branch oluşturun (`git checkout -b feature/YeniOzellik`)
3. Değişikliklerinizi commit edin (`git commit -m 'Yeni özellik eklendi'`)
4. Branch'inizi push edin (`git push origin feature/YeniOzellik`)
5. Pull Request oluşturun

---

<div align="center">

### 📫 İletişim
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue.svg)](https://linkedin.com/in/yourusername)
[![Email](https://img.shields.io/badge/Email-Contact-red.svg)](mailto:your.email@example.com)

**Made with ❤️ by [Your Name]**

</div>
