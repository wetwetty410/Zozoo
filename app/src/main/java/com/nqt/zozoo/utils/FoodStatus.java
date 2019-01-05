package com.nqt.zozoo.utils;

/**
 * Created by USER on 1/5/2019.
 */

public class FoodStatus {
    private String id;
    private String idStatus;
    private String nameStatus;

    public FoodStatus(String id, String idStatus, String nameStatus) {
        this.id = id;
        this.idStatus = idStatus;
        this.nameStatus = nameStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(String idStatus) {
        this.idStatus = idStatus;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }
}
