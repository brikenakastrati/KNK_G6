package model;

public class carInventory {
    private Integer id;
    private String carid;
    private String carname;
    private Integer carstock;
    private double carprice;
    private String cartype;
    private String carimage;
    private String carstatus;

    public carInventory(Integer id, String carid, String carname, String cartype ,Integer carstock, double carprice, String carstatus ,String carimage) {
        this.id = id;
        this.carid = carid;
        this.carname = carname;
        this.cartype=cartype;
        this.carstock = carstock;
        this.carprice = carprice;
        this.carstatus = carstatus;
        this.carimage = carimage;

    }

    public Integer getId() {
        return id;
    }

    public String getCarid() {
        return carid;
    }

    public String getCarname() {
        return carname;
    }

    public Integer getCarstock() {
        return carstock;
    }

    public String getCartype() {
        return cartype;
    }

    public double getCarprice() {
        return carprice;
    }

    public String getCarstatus() {
        return carstatus;
    }

    public String getCarimage() {
        return carimage;
    }
}
