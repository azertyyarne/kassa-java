package model;

public class Product {
    private int productcode;
    private String name;
    private String productgroup;
    private double price;
    private int stock;

    public Product(int productcode, String name, String productgroup, double price, int stock) {
        setProductcode(productcode);
        setName(name);
        setProductgroup(productgroup);
        setPrice(price);
        setStock(stock);
    }

    public void setProductcode(int productcode) throws ModelException {
        if (productcode <0)
            throw new ModelException("Artikel moet een positieve artikelcode hebben");
        this.productcode = productcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ModelException {
        if (name == null || name.trim().isEmpty())
            throw new ModelException("Naam mag niet leeg zijn");
        this.name = name;
    }

    public void setProductgroup(String productgroup) {
        if (productgroup == null || productgroup.trim().isEmpty())
            throw new ModelException("Artikelgroep mag niet leeg zijn");
        this.productgroup = productgroup;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws ModelException {
        if (price <0)
            throw new ModelException("De prijs moet positief zijn");
        this.price = price;
    }

    public void setStock(int stock) throws ModelException {
        if (stock <0)
            throw new ModelException("Voorraad moet positief zijn");
        this.stock = stock;
    }
}
