package com.example.prac03;

public class Country {
    private int flagImage; // ID hình ảnh quốc kỳ
    private String name; // Tên quốc gia
    private String capital; // Thủ đô

    public Country(int flagImage, String name, String capital) {
        this.flagImage = flagImage;
        this.name = name;
        this.capital = capital;
    }

    public int getFlagImage() {
        return flagImage;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }
}
