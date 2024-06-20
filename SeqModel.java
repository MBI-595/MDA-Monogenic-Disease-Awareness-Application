package com.example.monogenicdiseases;

public class SeqModel {
    String ChainA;
    String ChainB;
    String ChainC;
    String ChainD;

    public SeqModel() {
    }

    public SeqModel(String chainA, String chainB, String chainC, String chainD) {
        ChainA = chainA;
        ChainB = chainB;
        ChainC = chainC;
        ChainD = chainD;
    }

    public String getChainA() {
        return ChainA;
    }

    public void setChainA(String chainA) {
        ChainA = chainA;
    }

    public String getChainB() {
        return ChainB;
    }

    public void setChainB(String chainB) {
        ChainB = chainB;
    }

    public String getChainC() {
        return ChainC;
    }

    public void setChainC(String chainC) {
        ChainC = chainC;
    }

    public String getChainD() {
        return ChainD;
    }

    public void setChainD(String chainD) {
        ChainD = chainD;
    }
}
