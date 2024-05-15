package model;

public class Message {
    private int id;
    private String first_name;
    private String last_name;
    private String message;

    public Message(int id, String first_name, String last_name, String message) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getMessage() {
        return message;
    }

}
