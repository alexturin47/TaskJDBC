package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();

        userDao.saveUser("Василий", "Пупкин", (byte)50);
        userDao.saveUser("Иван", "Иванов", (byte)23);
        userDao.saveUser("Сергей", "Петров", (byte)35);
        userDao.saveUser("Семен" , "Семенов", (byte)30);

        List<User> users = userDao.getAllUsers();
        for (User user : users){
            System.out.println(user.getId() + " " + user.toString());
        }

        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
