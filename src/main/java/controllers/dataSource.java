package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class dataSource {


    public static void main(String[] args) {
        Connection con = null ;
        Statement stmt = null ;
        ResultSet result = null ;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection(
                    "jdbc:hsqldb:file:C:/Users/Double Brain/Desktop/webcredit/src/main/resources/database.tmp/clientDB", "SA", "");
            stmt = con.createStatement();
            result = stmt.executeQuery(
                    "SELECT CLIENTID, SURNAME, NAME, PATRONYMIC, PHONE, EMAIL, PASSPORTSERIES, PASSPORTID from CLIENT");

            while(result.next()){
                System.out.println(result.getString("SURNAME")+" | "
                        +result.getString("NAME") +" | "+result.getString("PATRONYMIC")
                        +" | "+result.getString("PHONE") +" | "+result.getString("EMAIL")+" | "+result.getString("PASSPORTSERIES")
                        +" | "+result.getString("PASSPORTID"));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}