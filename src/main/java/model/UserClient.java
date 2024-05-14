package model;
public class UserClient {
    private Integer id;
    private String username;
    private String salt;
    public UserClient(Integer id, String username, String salt) {
        this.id = id;
        this.username = username;
        this.salt= salt;
    }
}
