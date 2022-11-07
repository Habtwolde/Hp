package com.example.mhts.hp.MainActivities.Model;

import android.widget.SectionIndexer;

public class Anime {

    private String PlateNumber;
    private String Code;
    private String Region;
    private String CrimeType;
    private String CrimeSection;
    private String Priority;
    private String ImageUrl;

    public Anime() {
    }
    public void setPlateNumber(String plateNumber) {
        PlateNumber = plateNumber;
    }

    public void setCode(String code) {
        Code = code;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public void setCrimeType(String crimeType) {
        CrimeType = crimeType;
    }

    public void setCrimeSection(String crimeSection) {
        CrimeSection = crimeSection;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public Anime(String plateNumber, String code, String region, String crimeType, String crimeSection, String priority, String imageUrl) {
        PlateNumber = plateNumber;
        Code = code;
        Region = region;
        CrimeType = crimeType;
        CrimeSection = crimeSection;
        Priority = priority;
        ImageUrl = imageUrl;

    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getPriority() {
        return Priority;
    }

    public String getPlateNumber() {  return PlateNumber;   }

    public String getCode() {
        return Code;
    }

    public String getRegion() {
        return Region;
    }

    public String getCrimeType() {
        return CrimeType;
    }

    public String getCrimeSection() {
        return CrimeSection;
    }
}
