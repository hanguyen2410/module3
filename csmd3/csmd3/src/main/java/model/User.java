package model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class User {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String phone;
    private String email;
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    private String images;
    public User() {
    }

    public User(int id, String username, String password, String fullname, String phone, String email, String country, String images) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.country = country;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @NotEmpty(message = "UserName không được để trống")
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
    @NotEmpty(message = "FullName không được để trống")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Email(regexp = "^[A-Za-z0-9.]*[A-Za-z0-9]+@[A-Za-z0-9]+(.[A-Za-z0-9]+)$", message = "Email không hợp lệ!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
