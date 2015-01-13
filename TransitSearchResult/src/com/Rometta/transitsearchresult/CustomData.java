package com.Rometta.transitsearchresult;

import android.graphics.Bitmap;


public class CustomData {
    private Bitmap imageData_;
    private String textData_;
    private String textData2_;
 
    public void setImagaData(Bitmap image) {
        imageData_ = image;
    }
 
    public Bitmap getImageData() {
        return imageData_;
    }
 
    public void setTextData(String text) {
        textData_ = text;
    }
 
    public String getTextData() {
        return textData_;
    }
    
    public void setTextData2(String text) {
        textData2_ = text;
    }
 
    public String getTextData2() {
        return textData2_;
    }
}