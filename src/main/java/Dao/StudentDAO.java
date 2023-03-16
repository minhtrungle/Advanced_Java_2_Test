package Dao;

import Connection.ConnectJDBC;
import Model.Student;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public static List<Student> getAllStudent() throws SQLException {
        List<Student> studentList = new ArrayList<>();
        Connection con = (Connection) ConnectJDBC.getConnection();
        final String sql = "SELECT * FROM `students`";

        try {
            Statement sta = con.createStatement();

            ResultSet res = sta.executeQuery(sql);

            while (res.next()) {
                Student st = new Student();
                st.setId(res.getString("id"));
                st.setFullName(res.getString("full_name"));
                st.setGender(res.getInt("gender"));
                st.setBirthday(res.getString("birthday"));
                st.setAddress(res.getString("address"));
                st.setPhone(res.getString("phone"));
                st.setEmail(res.getString("email"));
                st.setGpa(res.getDouble("gpa"));

                studentList.add(st);
            }
            res.close();
            sta.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public Student getByID(String id) throws SQLException {
        Connection con = ConnectJDBC.getConnection();
        final String sql = "SELECT * FROM `students` WHERE `id` LIKE '%" + id + "%'"  ;
        Student st = null;

        try {
            Statement sta = con.createStatement();

            ResultSet res = sta.executeQuery(sql);


            if (res.next()) {
                st = new Student();
                st.setId(res.getString("id"));
                st.setFullName(res.getString("full_name"));
                st.setGender(res.getInt("gender"));
                st.setBirthday(res.getString("birthday"));
                st.setAddress(res.getString("address"));
                st.setPhone(res.getString("phone"));
                st.setEmail(res.getString("email"));
                st.setGpa(res.getDouble("gpa"));

            }
            res.close();
            sta.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }

    public Student getByName(String name) throws SQLException {
        Connection con = ConnectJDBC.getConnection();
        final String sql = "SELECT * FROM `students` WHERE `full_name` LIKE '%" + name + "%'";
        Student st = null;

        try {
            Statement sta = con.createStatement();

            ResultSet res = sta.executeQuery(sql);

            if (res.next()) {
                st = new Student();
                st.setId(res.getString("id"));
                st.setFullName(res.getString("full_name"));
                st.setGender(res.getInt("gender"));
                st.setBirthday(res.getString("birthday"));
                st.setAddress(res.getString("address"));
                st.setPhone(res.getString("phone"));
                st.setEmail(res.getString("email"));
                st.setGpa(res.getDouble("gpa"));

            }
            res.close();
            sta.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }

    public void insertStudent(Student s) throws SQLException {
        Connection con = ConnectJDBC.getConnection();
        final String sql = String.format("INSERT INTO `students` VALUES ('%s', '%s', '%d', '%s', '%s', '%s', '%s', '%f')",
                s.getId(), s.getFullName(), s.getGender(), s.getBirthday(), s.getAddress(), s.getPhone(), s.getEmail(), s.getGpa());

        try {
            Statement sta = con.createStatement();

            long res = sta.executeUpdate(sql);
            if (res == 0) {
                System.out.println("Insert students thất bại");
            }
            sta.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student s, String id) throws SQLException {
        Connection con = ConnectJDBC.getConnection();

        Student st = getByID(id);
        if (st == null) {
            throw new RuntimeException("Không có học sinh thõa mãn");
        }

        final String sql = String.format("UPDATE `students` SET `full_name`='%s',`gender`='%d',`birthday`='%s',`address`='%s',`phone`='%s',`email`='%s',`gpa`='%f' WHERE `id` = '%s'",
                s.getFullName(), s.getGender(), s.getBirthday(), s.getAddress(), s.getPhone(), s.getEmail(), s.getGpa(), id);

        try {
            Statement sta = con.createStatement();

            long res = sta.executeUpdate(sql);
            if (res == 0) {
                System.out.println("Update students thất bại");
            }
            sta.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(String id) throws SQLException {
        Connection con = ConnectJDBC.getConnection();

        Student st = getByID(id);
        if (st == null) {
            throw new RuntimeException("Không có học sinh thõa mãn");
        }

        final String sql = "DELETE FROM `students` WHERE `id` LIKE '%" + id + "%'"  ;

        try {
            Statement sta = con.createStatement();

            long res = sta.executeUpdate(sql);
            if (res == 0) {
                System.out.println("Delete students thất bại");
            }
            sta.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
