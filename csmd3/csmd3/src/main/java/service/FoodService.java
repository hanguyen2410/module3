package service;

import model.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FoodService extends DatabaseQuery implements IFoodService{
    private static final String CHECK_FOOD_NAME = "select * FROM food where foodname = ?;";
    private static final String UPDATE_FOOD_SQL = "UPDATE food SET foodname = ?,price= ?, descrip = ?, images = ?, type = ?  where id = ?";

    private static final String INSERT_FOOD = "INSERT INTO `food` (`foodname`,`price`,`descrip`,`images`,`type`)" + "VALUES(?,?,?,?,?)";
    private static final String SELECT_FOOD_BYID = "SELECT id, foodname, price, descrip, images, type FROM food WHERE id = ?";

    private static final String SELECT_ALL_FOOD = "SELECT id, foodname, price, descrip, images, type FROM food";
    private static final String DELETE_FOOD = "DELETE FROM food where id = ?";

    private int noOfRecords;
    public int getNoOfRecords() {
        return noOfRecords;
    }
    @Override
    public void insertFood(Food food) throws SQLException {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FOOD);
            preparedStatement.setString(1,food.getNameFood());
            preparedStatement.setDouble(2,food.getPrice());
            preparedStatement.setString(3,food.getDescription());
            preparedStatement.setString(4,food.getImages());
            preparedStatement.setInt(5,food.getType());

            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            printSQLException(ex);
        }

    }

    @Override
    public Food selectFood(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FOOD_BYID);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + "selectUser: " + preparedStatement);
            while (rs.next()){
                int idFood = rs.getInt("id");
                String nameFood = rs.getString("foodname");
                Double price = rs.getDouble("price");
                String descrip = rs.getString("descrip");
                String images = rs.getString("images");
                int type = rs.getInt("type");
                Food food = new Food(idFood,nameFood,price,descrip,images,type);
                return food;
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return null;
    }

    @Override
    public List<Food> findAll() {
        List<Food> foodList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FOOD);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                //SELECT id, foodname, price, descrip, images FROM food
                int idFood = rs.getInt("id");
                String nameFood = rs.getString("foodname");
                Double price = rs.getDouble("price");
                String descrip = rs.getString("descrip");
                String images = rs.getString("images");
                int type = rs.getInt("type");
                Food food = new Food(idFood,nameFood,price,descrip,images,type);
                foodList.add(food);
            }
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return foodList;
    }

    @Override
    public List<Food> selectAllFoodsPaggingFilter(int offset, int noOfRecords, String q, int type) {
        List<Food> listFood = new ArrayList<>();
        Food food = null;
        PreparedStatement stmt = null;
        Connection connection = null;
        try {
            connection = getConnection();
            if (type != -1) {
                String query = "select SQL_CALC_FOUND_ROWS * from food where foodname like ? and type = ? limit "
                        + offset + ", " + noOfRecords;
                stmt = connection.prepareStatement(query);
                stmt.setString(1, '%' + q + '%');
                stmt.setInt(2, type);
            } else {
                if (type == -1) {
                    String query = "select SQL_CALC_FOUND_ROWS * from food where foodname like ? limit "
                            + offset + ", " + noOfRecords;
                    stmt = connection.prepareStatement(query);
                    stmt.setString(1, '%' + q + '%');
                }
            }
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                food = new Food();
                food.setId(rs.getInt("id"));
                food.setNameFood(rs.getString("foodname"));
                food.setPrice(rs.getDouble("price"));
                food.setDescription(rs.getString("descrip"));
                food.setImages(rs.getString("images"));
                food.setType(rs.getInt("type"));
                listFood.add(food);
            }
            rs.close();

            rs = stmt.executeQuery("SELECT FOUND_ROWS()");
            if (rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listFood;
    }

    @Override
    public boolean deleteFood(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FOOD)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateFood(Food food) throws SQLException {
        boolean rowUpdate;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FOOD_SQL);
        ) {
            preparedStatement.setString(1, food.getNameFood());
            preparedStatement.setDouble(2, food.getPrice());
            preparedStatement.setString(3, food.getDescription());
            preparedStatement.setString(4,food.getImages());
            preparedStatement.setInt(5,food.getType());
            preparedStatement.setInt(6, food.getId());
            rowUpdate = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdate;
    }

    @Override
    public boolean checkNameFood(String nameFood) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_FOOD_NAME);
        preparedStatement.setString(1,nameFood);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            return true;
        }
        return false;
    }

}
