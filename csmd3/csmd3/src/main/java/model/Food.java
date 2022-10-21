package model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Food {
    private int id;
    private String nameFood;
    private Double price;
    private String description;
    private String images;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public Food() {
    }

    public Food(int id, String nameFood, Double price, String description, String images,int type) {
        this.id = id;
        this.nameFood = nameFood;
        this.price = price;
        this.description = description;
        this.images = images;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotEmpty(message = "Tên món ăn không được để trống")
    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }
    @Min(value = 1000)
    @Max(value = 100000000)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
