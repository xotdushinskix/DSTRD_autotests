package com.distriread.autotests.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 07.11.16.
 */
public class InfoGetter {

    private WebDriver driver;

    public InfoGetter(FirefoxDriver driver) {
        this.driver = driver;
    }


    public List<String> getStringInfos(By elementsXpathPath) {
        List<String> infoText = new ArrayList<String>();
        List<WebElement> names = driver.findElements(elementsXpathPath);
        for (int i = 0 ; i < names.size(); i++) {
            String name = names.get(i).getText();
            infoText.add(name);
        }
        return infoText;
    }



    public List<String> getStrigRequiredWord(By elementsXpathPath) {
        List<String> infoText = new ArrayList<String>();
        List<WebElement> names = driver.findElements(elementsXpathPath);
        for (int i = 0 ; i < names.size(); i++) {
            String name = names.get(i).getText();
            String [] arr = name.split(" ");
            name = arr[0];
            infoText.add(name);
        }
        return infoText;
    }




    public List<Integer> getIntegerInfos(By elementsXpathPath) {
        List<Integer> intText = new ArrayList<Integer>();
        List<WebElement> intValues = driver.findElements(elementsXpathPath);
        for (int i = 0; i < intValues.size(); i++) {
            String wName = intValues.get(i).getText();
            intText.add(Integer.valueOf(String.valueOf(wName)));
        }
        return intText;
    }

}
