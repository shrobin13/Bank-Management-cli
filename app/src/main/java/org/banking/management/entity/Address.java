package org.banking.management.entity;

public class Address {
    private String country;
    private String city;
    private int post_code;
    private String contact_no;


    public Address(String country, String city, int post_code, String contact_no) {
        this.country = country;
        this.city = city;
        this.post_code = post_code;
        this.contact_no = contact_no;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPost_code(int post_code) {
        this.post_code = post_code;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getPost_code() {
        return post_code;
    }

    public String getContact_no() {
        return contact_no;
    }
}
