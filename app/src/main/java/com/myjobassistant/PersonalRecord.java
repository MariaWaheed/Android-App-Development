package com.myjobassistant;

public class PersonalRecord {
    String fname;
    String last;
    String email;
    String date;
    String country;
    String city;
    String domicile;
    String postal;
    String address;
    String cell;
    String gender;
    String cnic;
    public PersonalRecord(){

    }

    public PersonalRecord(String fname, String last, String email, String date, String country, String city, String domicile, String postal, String address, String cell, String gender, String cnic) {
        this.fname = fname;
        this.last = last;
        this.email = email;
        this.date = date;
        this.country = country;
        this.city = city;
        this.domicile = domicile;
        this.postal = postal;
        this.address = address;
        this.cell = cell;
        this.gender = gender;
        this.cnic= cnic;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getFname() {
        return fname;
    }

    public String getLast() {
        return last;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getCell() {
        return cell;
    }

    public String getGender() {
        return gender;
    }

    public String getDomicile() {
        return domicile;
    }

    public String getEmail() {
        return email;
    }

    public String getPostal() {
        return postal;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setCity(String mycity) {
        this.city = mycity;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setCountry(String mycountry) {
        this.country = mycountry;
    }

    public void setDomicile(String mydomicile) {
        this.domicile = mydomicile;
    }

    public void setEmail(String myemail) {
        this.email = myemail;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }
}
