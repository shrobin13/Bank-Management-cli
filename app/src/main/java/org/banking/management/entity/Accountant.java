package org.banking.management.entity;

public class Accountant {
    private String userName;
    private String email;
    private String password;

    public Accountant(String userName, String email, String password){
        super();
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public Accountant(){ super(); }

    public String getUserName(){
        return this.userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password =  password;
    }

    @Override
    public String toString(){
        return "Accountant [ username=" + this.userName + ", email=" + this.email + "password+" + this.password + "]";
    }

}
