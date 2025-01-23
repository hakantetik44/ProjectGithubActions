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





## CI/CD Pipeline Açıklaması

### Variables
İlk olarak `variables` kısmına odaklanalım. Burada, projemizde kullanacağımız bazı ayarları tanımlıyoruz. Örneğin, Maven için gerekli olan `MAVEN_OPTS` değişkenini burada tanımlıyoruz. Bu değişken, Maven'in yerel depo yolu gibi ayarları içeriyor, böylece projedeki bağımlılıkları doğru şekilde yönetebiliyoruz.

Ardından, `SELENIUM_URL` ve `CHROME_OPTIONS` gibi değişkenlerimizi belirliyoruz. Selenium Grid ile çalışacağımız için Selenium URL'sini bu şekilde tanımlıyoruz. Chrome seçenekleri de burada belirleniyor. Mesela, tarayıcının başsız modda çalışmasını sağlayacak parametreleri burada belirtiyoruz.

### Image
`Image` kısmında, kullanacağımız Docker imajını belirliyoruz. Burada, `maven:3.9.6-eclipse-temurin-17` imajını kullanıyoruz. Bu imaj, Java 17 ile uyumlu bir Maven ortamı sağlıyor ve testlerimiz için gerekli tüm araçlara sahip.

### Services
`Services` kısmında ise, Selenium Grid'i başlatacak bir container tanımlıyoruz. `selenium/standalone-chrome` imajını kullanarak, Chrome tarayıcısı üzerinde test yapabilmek için gerekli olan Selenium Grid servisini başlatıyoruz. Bu servis, testlerimizin doğru şekilde çalışması için kritik bir rol oynuyor.

### Cache
Şimdi, `cache` kısmına geçelim. Burada, Maven bağımlılıklarını `.m2/repository` dizininde tutuyoruz. Bu sayede, her CI çalıştırmasında aynı bağımlılıkları tekrar indirmemize gerek kalmıyor. Böylece daha hızlı bir test süreci sağlıyoruz.

### Stages
Sonrasında, `stages` kısmında test, raporlama ve dağıtım adımlarını tanımlıyoruz. İlk olarak test aşamasında testlerimizi çalıştırıyoruz. Testlerin başlamadan önce, bazı ek paketlerin yüklendiği `before_script` kısmı var. Bu aşamada, Selenium Grid'in başlatılıp başlatılmadığını kontrol ediyoruz.

### Test
Testlerimizi çalıştırmadan önce, Maven ile gerekli tüm ayarları yapıyoruz. Burada, Selenium Grid URL'sini, Chrome tarayıcısının ayarlarını ve diğer gerekli parametreleri environment variables üzerinden aktarıyoruz. Ardından, testleri çalıştırmaya başlıyoruz. Bu aşama, `mvn clean test` komutuyla başlıyor ve test raporlarını belirli formatlarda oluşturuyoruz.

### Test Sonuçları
Test sonuçlarını aldıktan sonra, `jq` komutunu kullanarak JSON dosyasından test raporlarını analiz ediyoruz. Başarı, başarısızlık, atlanmış ve bekleyen adımları sayıyoruz ve bu sonuçları raporluyoruz. Ayrıca, test özeti ve detayları bir dosyaya yazılıyor.

### Artifacts
`Artifacts` kısmında ise, test raporlarını, Allure ve Cucumber raporlarını, ve test özetini saklıyoruz. Bu dosyalar daha sonra raporlama aşamasında kullanılmak üzere saklanıyor.

### Raporlama
Raporlama aşamasında, `allure:report` komutuyla Allure raporunu oluşturuyoruz ve Cucumber raporunu da benzer şekilde kopyalayıp uygun dizinlere yerleştiriyoruz. Bu raporlar, testlerin detaylarını görsel olarak sunarak, kullanıcıya daha anlaşılır bir biçimde sunuluyor.

### Deploy
Son olarak, `pages` kısmında, tüm raporları GitLab Pages üzerinden yayınlıyoruz. Bu sayede, test raporlarına kolayca erişilebiliyor ve tüm proje ekibi sonuçları inceleyebiliyor. Bu adımda raporların yayına alınması için gerekli tüm işlemler yapılıyor.

Bu yapı, testlerimizin sorunsuz bir şekilde çalışmasını sağlıyor, ayrıca her aşamanın raporlanıp izlenebilmesini mümkün kılıyor. Yani bu CI/CD pipeline'ı sayesinde, test süreçlerimiz tamamen otomatize olmuş oluyor ve her şey düzgün bir şekilde izlenebiliyor.

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
# AmazonGitlabCI-CD
