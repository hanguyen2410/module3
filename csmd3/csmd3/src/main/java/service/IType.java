package service;

import model.Type;

import java.sql.SQLException;
import java.util.List;

public interface IType {
    public void insertType(Type type) throws SQLException;

    public Type selectType(int id);

    public List<Type> selectAllType();

    public boolean deleteType(int id) throws SQLException;

    public boolean updateType(Type type) throws SQLException;
}
