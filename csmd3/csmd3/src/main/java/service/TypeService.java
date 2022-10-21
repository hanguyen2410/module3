package service;

import model.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class TypeService extends DatabaseQuery implements IType{

    private static final String SELECT_TYPE = "SELECT * FROM type where id = ?";
    private static final String SELECT_ALL_TYPE = "SELECT * FROM type";

    @Override
    public void insertType(Type type) throws SQLException {

    }

    @Override
    public Type selectType(int id) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TYPE);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int idCountry = rs.getInt("id");
                String name = rs.getString("nametype");
                Type country = new Type(id, name);
                return country;
            }
            connection.close();
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return null;
    }

    @Override
    public List<Type> selectAllType() {
        List<Type> listType = new ArrayList<>();
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TYPE);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectAllType " + preparedStatement);
            while (rs.next()){
                int id = rs.getInt("id");
                String nametype = rs.getString("nametype");
                Type type = new Type(id, nametype);
                listType.add(type);
            }
            connection.close();
        }catch (SQLException ex){
            printSQLException(ex);
        }

        return listType;
    }


    @Override
    public boolean deleteType(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateType(Type type) throws SQLException {
        return false;
    }
}
