package ict.bean;

import java.io.Serializable;

public class ClientInfo implements Serializable{
    private String username,password;

    public ClientInfo() {
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
