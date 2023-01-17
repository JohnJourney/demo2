package com.example.demo2;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

//public class sqlConnection {
public class DataFromSQL {
    public static Connection Connection;
    public static Statement Statement;
    public static ResultSet ResultSet;
    public static ArrayList listNames;

    //public static HashMap mapNames;

    public static ArrayList<HashMap> qetConnection() throws ClassNotFoundException, SQLException {

        try {
            Connection = null;
            Class.forName("org.sqlite.JDBC");

            Connection = DriverManager.getConnection("JDBC:sqlite:base 1 test.db");
            System.out.println("Connection sucessful!" + "\n");

            Statement = Connection.createStatement();
            ResultSet = Statement.executeQuery("select * from people");

            listNames = new ArrayList<HashMap>();

            while (ResultSet.next()) {

                var mapNames = new HashMap<String, String>();
                mapNames.put("name", ResultSet.getString("Name"));
                mapNames.put("phone", ResultSet.getString("Phone"));
                listNames.add(mapNames);

            }

        } catch (SQLException ex) {
            System.out.println("Error");
        }
//        catch (SQLException s_ex) {
//                System.out.println(s_ex.getMessage());
//                }
        finally {
            try {
                Connection.close();
            } catch (Exception e) { /* Ignored */ }
            System.out.println("\n" + "End");
        }

        return listNames;
    }
}

