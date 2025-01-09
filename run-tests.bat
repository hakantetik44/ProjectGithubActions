@echo off
echo 🚀 Starting test execution...

REM Gerekli dizinleri oluştur
if not exist "target\cucumber-reports" mkdir target\cucumber-reports
if not exist "target\allure-results" mkdir target\allure-results

call mvn clean test
if %ERRORLEVEL% EQU 0 (
    echo ✅ Tests completed successfully
) else (
    echo ❌ Tests failed
)

echo 📊 Generating and opening Allure report...
REM Önce raporu oluştur, sonra tek seferde aç
call mvn allure:report
call mvn allure:serve
pause 