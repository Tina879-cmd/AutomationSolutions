package com.qa.ui.tests;

import com.qa.ui.pages.HomePages;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    static WebDriver driver;
    static HomePages homePage;

    @BeforeAll
    public static void setupClass() {
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getDriverManager("chrome");
        homePage = new HomePages(driver);
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
    }
}
