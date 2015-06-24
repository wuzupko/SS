package com.menu;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    Statement st = null;
        public Database(){
        try {
            Class.forName("org.h2.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test","sa", "");

           st = conn.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void creatTableProducts() {
        try {
        st.execute("CREATE TABLE Products (" +
                "ID int NOT NULL AUTO_INCREMENT," +
                "pName varchar(20) NOT NULL," +
                "pVeight real(5)," +
                "pCost real(5)," +
                "PRIMARY KEY (ID)" +
                ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product){

        try {
            st.execute("INSERT INTO Products (pName, pVeight, pCost)"+
                    "VALUES ('"+product.getName()+"',"+product.getKillogram()+","+product.getCost()+");");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteProduct(Product product){

        try {
            st.execute("DELETE FROM Products where"+
                    "pName=('"+product.getName()+"';");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getProduct(String product){

        try {
            st.execute("DELETE FROM Products where"+
                    "pName=('"+product.getName()+"';");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void closeConnection(){
        try{
        this.st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
