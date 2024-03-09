/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;

/**
 *
 * @author admin
 */
public class AccountDAO extends DBContext {

    public Account findByUsernamePassword(String username, String password) {
        //connect with DB
        connection = getConnection();
        //chuẩn bị câu lệnh SQL
        String sql = "SELECT *\n"
                + "  FROM [dbo].[Account]\n"
                + "  where username = ? and [password] = ?"; // SQL error?
        //Tạo đối tượng prepareStatement
        try {
            statement = connection.prepareStatement(sql);
            //Set parameter (optional)
            statement.setObject(1, username);
            statement.setObject(2, password);
            //thực thi câu lệnh
            resultSet = statement.executeQuery();
            //trả về kết quả
            if (resultSet.next()) {
                //String username_Found = resultSet.getString("username").trim();
                //String password_Found = resultSet.getString("password").trim();
                //Account account = new Account(username, password);
                //listFound.add(account);
                
                //
                String username_Found = resultSet.getString("username").trim();
                String password_Found = resultSet.getString("password").trim();
                Account account = new Account(username, password);
                return account;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
