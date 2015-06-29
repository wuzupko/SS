package com.menu;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

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
        st.execute("Drop table Products");
        st.execute("CREATE TABLE Products (" +
                "ID int NOT NULL AUTO_INCREMENT," +
                "pName varchar(20) NOT NULL," +
                "pWeight real(5)," +
                "pCost real(5)," +
                "PRIMARY KEY (ID)" +
                ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void creatTableDishes() {
        try {
        st.execute("Drop table Dishes");
        st.execute("CREATE TABLE Dishes (" +
                "ID int NOT NULL AUTO_INCREMENT," +
                "dName varchar (20)," +
                "dCategory int(1)," +
                "PRIMARY KEY (ID)" +
                ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void creatTableListIngredients() {
        try {
            st.execute("Drop table ListIngredients");
            st.execute("CREATE TABLE ListIngredients (" +
                    "ID int NOT NULL AUTO_INCREMENT," +
                    "dID int (2)," +
                    "pID int (2),"+
                    "pWeight real(5)," +
                    "PRIMARY KEY (ID)" +
                    ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addDish(Dish dish){

        try {
            HashMap<Product, Double> mapDishProductsList=new HashMap<Product, Double>(dish.getMapDishProductsList());
            //mapDishProductsList=dish.getMapDishProductsList();
            st.execute("INSERT INTO DISHES (dID,dName, dCategory)"+
                    "VALUES ("+dish.getID()+",'"+dish.getDishName()+"',"+dish.getCategory()+");");



            for (Map.Entry<Product, Double> entry : mapDishProductsList.entrySet()) {
                st.execute("INSERT INTO ListIngredients (dID,pID, pWeight)" +
                        "VALUES (" + dish.getID() + "," + entry.getKey().getId() + "," + entry.getValue()+ ");");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Dish getDish(String dName){
        Dish dish=new Dish();
        ResultSet result;
        try {
            result=st.executeQuery("SELECT dID,dName,dCategory FROM Dishes" +
                    "WHERE dName=" + dName + "; UNION ");
            dish.setID(result.getInt("dID"));
            dish.setDishName(dName);
            dish.setCategory(result.getInt("dCategory"));

            result=st.executeQuery("SELECT ListIngredients.dID, ListIngredients.pID, ListIngredients.pWeight, Products.ID, Products.pName, Products.Cost,  "+
            "FROM ListIngredients,Products" +
                    "WHERE (ListIngredients.dID="+ dish.getID()+") AND (ListIngredients.pID=Products.ID) ORDER BY ListIngredients.dID ;");

            result=st.executeQuery("SELECT dishes.ID ,listingredients.pid ,dishes.dname FROM Dishes INNER JOIN  ListINgredients ON dishes.ID=ListIngredients.dID INNER JOIN Products ON products.ID=ListIngredients.pID\n" +
                    "WHERE (ListIngredients.dID=1) AND (ListIngredients.pID=Products.ID) AND (dishes.dname='Borsh') ORDER BY  dishes.ID");




        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addProduct(Product product){

        try {
            st.execute("INSERT INTO Products (pName, pWeight, pCost)"+
                    "VALUES ("+product.getName()+"',"+product.getKillogram()+","+product.getCost()+");");

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
    public Product getProduct(String product){
        Product prod=new Product();
        ResultSet result;
        try {
           result= st.executeQuery("SELECT * FROM Products where" +
                   "pName=('" + product + "';");
            prod.setId(result.getInt("pID"));
            prod.setName(result.getString("pName"));
            prod.setKillogram(result.getDouble("pWeight"));
            prod.setCost(result.getDouble("pCost"));
            return prod;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
