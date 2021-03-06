package com.recore.tishkcare.Activitys.Model;

public class Doctor {

    private String email,password,name,phone, speciality,startHour,endHour,location,gender,doctorImg,doctorId;

    public Doctor() {
    }

    public Doctor(String email, String password, String name, String phone, String specialty, String startHour, String endHour, String location, String gender, String doctorImg, String doctorId) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.speciality = specialty;
        this.startHour = startHour;
        this.endHour = endHour;
        this.location = location;
        this.gender = gender;
        this.doctorImg = doctorImg;
        this.doctorId = doctorId;
    }

    public Doctor(String email, String password, String name, String phone, String specilty, String startHour, String endHour, String location, String gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.speciality = specilty;
        this.startHour = startHour;
        this.endHour = endHour;
        this.location = location;
        this.gender=gender;
    }

    public Doctor(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDoctorImg() {
        return doctorImg;
    }

    public void setDoctorImg(String doctorImg) {
        this.doctorImg = doctorImg;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
}
