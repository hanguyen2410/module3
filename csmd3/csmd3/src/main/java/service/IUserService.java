package service;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    public void insertUser(User user) throws SQLException;

    public User selectUser(int id) throws SQLException;

    public List<User> selectAllUsers() throws SQLException;

    public boolean deleteUser(int id) throws SQLException;

    public boolean updateUser(User user) throws SQLException;

    public boolean checkEmailExists(String email) throws SQLException;
    public  User getLogin(String username) throws SQLException;
}
