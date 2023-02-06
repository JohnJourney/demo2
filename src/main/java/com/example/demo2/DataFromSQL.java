package com.example.demo2;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataFromSQL {

    public static Connection Connection;
    public static Statement Statement;
    public static ResultSet ResultSet;
    public static ArrayList listData;
    public static ArrayList listColumnNames;

    //public static ArrayList<HashMap> qetDataFromSQLDataBase(String waytoDataBase) throws ClassNotFoundException, SQLException {
    public static HashMap<String, ArrayList> qetDataFromSQLDataBase(String waytoDataBase) throws ClassNotFoundException, SQLException {

        //todo >>> add table name as parameter
        // attention! ResultSet.getString - need to use appropriate get...[type].... according to type from sql

        try {
            Connection = null;
            Class.forName("org.sqlite.JDBC");

            String dbUrl = "JDBC:sqlite:" + waytoDataBase;

            Connection = DriverManager.getConnection(dbUrl);
            System.out.println("Connection sucessful!" + "\n");

            Statement = Connection.createStatement();
            ResultSet = Statement.executeQuery("select * from people");
            //ResultSet = Statement.executeQuery("select * from partners");

            listData = new ArrayList<HashMap>();

            ResultSetMetaData rsmd = ResultSet.getMetaData();

            int columnCount = rsmd.getColumnCount();

            listColumnNames = new ArrayList<String>();

            for (int i = 1; i <= columnCount; i++) {
                String nameOfColumn = rsmd.getColumnName(i);
                listColumnNames.add(nameOfColumn);
            }

            while (ResultSet.next()) {

                var mapNames = new HashMap<String, String>();
                // The column count starts from 1
//                for (int i = 1; i <= columnCount; i++) {
//                    String nameOfColumn = rsmd.getColumnName(i);
//                    mapNames.put(nameOfColumn, ResultSet.getString(nameOfColumn));
//                }
                int count = 0;
                while (listColumnNames.size() > count) {
                    var columnName = (String) listColumnNames.get(count);
                    mapNames.put(columnName, ResultSet.getString(columnName));
                    count++;
                }

                listData.add(mapNames);

            }

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
//        catch (SQLException s_ex) {
//            System.out.println(s_ex.getMessage());
//        }
        finally {
            try {

                Statement = null;
                ResultSet = null;
                Connection.close();

            } catch (Exception e) { /* Ignored */ }
            System.out.println("\n" + "End");
        }

        // return listNames;
        var mapColumnNames_and_listResultSet = new HashMap<String, ArrayList>();

        mapColumnNames_and_listResultSet.put("columnNames", listColumnNames);
        mapColumnNames_and_listResultSet.put("dataList", listData);

        return mapColumnNames_and_listResultSet;

    }
}
