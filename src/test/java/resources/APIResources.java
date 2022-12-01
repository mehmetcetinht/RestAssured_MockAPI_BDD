package resources;

public enum APIResources {

    // Enum classes are used while working with multiple constants
    // So instead of creating a normal class where you write down variables, getters and setters multiple times
    // Create methods once and then create one constructor with enum class

    addProductAPI("/product"),
    getAllProducts("/product"),
    getProductWithID("/product/");

    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }



}
