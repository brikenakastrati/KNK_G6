package model;

public class RestockRequest {
    private int id;
    private String user;
    private String carName;
    private String carType;
    private String carDescription;
    private String requestDate;

    // Constructor
    public RestockRequest(int id, String user, String carName, String carType, String carDescription, String requestDate) {
        this.id = id;
        this.user = user;
        this.carName = carName;
        this.carType = carType;
        this.carDescription = carDescription;
        this.requestDate = requestDate;
    }

    // Getters
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

    public String getCarDescription() {
        return carDescription;
    }

    public String getRequestDate() {
        return requestDate;
    }
}
