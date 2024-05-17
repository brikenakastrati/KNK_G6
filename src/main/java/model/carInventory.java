package model;

import java.sql.Timestamp;

public class carInventory {
    private String carid;
    private String carname;
    private String cartype;
    private int carstock;
    private double carprice;
    private String carstatus;
    private String carimage;
    private Timestamp dateAdded;

    public carInventory(String carid, String carname, String cartype, int carstock, double carprice, String carstatus, String carimage, Timestamp dateAdded) {
        this.carid = carid;
        this.carname = carname;
        this.cartype = cartype;
        this.carstock = carstock;
        this.carprice = carprice;
        this.carstatus = carstatus;
        this.carimage = carimage;
        this.dateAdded = dateAdded;
    }

    // Getters and setters for all fields including dateAdded

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
    }

    public int getCarstock() {
        return carstock;
    }

    public void setCarstock(int carstock) {
        this.carstock = carstock;
    }

    public double getCarprice() {
        return carprice;
    }

    public void setCarprice(double carprice) {
        this.carprice = carprice;
    }

    public String getCarstatus() {
        return carstatus;
    }

    public void setCarstatus(String carstatus) {
        this.carstatus = carstatus;
    }

    public String getCarimage() {
        return carimage;
    }

    public void setCarimage(String carimage) {
        this.carimage = carimage;
    }

    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }
}