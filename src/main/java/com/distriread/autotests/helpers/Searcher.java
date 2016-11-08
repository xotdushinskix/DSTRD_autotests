package com.distriread.autotests.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by nikita on 07.11.16.
 */
public class Searcher extends Waiter{

    private WebDriver driver;

    public Searcher(FirefoxDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public void select2Search(By searchButton, By select2, By inputPath, By suggestionPath, By searchButtonOnSG,
                                String reqString) {
        waitClickable(searchButton).click();
        driver.findElement(select2).click();
        driver.findElement(inputPath).sendKeys(reqString);
        waitClickable(suggestionPath).click();
        driver.findElement(searchButtonOnSG).click();
    }



    public void searchInput(By searchButton, By inputField, By searchButtonOnSG, String reqString) {
        waitClickable(searchButton).click();
        driver.findElement(inputField).sendKeys(reqString);
        driver.findElement(searchButtonOnSG).click();
    }




    public void selectDropdown(By searchButton, By selectDD, String visibleText) {
        waitClickable(searchButton).click();
        WebElement element = driver.findElement(selectDD);
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(visibleText);
    }

}
