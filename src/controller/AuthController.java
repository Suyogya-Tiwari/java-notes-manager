package controller;

import util.DBConnection;
import util.HashUtil;
import model.User;

import java.sql.*;

public class AuthController {

    // REGISTER USER
    public static boolean register(String username, String password) {
        try (Connection con = DBConnection.getConnection()) {

            String hashed = HashUtil.hashPassword(password);

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users(username, password_hash) VALUES (?, ?)"
            );
            ps.setString(1, username);
            ps.setString(2, hashed);

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    // LOGIN USER
    public static User login(String username, String password) {
        try (Connection con = DBConnection.getConnection()) {

            String hashed = HashUtil.hashPassword(password);

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM users WHERE username=? AND password_hash=?"
            );
            ps.setString(1, username);
            ps.setString(2, hashed);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}