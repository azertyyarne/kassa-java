package database;

import model.Product;

public interface Database extends LoadSave{

    Product getProduct(int id);

}
