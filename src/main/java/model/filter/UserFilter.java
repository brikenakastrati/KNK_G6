package model.filter;

import java.time.LocalDateTime;

public class UserFilter {
    private String userName;
    private LocalDateTime from;
    private LocalDateTime to;
    private int page;
    private int size;
    private String email;

    public UserFilter(String userName) {
        this.userName = userName;
        this.from = from;
        this.to = to;
    }
    public String buildQuery(){
        String query="";
        if(this.userName != null){
            query+="And user_name like " + this.userName + "%";
        }
        if(this.from != null){
            query+="And from =>"+this.from;
        }
        if(this.to !=null){
            query+="And to <=" + this.to;
    }
        if (this.page >=0){
            int offset=this.page*this.size;
            int limit=this.size;
            query+="Limit" + limit+"offset=" + offset;
        }

        if (this.email != null){
            query+="AND email like " + this.email +"%";
        }
        return query;

    }
}
