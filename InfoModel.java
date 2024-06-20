package com.example.monogenicdiseases;

public class InfoModel {

    String intro;
    String Sypmtoms;
    String Tests;
    String Providers;

    InfoModel(String intro,String Symptoms,String Tests,String Providers){
        this.intro=intro;
        this.Sypmtoms=Symptoms;
        this.Tests=Tests;
        this.Providers=Providers;
    }

    InfoModel(){

    }

    public String getintro() {
        return intro;
    }

    public void setintro(String Intro) {
        this.intro = intro;
    }

    public String getSypmtoms() {
        return Sypmtoms;
    }

    public void setSypmtoms(String sypmtoms) {
        Sypmtoms = sypmtoms;
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
