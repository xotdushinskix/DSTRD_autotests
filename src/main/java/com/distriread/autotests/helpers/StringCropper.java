package com.distriread.autotests.helpers;




/**
 * Created by nikita on 21.11.16.
 */
public class StringCropper {

    public String cropString(String requiredString, int startCrop, int endCrop) {
        String newString = requiredString.substring(startCrop, endCrop);
        return newString;
    }

}
