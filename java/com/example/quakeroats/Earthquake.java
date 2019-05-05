package com.example.quakeroats;

class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private String date;

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public String getDate() {
        return date;
    }

    public Earthquake(double mMagnitude, String mLocation, String date) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.date = date;
    }


}
