package com.example.monogenicdiseases;

public class DBModel {

    String intro;
    String Symptoms;
    String Tests;
    String Providers;
    String Disease;

    DBModel(String intro,String Symptoms,String Tests,String Providers, String Disease){
        this.intro=intro;
        this.Symptoms=Symptoms;
        this.Tests=Tests;
        this.Providers=Providers;
        this.Disease=Disease;
    }

    DBModel(){

    }

    public String getintro() {
        return intro;
    }

    public void setintro(String intro) {
        this.intro = intro;
    }

    public String getSymptoms() {
        return Symptoms;
    }

    public void setSymptoms(String Symptoms) {
        Symptoms = Symptoms;
    }

    public String getDisease() {
        return Disease;
    }

    public void setDisease(String disease) {
        Disease = disease;
    }

    public String getTests() {
        return Tests;
    }

    public void setTests(String tests) {
        Tests = tests;
    }

    public String getProviders() {
        return Providers;
    }

    public void setProviders(String providers) {
        Providers = providers;
    }
}