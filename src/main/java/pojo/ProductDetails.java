package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProductDetails {

    // The name of boolean keys will be removed the 'is' and take the rest as a key in pojo
    // like isActive -> active or isValid -> valid...
    // To prevent this, add JsonProperty annotation on top of the set/get method of boolean key like below:

    // @JsonProperty(value="isBasketDiscount")
    // public void setBasketDiscount(boolean basketDiscount){...}

    private String title;
    private String description;
    private int price;
    private boolean isBasketDiscount;
    private int basketDiscountPercentage;
    private double rating;
    private int stock;
    private boolean isActive;
    private String brand;
    private String category;
    private List<String> images;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBasketDiscount() {
        return isBasketDiscount;
    }

    @JsonProperty(value="isBasketDiscount")
    public void setBasketDiscount(boolean basketDiscount) {
        isBasketDiscount = basketDiscount;
    }

    public int getBasketDiscountPercentage() {
        return basketDiscountPercentage;
    }

    public void setBasketDiscountPercentage(int basketDiscountPercentage) {
        this.basketDiscountPercentage = basketDiscountPercentage;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isActive() {
        return isActive;
    }

    @JsonProperty(value="isActive")
    public void setActive(boolean active) {
        isActive = active;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
