package controller;

import util.DBConnection;
import model.Note;

import java.sql.*;
import java.util.ArrayList;

public class NotesController {

    // ADD NOTE
    public static void addNote(int userId, String title, String content) throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO notes(user_id, title, content) VALUES (?, ?, ?)"
        );
        ps.setInt(1, userId);
        ps.setString(2, title);
        ps.setString(3, content);

        ps.executeUpdate();
    }

    // GET ALL NOTES
    public static ArrayList<Note> getNotes(int userId) throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM notes WHERE user_id=?"
        );
        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();

        ArrayList<Note> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new Note(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("content")
            ));
        }

        return list;
    }

    // DELETE NOTE
    public static void deleteNote(int id) throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
                "DELETE FROM notes WHERE id=?"
        );
        ps.setInt(1, id);

        ps.executeUpdate();
    }

    public static void updateNote(int id, String title, String content) throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
                "UPDATE notes SET title=?, content=? WHERE id=?"
        );
        ps.setString(1, title);
        ps.setString(2, content);
        ps.setInt(3, id);

        ps.executeUpdate();
    }
}