package com.distriread.autotests.helpers;

import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by nikita on 07.11.16.
 */
public class PropertyReader {


    String result = "";
    InputStream inputStream;
    protected String propFileName;

    public PropertyReader(InputStream inputStream, String propFileName) {
        this.inputStream = inputStream;
        this.propFileName = propFileName;
    }


    public void PropertyWorker(String propItemName) throws IOException {
        try {
            Properties prop = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            result = prop.getProperty(propItemName);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
    }



    public By xP_Val(String propItemName) {
        try {
            PropertyWorker(propItemName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return By.xpath(result);
    }



    public String str_Val(String propItemName) {
        try {
            PropertyWorker(propItemName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }



}