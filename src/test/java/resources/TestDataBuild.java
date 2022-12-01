package resources;

import pojo.ProductDetails;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public static String getOneProduct(String title, String description, int price) {
        String response = "{\n" +
                "  \"getProducts\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"title\": \"" + title + "\",\n" +
                "      \"description\": \"" + description + "\",\n" +
                "      \"price\": " + price + ",\n" +
                "      \"isBasketDiscount\": true,\n" +
                "      \"basketDiscountPercentage\": 4,\n" +
                "      \"rating\": 2.19,\n" +
                "      \"stock\": 1,\n" +
                "      \"isActive\": false,\n" +
                "      \"brand\": \"Apple\",\n" +
                "      \"category\": \"ipad\",\n" +
                "      \"images\": [\n" +
                "        \"https://productimages.net/s/4/500/9655454531634.jpg\",\n" +
                "        \"https://productimages.net/s/58/1100/11339580801074.jpg/format:webp\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        return response;
    }

    public static String getMultipleProduct() {

        return "{\n" +
                "  \"getProducts\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"title\": \"Hepsi Home Pasta Tekli Sarkıt Avize Ahşap\",\n" +
                "      \"description\": \"Avize\",\n" +
                "      \"price\": 110,\n" +
                "      \"isBasketDiscount\": true,\n" +
                "      \"basketDiscountPercentage\": 4,\n" +
                "      \"rating\": 2.19,\n" +
                "      \"stock\": 1,\n" +
                "      \"isActive\": false,\n" +
                "      \"brand\": \"Apple\",\n" +
                "      \"category\": \"ipad\",\n" +
                "      \"images\": [\n" +
                "        \"https://productimages.net/s/4/500/9655454531634.jpg\",\n" +
                "        \"https://productimages.net/s/58/1100/11339580801074.jpg/format:webp\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"title\": \"Apple iPhone 12 64 GB\",\n" +
                "      \"description\": \"Apple\",\n" +
                "      \"price\": 15000,\n" +
                "      \"isBasketDiscount\": false,\n" +
                "      \"rating\": 3.2,\n" +
                "      \"stock\": 2000,\n" +
                "      \"brand\": \"Apple\",\n" +
                "      \"category\": \"smartphones\",\n" +
                "      \"images\": [\n" +
                "        \"https://productimages.net/s/76/1500/110000018213454.jpg\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

    }

    public ProductDetails addProductAsWhole(String title, String description, int price) {

        ProductDetails productDetails = new ProductDetails();

        List<String> imageList = new ArrayList<String>();
        imageList.add("https://productimages.net/s/4/500/9655454531634.jpg");
        imageList.add("https://productimages.net/s/58/1100/11339580801074.jpg/format:webp");

        productDetails.setTitle(title);
        productDetails.setDescription(description);
        productDetails.setPrice(price);
        productDetails.setBasketDiscount(true);
        productDetails.setBasketDiscountPercentage(4);
        productDetails.setRating(2.19);
        productDetails.setStock(1);
        productDetails.setActive(false);
        productDetails.setBrand("Apple");
        productDetails.setCategory("ipad");
        productDetails.setImages(imageList);

        return productDetails;
    }

    public String updateProduct(double rating, int stock, String isActive, String brand) {
        return "{\n" +
                "  \"rating\": " + rating + ",\n" +
                "  \"stock\": " + stock + ",\n" +
                "  \"isActive\": " + isActive + ",\n" +
                "  \"brand\": \"" + brand + "\"\n" +
                "}";
    }

}
