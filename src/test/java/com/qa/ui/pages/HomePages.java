package com.qa.ui.pages;

import com.opencsv.CSVWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;


public class HomePages extends WebDriverWrapper {

    private final static String HOME_PAGE_URL = "https://www.hotels.com/?pos=HCOM_US&locale=en_US";

    private static final By CITY = By.xpath("//*[@placeholder='e.g. Las Vegas']");
    private static final By SEARCH_BUTTON = By.xpath("//button[@aria-label='Search']");
    private static final By SORT = By.xpath("//button[@aria-label='Price']");
    private static final By PRICE_DESC = By.xpath("//button[@value='PRICE_HIGHEST_FIRST']");
    private static final By PARKING =
            By.xpath("//ul[contains(@class,'list-popular')]//button[contains(@role,'checkbox') and contains(text(),'Parking')]");

    private static final By HOTEL_LIST = By.xpath("//*[@class='_3zH0kn']");
    private static final By ADDRESS_LIST = By.xpath("//*[@class='_2oHhXM']");
    private static final By PRICE_LIST = By.xpath("//*[contains(@aria-label,'Current price: $') or contains(@aria-label,'New price: $')]");
    private static final By COMPLETE_LIST = By.xpath("//*[@class='_2sPUhr']");

    public HomePages(WebDriver webDriver) {
        super(webDriver);
    }

    public void navigateHomePage() {
        driver.navigate().to(HOME_PAGE_URL);
    }

    public void chooseCity(String city) {
        click(CITY);
        fill(CITY, city);
        click(By.xpath("//h1"));
        // findElementByClickAbility(By.xpath("//input[@name='q-destination']"));
        findElementByClickAbility(By.xpath("//input[contains(@value,'Amsterdam')]"));
    }

    public void search() {
        click(SEARCH_BUTTON);
    }

    public boolean sortByPrice() {
        click(SORT);
        click(PRICE_DESC);
        findElementByClickAbility(By.xpath("//main//section/ul/li/div[@id='0']"));
        String priceSort = driver.findElement(By.xpath("//button[@aria-label='Price']")).getText();
        if (priceSort.equalsIgnoreCase("Price (high to low)")) {
            return true;
        }
        return false;
    }

    public boolean popularFilter() {
        click(PARKING);
        String value = driver.findElement(PARKING).getAttribute("aria-checked");
        if (value.equals("true")) {
            return true;
        }
        return false;
    }


    public void hotelList() throws IOException {

        CSVWriter write = new CSVWriter(new FileWriter(
                System.getProperty("user.dir") + "/src/testData/sample2.csv"), '|',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        List<String[]> hotels = new ArrayList<String[]>();
        List<WebElement> hotelList = driver.findElements(HOTEL_LIST);
        List<WebElement> addressList = driver.findElements(ADDRESS_LIST);
        List<WebElement> priceList = driver.findElements(PRICE_LIST);

        hotels.add(new String[]{"Hotel Name", "Hotel Address", "Hotel Price"});

        for (int i = 0; i < 5; i++) {
            hotels.add(new String[]{hotelList.get(i).getText(), addressList.get(i).getText(), priceList.get(i).getText()});
        }
        write.writeAll(hotels);
        write.flush();

    }
}
