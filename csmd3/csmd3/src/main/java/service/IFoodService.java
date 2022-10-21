package service;

import model.Food;

import java.sql.SQLException;
import java.util.List;

public interface IFoodService {

    void insertFood(Food food) throws SQLException;

    public Food selectFood(int id);

    public List<Food> findAll();
    public List<Food> selectAllFoodsPaggingFilter(int offset, int noOfRecords, String q, int idcategory);

    public boolean deleteFood(int id) throws SQLException;

    public boolean updateFood(Food food) throws SQLException;
    public  boolean checkNameFood(String nameFood) throws SQLException;
    public int getNoOfRecords();

}
