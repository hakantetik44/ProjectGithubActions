#!/bin/bash
echo "🚀 Starting test execution..."

# Gerekli dizinleri oluştur
mkdir -p target/cucumber-reports
mkdir -p target/allure-results

# Testleri çalıştır
mvn clean test
TEST_STATUS=$?

if [ $TEST_STATUS -eq 0 ]; then
    echo "✅ Tests completed successfully"
else
    echo "❌ Tests failed"
fi

echo "📊 Generating and opening Allure report..."
# Önce raporu oluştur, sonra tek seferde aç
mvn allure:report && mvn allure:serve 