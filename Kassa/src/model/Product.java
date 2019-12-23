package model;

public class Product {
    private String code;
    private String name;
    private String group;
    private double price;
    private int stock;

    public Product(String code, String name, String group, double price, int stock) {
        setCode(code);
        setName(name);
        setGroup(group);
        setPrice(price);
        setStock(stock);
    }

    private void setCode(String code) {
        this.code = code;
    }

    private void setName(String name) throws ModelException {
        if (name == null || name.trim().isEmpty())
            throw new ModelException("Naam mag niet leeg zijn");
        this.name = name;
    }

    private void setGroup(String group) {
        if (group == null || group.trim().isEmpty())
            throw new ModelException("Artikelgroep mag niet leeg zijn");
        this.group = group;
    }

    public void setPrice(double price) throws ModelException {
        if (price < 0)
            throw new ModelException("De prijs moet positief zijn");
        this.price = price;
    }

    public void setStock(int stock) throws ModelException {
        this.stock = stock;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Product){
            return ((Product) o).getCode().equals(code);
        }
        return false;
    }

    public void removeFromStock(){
        setStock(stock - 1);
    }
}
