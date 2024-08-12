package com.example.educonnect.ImgBbObj;

public class Users {
    public String uid;
    public String imgUrl;
    public String name;
    public String phone;
    public String dept;
    public String email;
    public String userType;

    public Users(String uid, String imgUrl, String name, String phone, String dept, String email, String userType) {
        this.uid = uid;
        this.imgUrl = imgUrl;
        this.name = name;
        this.phone = phone;
        this.dept = dept;
        this.email = email;
        this.userType = userType;
    }

    public Users() {

    }

    //    public Users(String uid, String name, String email, String imageUrl, String number, String userType) {
//    }
}
