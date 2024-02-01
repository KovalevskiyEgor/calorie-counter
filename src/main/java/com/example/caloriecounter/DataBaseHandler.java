package com.example.caloriecounter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DataBaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        //String connectionString ="jdbc:mysql://localhost:3306/products";


        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }


    public void signUpUser(String login, String password) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.PRODUCT_USERS + "(" + Const.USER_LOGIN + "," + Const.USER_PASSWORD + ")"
                + "VALUES(?,?)";
        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert)) {

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        }
    }

    public void signUpUser(double ccal, double proteins, double carbo, double fats) {

        String update = "UPDATE users SET Калории =?, Белки=?, Жиры=?,Углеводы=? WHERE Калории IS NULL";

        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(update)) {

            preparedStatement.setDouble(1, ccal);
            preparedStatement.setDouble(2, proteins);
            preparedStatement.setDouble(3, fats);
            preparedStatement.setDouble(4, carbo);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean signInUser(String login, String password) {
        String select = "SELECT * FROM users WHERE Логин LIKE ?";
        String pass = "";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, login);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                pass = (rs.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return pass.equals(password);
    }

    public String getNeededNutrient(String tableName,String login,String nutrientName,String mealName){
        //String select = mealName.length()>1?"SELECT * FROM "+tableName+" WHERE Логин LIKE ? AND Прием_пищи LIKE ?": "SELECT * FROM "+tableName+" WHERE Логин LIKE ?";
        String select =  mealName.length()>1?"SELECT SUM(Белки),SUM(Жиры),SUM(Углеводы),SUM(Калории) FROM "+tableName+" WHERE Логин LIKE ? AND Прием_пищи LIKE ? GROUP BY Прием_пищи": "SELECT * FROM "+tableName+" WHERE Логин LIKE ?";
        String pass = "";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, login);
            if(mealName.length()>1) {
            preparedStatement.setString(2, mealName);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                if(nutrientName.equals("Калории")) pass = (rs.getString(4));
                if(nutrientName.equals("Белки")) pass = (rs.getString(1));
                if(nutrientName.equals("Жиры")) pass = (rs.getString(2));
                if(nutrientName.equals("Углеводы")) pass = (rs.getString(3));
            }}
            else{
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    if(nutrientName.equals("Калории")) pass = (rs.getString(3));
                    if(nutrientName.equals("Белки")) pass = (rs.getString(4));
                    if(nutrientName.equals("Жиры")) pass = (rs.getString(5));
                    if(nutrientName.equals("Углеводы")) pass = (rs.getString(6));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return pass.equals("")?"0":pass;
    }


    public ObservableList<Product> getProducts() throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM products";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);

        ResultSet rs = preparedStatement.executeQuery();
        ObservableList<Product> observableList = FXCollections.observableArrayList();
        while (rs.next()) {
            observableList.add(new Product(rs.getString(1), rs.getString(2),(rs.getString(3)),(rs.getString(4)),(rs.getString(5)))) ;

        }

            return observableList;

}
    public void addProduct(String mealName,int amount,String login, String productName,int protein, int fats,int carbo,int calories){
        String insert = "INSERT INTO added_products_2 (Логин,Название,Белки,Жиры,Углеводы,Калории,Количество,Прием_пищи) VALUES (?,?,?,?,?,?,?,?)";
        int oldAmount=amount;
        amount=amount/100;
        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert)) {

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, productName);
            preparedStatement.setString(3, String.valueOf(protein*amount));
            preparedStatement.setString(4, String.valueOf(fats*amount));
            preparedStatement.setString(5, String.valueOf(carbo*amount));
            preparedStatement.setString(6, String.valueOf(calories*amount));
            preparedStatement.setString(7, String.valueOf(oldAmount));
            preparedStatement.setString(8, mealName);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public ObservableList<Product> getProducts(String login,String mealName) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM added_products_2 WHERE Логин LIKE ? AND Прием_пищи LIKE ?";
        //String s="SELECT SUM(Белки),SUM(Жиры),SUM(Углеводы),SUM(Калории) FROM added_products_2 WHERE Логин LIKE ? AND Прием_пищи LIKE ? GROUP BY Прием_пищи";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);

        preparedStatement.setString(1,login);
        preparedStatement.setString(2,mealName);

        ResultSet rs = preparedStatement.executeQuery();
        ObservableList<Product> observableList = FXCollections.observableArrayList();
        while (rs.next()) {
            observableList.add(new Product(rs.getString(2), rs.getString(3),(rs.getString(4)),(rs.getString(5)),(rs.getString(7)),(rs.getString(6)))) ;

        }

        return observableList;

    }

    public Product findProduct(String productName) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM products WHERE Название LIKE ?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        preparedStatement.setString(1,productName);

        ResultSet rs= preparedStatement.executeQuery();

        Product product = null;
        if(rs.next()) product=new Product(rs.getString(1), rs.getString(2),(rs.getString(3)),(rs.getString(4)),rs.getString(5)) ;

        return product;
    }

    public void updateProduct(String productName, String soldAmount,String samount,String mealName,String login) throws SQLException, ClassNotFoundException {
        int amount =Integer.valueOf(samount)/100;
        Product product = findProduct(productName);


        int proteins = Integer.valueOf(product.getProductProteins())*amount;
        int carbo = Integer.valueOf(product.getProductCarbs())*amount;
        int fats = Integer.valueOf(product.getProductFats())*amount;
        int calories = Integer.valueOf(product.getProductCalories())*amount;

        String select="UPDATE added_products_2 " +
                "SET Белки=?, Жиры=?, Углеводы=?, Калории=?,Количество=? " +
                "WHERE Название LIKE ? AND Количество LIKE ? AND Прием_пищи LIKE ? AND Логин LIKE ?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);

        preparedStatement.setString(1, String.valueOf(proteins));
        preparedStatement.setString(2, String.valueOf(fats));
        preparedStatement.setString(3, String.valueOf(carbo));
        preparedStatement.setString(4, String.valueOf(calories));
        preparedStatement.setString(5, samount);

        preparedStatement.setString(6, productName);
        preparedStatement.setString(7, soldAmount);
        preparedStatement.setString(8, mealName);
        preparedStatement.setString(9, login);


        preparedStatement.executeUpdate();


    }

    public void delete(String productName, String amount, String mealName, String login) throws SQLException, ClassNotFoundException {
        String select="DELETE FROM added_products_2 WHERE Логин LIKE ? AND Количество LIKE ? AND Прием_пищи LIKE ? AND Название LIKE ? ";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);

        preparedStatement.setString(1,login);
        preparedStatement.setString(2,amount);
        preparedStatement.setString(3,mealName);
        preparedStatement.setString(4,productName);

        preparedStatement.executeUpdate();
    }
}
