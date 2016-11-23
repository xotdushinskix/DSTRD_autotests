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




    public void select2(By searchButton, By select2, By inputPath, By suggestionPath,
                        By searchButtonOnSG, String reqString) {
        super.waitClickable(searchButton).click();
        driver.findElement(select2).click();
        driver.findElement(inputPath).sendKeys(reqString);
        super.waitToInvisibility(suggestionPath, "Searching…");
        driver.findElement(suggestionPath).click();
        driver.findElement(searchButtonOnSG).click();
    }




    public void select2(By select2, By inputPath, String reqString, By suggestionPath) {
        driver.findElement(select2).click();
        driver.findElement(inputPath).sendKeys(reqString);
        super.waitToInvisibility(suggestionPath, "Searching…");
        driver.findElement(suggestionPath).click();
    }




    public void searchInput(By searchButton, By inputField, String reqString, By searchButtonOnSG) {
        super.waitClickable(searchButton).click();
        driver.findElement(inputField).sendKeys(reqString);
        driver.findElement(searchButtonOnSG).click();
    }




    public void searchButtonAndSelectDropdown(By searchButton, By selectDD, String visibleText) {
        super.waitClickable(searchButton).click();
        WebElement element = driver.findElement(selectDD);
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(visibleText);
    }




    public void selectDropDown(By selectDD, String visibleText) {
        WebElement element = driver.findElement(selectDD);
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

}
