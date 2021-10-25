package com.pro3.coco;

public class TrainListItem {

    private int trainNo;
    private String stList;
    private String stDate;
    private String dpDate;
    private int person;
    private String stRegion;
    private String dpRegion;
    private int genPrice;
    private int spcPrice;

    public int getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(int trainNo) {
        this.trainNo = trainNo;
    }

    public String getStList() {
        return stList;
    }

    public void setStList(String stList) {
        this.stList = stList;
    }

    public String getStDate() {
        return stDate;
    }

    public void setStDate(String stDate) {
        this.stDate = stDate;
    }

    public String getDpDate() {
        return dpDate;
    }

    public void setDpDate(String dpDate) {
        this.dpDate = dpDate;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public String getStRegion() {
        return stRegion;
    }

    public void setStRegion(String stRegion) {
        this.stRegion = stRegion;
    }

    public String getDpRegion() {
        return dpRegion;
    }

    public void setDpRegion(String dpRegion) {
        this.dpRegion = dpRegion;
    }

    public int getGenPrice() {
        return genPrice;
    }

    public void setGenPrice(int genPrice) {
        this.genPrice = genPrice;
    }

    public int getSpcPrice() {
        return spcPrice;
    }

    public void setSpcPrice(int spcPrice) {
        this.spcPrice = spcPrice;
    }

    @Override
    public String toString() {
        return "TrainListItem{" +
                "trainNo=" + trainNo +
                ", stList='" + stList + '\'' +
                ", stDate='" + stDate + '\'' +
                ", dpDate='" + dpDate + '\'' +
                ", person=" + person +
                ", stRegion='" + stRegion + '\'' +
                ", dpRegion='" + dpRegion + '\'' +
                ", genPrice=" + genPrice +
                ", spcPrice=" + spcPrice +
                '}';
    }
}
