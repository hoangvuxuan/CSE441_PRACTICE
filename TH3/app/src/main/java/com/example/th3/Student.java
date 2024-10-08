package com.example.th3;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private FullName full_name;
    private String gender;
    private String birth_date;
    private String email;
    private String address;
    private String major;
    private double gpa;
    private int year;

    public Student() {
    }

    public Student(String address, String birth_date, String email, FullName full_name, String gender, double gpa, int id, String major, int year) {
        this.address = address;
        this.birth_date = birth_date;
        this.email = email;
        this.full_name = full_name;
        this.gender = gender;
        this.gpa = gpa;
        this.id = id;
        this.major = major;
        this.year = year;
    }

    // Getters và Setters cho tất cả các thuộc tính
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FullName getFull_name() {
        return full_name;
    }

    public void setFull_name(FullName full_name) {
        this.full_name = full_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // Lớp lồng cho FullName
    public static class FullName implements Serializable {
        private String first;
        private String last;
        private String midd;

        public FullName() {
        }

        public FullName(String first, String last, String midd) {
            this.first = first;
            this.last = last;
            this.midd = midd;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public String getMidd() {
            return midd;
        }

        public void setMidd(String midd) {
            this.midd = midd;
        }
    }
}


