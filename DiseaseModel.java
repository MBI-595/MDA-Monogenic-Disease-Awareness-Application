package com.example.monogenicdiseases;

public class DiseaseModel {
    String Selected;

    DiseaseModel(String Selected){
        this.Selected=Selected;
    }

    DiseaseModel(){

    }

    public String getSelected() {
        return Selected;
    }

    public void setSelected(String selected) {
        this.Selected = Selected;
    }
}
