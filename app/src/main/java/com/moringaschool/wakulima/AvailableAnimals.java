package com.moringaschool.wakulima;

public class AvailableAnimals {
    private String mType;
    private String mBreed;
    private String mImage;
    private String mDescription;


    public AvailableAnimals() {
    }

    public AvailableAnimals(String mType, String mBreed, String mImage, String mDescription) {
        this.mType = mType;
        this.mBreed = mBreed;
        this.mImage = mImage;
        this.mDescription = mDescription;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmBreed() {
        return mBreed;
    }

    public void setmBreed(String mBreed) {
        this.mBreed = mBreed;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
