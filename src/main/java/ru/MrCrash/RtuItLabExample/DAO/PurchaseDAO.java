package ru.MrCrash.RtuItLabExample.DAO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.MrCrash.RtuItLabExample.Moduls.Purchase;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PurchaseDAO {
    @Value("${spring.datasource.url}")
    private final String URL = null;
    @Value("${spring.datasource.password}")
    private final String PASSWORD = null;
    @Value("${spring.datasource.username}")
    private final String USERNAME = null;

    private static Connection connection;

    @PostConstruct
    public void initMethod() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Purchase> getAllPurchases() {
        List<Purchase> purchases = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM purchases");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Purchase purchase = new Purchase(resultSet.getInt("id_person"),
                        resultSet.getInt("id_purchase"),
                        resultSet.getString("name"),
                        resultSet.getDouble("cost"),
                        String.valueOf(resultSet.getDate("date")));
                purchases.add(purchase);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return purchases;
    }

    public List<Purchase> getPurchasesFromId(int id) {
        List<Purchase> purchases = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM purchases WHERE id_person=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Purchase purchase = new Purchase(resultSet.getInt("id_person"),
                        resultSet.getInt("id_purchase"),
                        resultSet.getString("name"),
                        resultSet.getDouble("cost"),
                        String.valueOf(resultSet.getDate("date")));
                purchases.add(purchase);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return purchases;
    }

    public void createNewPurchase(Purchase purchase) {
        try {
            int uniqueId = purchase.getIdPurchase();
            List<Integer> idList = getPurchasesFromId(purchase.getIdPerson())
                    .stream()
                    .map(purchase1 -> purchase1.getIdPurchase())
                    .collect(Collectors.toList());
            if (idList.indexOf(uniqueId) != -1) {
                uniqueId = idList.stream().max(Integer::compare).get();
                uniqueId++;
            }
            PreparedStatement statement = connection.prepareStatement("INSERT INTO purchases VALUES(?,?,?,?,?)");
            statement.setInt(1, purchase.getIdPerson());
            statement.setInt(2, uniqueId);
            statement.setString(3, purchase.getName());
            statement.setDouble(4, purchase.getCost());
            statement.setDate(5, java.sql.Date.valueOf(purchase.getPurchaseDate()));
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updatePurchase(int idPerson, int idPurchaseToBeUpdated, Purchase purchase) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement
                            ("UPDATE purchases SET " +
                                    "name=?, " +
                                    "cost=?, " +
                                    "date=? " +
                                    "WHERE id_person=? AND " +
                                    "id_purchase=?");
            statement.setString(1, purchase.getName());
            statement.setDouble(2, purchase.getCost());
            statement.setDate(3, java.sql.Date.valueOf(purchase.getPurchaseDate()));
            statement.setInt(4, idPerson);
            statement.setInt(5, idPurchaseToBeUpdated);
            System.out.println("hello");
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deletePurchase(int idPerson, int idPurchase) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM " +
                            "purchases WHERE " +
                            "id_person=? AND " +
                            "id_purchase=?");
            statement.setInt(1, idPerson);
            statement.setInt(2, idPurchase);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deletePerson(int idPerson) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM " +
                            "purchases WHERE " +
                            "id_person=?");
            statement.setInt(1, idPerson);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
