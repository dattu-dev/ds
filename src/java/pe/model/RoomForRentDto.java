/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.model;

import java.util.Date;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class RoomForRentDto {

    //-----            your code here   --------------------------------
    private int id;
    private String title;
    private double price;
    private String location;
    private String description;
    private Date postedDate;
    private int status;
    private String username;

    public RoomForRentDto() {
    }

    public RoomForRentDto(int id, String title, double price, String location,
            String description, Date postedDate, int status, String username) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.location = location;
        this.description = description;
        this.postedDate = postedDate;
        this.status = status;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
