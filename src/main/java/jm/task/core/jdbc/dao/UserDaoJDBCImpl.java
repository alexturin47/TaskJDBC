package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao{

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getMySQLConnection()) {
            connection.setAutoCommit(false);
            String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT not null AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) not null, " +
                    "lastName VARCHAR(50) not null, " +
                    "age TINYINT not null)";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLSyntaxErrorException e){
            return;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.getMySQLConnection()) {
            connection.setAutoCommit(false);
            String sql = "DROP TABLE IF EXISTS users";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
        }catch (SQLSyntaxErrorException e) {
            return;
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age){
        try (Connection connection = Util.getMySQLConnection()){
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users( name, lastName, age) VALUES(?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2,lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            connection.commit();
            System.out.printf("User с именем %s - добавлен в базу \n", name);
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Connection connection = Util.getMySQLConnection()) {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM users WHERE id = " + id;
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list= new ArrayList<>();
        try(Connection connection = Util.getMySQLConnection()) {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM USERS";
            ResultSet rs = statement.executeQuery(sql);
            connection.commit();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                list.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try(Connection connection = Util.getMySQLConnection()) {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String sql = "TRUNCATE users";
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
