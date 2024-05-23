package model;

public class RestockRequest {
    private int id;
    private String user;
    private String carName;
    private String timestamp;

    public RestockRequest(int id, String user, String carName, String timestamp) {
        this.id = id;
        this.user = user;
        this.carName = carName;
        this.timestamp = timestamp;
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
    public String getTimestamp() {
        return timestamp;
    }}
