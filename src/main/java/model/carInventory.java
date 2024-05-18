package model;

import java.sql.Date;
import java.sql.Timestamp;

public class carInventory {
    private String carid;
    private String carname;
    private String cartype;
    private int carstock;
    private double carprice;
    private String carstatus;

    private Timestamp dateAdded;

    public carInventory(String carid, String carname, String cartype, int carstock, double carprice, String carstatus, Timestamp dateAdded) {
        this.carid = carid;
        this.carname = carname;
        this.cartype = cartype;
        this.carstock = carstock;
        this.carprice = carprice;
        this.carstatus = carstatus;
        this.dateAdded = dateAdded;
    }


    // Getters and setters for all fields including dateAdded

    public String getCarid() {
        return carid;
    }


    public String getCarname() {
        return carname;
    }



    public String getCartype() {
        return cartype;
    }



    public int getCarstock() {
        return carstock;
    }



    public double getCarprice() {
        return carprice;
    }



    public String getCarstatus() {
        return carstatus;
    }


    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }
}