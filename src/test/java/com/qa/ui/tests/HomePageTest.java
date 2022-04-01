package com.qa.ui.tests;

import net.jodah.failsafe.internal.util.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.IOException;

@Execution(ExecutionMode.CONCURRENT)
public class HomePageTest extends BaseTest {

    @BeforeEach
    public void navigateAndSearch() {
        homePage.navigateHomePage();
        homePage.chooseCity("Amsterdam");
        homePage.search();
    }

    @Test
    public void validateParkingFilter() {
        Assert.isTrue(homePage.popularFilter(), "Parking is not selected");
    }

    @Test
    public void validatePriceFilter() {
        Assert.isTrue(homePage.sortByPrice(), "Price filter is not selected from high to low");
    }

    @Test
    public void validateHotelsFilter() throws IOException {
        homePage.popularFilter();
        homePage.sortByPrice();
        homePage.hotelList();
    }
}
