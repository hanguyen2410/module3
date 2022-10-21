package service;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService extends DatabaseQuery implements IUserService{
    private static final String INSERT_USER = "INSERT INTO `user` ( `username`, `password`, `fullname`, `phone`, `email`,`country`,`images`)" + "VALUES ( ?, ?, ?, ?, ?,?,?)";
    private static final String SELECT_USER_BYID = "select * From user where id = ?" ;
    private static final String SELECT_ALL_USERS = "select* From user";
    private static final String CHECK_EMAIL_EXISTS = "SELECT * FROM user where email = ?";
    private static final String UPDATE_USER = "UPDATE user SET username = ?,password= ?, fullname = ?, phone = ?, email = ? , country = ?, images = ?  where id = ?";
    private static final String DELETE_FOOD = "DELETE FROM user where id = ?";
    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM user WHERE username like ?";

    @Override
    public void insertUser(User user) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getFullname());
        preparedStatement.setString(4,user.getPhone());
        preparedStatement.setString(5,user.getEmail());
        preparedStatement.setString(6,user.getCountry());
        preparedStatement.setString(7,user.getImages());
        preparedStatement.executeUpdate();
    }

    @Override
    public User selectUser(int id) throws SQLException {
        try{
            Connection connection = getConnection();
            PreparedStatement preparableStatement = connection.prepareStatement(SELECT_USER_BYID);
            preparableStatement.setInt(1 , id);

            ResultSet rs = preparableStatement.executeQuery();

            System.out.println(this.getClass() + " selectUser: " + preparableStatement);
            while (rs.next()){
                int idUser = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("fullname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String images = rs.getString("images");
                User user = new User(idUser,username,password,fullName,phone,email,country,images);
                return user;
            }
            connection.close();
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return null;
    }

    @Override
    public List<User> selectAllUsers() throws SQLException {
        List<User> listUser = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparableStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = preparableStatement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("fullname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String images = rs.getString("images");
                User user = new User(id,username,password,fullName,phone,email,country,images);
                listUser.add(user);
            }
            connection.close();
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return listUser;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FOOD)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdate;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
        ) {
            //UPDATE user SET username = ?,password= ?, fullname = ?, phone = ?, email = ? , country = ?, images = ?  where id = ?
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3,user.getFullname());
            preparedStatement.setString(4,user.getPhone());
            preparedStatement.setString(5,user.getEmail());
            preparedStatement.setString(6,user.getCountry());
            preparedStatement.setString(7,user.getImages());
            preparedStatement.setInt(8,user.getId());
            rowUpdate = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdate;
    }

    @Override
    public boolean checkEmailExists(String email) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EMAIL_EXISTS);
        preparedStatement.setString(1, email);

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()){
            return true;
        }
        return false;
    }
    public User getLogin(String username) throws SQLException {
        User user = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME);
                preparedStatement.setString(1, username);
                System.out.println(preparedStatement);

                ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            int id = rs.getInt("id");
            String password = rs.getString("password");
            String fullname = rs.getString("fullname");
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            String country = rs.getString("country");
            String images = rs.getString("images");

            user = new User(id, username, password, fullname, phone, country, email, images);


            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }
}
