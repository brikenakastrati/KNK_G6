package model;

public class RestockRequest {
    private int id;
    private String user;
    private String carName;
    private String carType;
    private String requestDate;

    public RestockRequest(int id, String user, String carName, String carType, String requestDate) {
        this.id = id;
        this.user = user;
        this.carName = carName;
        this.carType = carType;
        this.requestDate = requestDate;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getCarName() {
        return carName;
    }

    public String getCarType() {
        return carType;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }
}
