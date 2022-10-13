package service;

import model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService{
    private static Map<Integer,Product> products;
    static {
        products = new HashMap<>();
        products.put(1,new Product(1,"Tủ lạnh",1000000,"Làm Lạnh Nhanh","Sony"));
        products.put(2,new Product(2,"TiVi",2000000,"30 inch","Sam Sung"));
        products.put(3,new Product(3,"Máy Giặt",3000000,"Giặt Nhanh","Panasonic"));
        products.put(4,new Product(4,"Điều Hòa",3500000,"Điều Hòa 2 chiều","Aqua"));
        products.put(5,new Product(5,"Bình nước nóng",1500000,"Nóng Nhanh","Sony"));
        products.put(6,new Product(6,"Tủ Kính",2500000,"Kính 10 ly","Việt Nam"));
    }
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(),product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id,product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }

    @Override
    public Product findByName(String name) {
        return products.get(name);
    }
}
