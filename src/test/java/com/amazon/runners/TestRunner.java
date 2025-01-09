package com.amazon.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.amazon.steps"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/report.html",
        "json:target/cucumber-reports/cucumber.json",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    },
    monochrome = true
)
public class TestRunner {
    @AfterClass
    public static void createReportDirectories() {
        // Sadece dizinleri oluştur, raporu açma
        File targetDir = new File("target");
        if (!targetDir.exists()) {
            targetDir.mkdir();
        }
        
        File reportsDir = new File("target/cucumber-reports");
        if (!reportsDir.exists()) {
            reportsDir.mkdir();
        }
    }
} 