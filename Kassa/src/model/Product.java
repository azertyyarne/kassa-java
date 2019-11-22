package model;

public class Product {
    private int code;
    private String name;
    private String group;
    private double price;
    private int stock;

    public Product(int code, String name, String group, double price, int stock) {
        setCode(code);
        setName(name);
        setGroup(group);
        setPrice(price);
        setStock(stock);
    }

    private void setCode(int code) throws ModelException {
        if (code <0)
            throw new ModelException("Artikel moet een positieve artikelcode hebben");
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

    private void setPrice(double price) throws ModelException {
        if (price <0)
            throw new ModelException("De prijs moet positief zijn");
        this.price = price;
    }

    private void setStock(int stock) throws ModelException {
        if (stock <0)
            throw new ModelException("Voorraad moet positief zijn");
        this.stock = stock;
    }

    public int getCode() {
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
}
