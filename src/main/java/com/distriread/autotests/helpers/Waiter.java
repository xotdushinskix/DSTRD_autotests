package com.distriread.autotests.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by nikita on 07.11.16.
 */
public class Waiter {

    private WebDriver driver;

    public Waiter(FirefoxDriver driver) {
        this.driver = driver;
    }


    public WebElement waitClickable(By requiredElement) {
        WebElement someElement = new WebDriverWait(driver, 10).until(ExpectedConditions.
                elementToBeClickable(requiredElement));
        return someElement;
    }



    public boolean waitToVisible(By requiredElement, String text) {
        Boolean someElement = new WebDriverWait(driver, 10).until(ExpectedConditions.textToBe(requiredElement, text));
        return someElement;
    }



    public WebElement waitToVisibleElement(By requiredElement) {
        WebElement someElement = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(requiredElement));
        return someElement;
    }



    public boolean waitToInvisibility(By requiredElement, String text) {
        Boolean someElement = new WebDriverWait(driver, 10).until(ExpectedConditions.
                invisibilityOfElementWithText(requiredElement, text));
        return someElement;
    }

}
