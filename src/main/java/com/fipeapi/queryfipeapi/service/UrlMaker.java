package com.fipeapi.queryfipeapi.service;

public class UrlMaker {
    private final String URL_LINK = "https://parallelum.com.br/fipe/api/v1/";
    private final String URL_BRAND = "/marcas/";
    private final String URL_MODEL = "/modelos/";
    private final String URL_YEAR = "/anos/";
    private String automobile = null;
    private String numberBrand = null;
    private String numberModel = null;
    private String numberYear = null;

    public void setAutomobile(String automobile) {
        this.automobile = automobile;
    }

    public void setNumberBrand(String numberBrand) {
        this.numberBrand = numberBrand;
    }

    public void setNumberModel(String numberModel) {
        this.numberModel = numberModel;
    }

    public void setNumberYear(String numberYear) {
        this.numberYear = numberYear;
    }

    public String url() {
        if (automobile != null && numberBrand != null && numberModel != null && numberYear != null) {
            return URL_LINK + automobile + URL_BRAND + numberBrand + URL_MODEL + numberModel + URL_YEAR + numberYear;
        } else if (automobile != null && numberBrand != null && numberModel != null) {
            return URL_LINK + automobile + URL_BRAND + numberBrand + URL_MODEL + numberModel + URL_YEAR;
        } else if (automobile != null && numberBrand != null) {
            return URL_LINK + automobile + URL_BRAND + numberBrand + URL_MODEL;
        } else if (automobile != null) {
            return URL_LINK + automobile + URL_BRAND;
        }
        return null;
    }

}
