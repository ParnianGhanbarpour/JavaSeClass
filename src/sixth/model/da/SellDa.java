package sixth.model.da;



import sixth.model.bl.PersonBl;
import sixth.model.entity.*;
import sixth.model.utils.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SellDa implements AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public SellDa() throws SQLException {
        connection = JdbcProvider.getConnection();
    }

    public void save(Sell sell) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT SELL_SEQ.NEXTVAL AS NEXT_ID FROM DUAL"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            sell.setId(resultSet.getInt("NEXT_USERNAME"));

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO SELL (ID, PRICE, SELL_TIME, PERSON_ID, PRODUCT_ID) VALUES (?, ?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, sell.getId());
            preparedStatement.setInt(2, sell.getPrice());
            preparedStatement.setString(3, sell.getSellTime().toString());
            preparedStatement.setInt(4, sell.getPerson().getId());
            preparedStatement.setInt(5, sell.getProduct().getId());

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public void edit(Sell sell) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE SELL SET PRICE=?, SELL_TIME=?, PERSON_ID=?, PRODUCT_ID=? WHERE ID=?"
            );

            preparedStatement.setInt(1, sell.getPrice());
            preparedStatement.setString(2, sell.getSellTime().toString());
            preparedStatement.setInt(3, sell.getPerson().getId());
            preparedStatement.setInt(4, sell.getProduct().getId());
            preparedStatement.setInt(5, sell.getId());

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public void remove(int id) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM SELL WHERE ID=?"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public List<Sell> findAll() throws SQLException {
        List<Sell> sellList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM SELL ORDER BY ID"
            );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Sell sell = Sell.builder()
                        .id(resultSet.getInt("ID"))
                        .price(resultSet.getInt("PRICE"))
                        .sellTime(LocalDateTime.parse(resultSet.getString("SELL_TIME")))
                        .person(PersonBl.findByUsername(resultSet.getInt("PERSON_USERNAME")))
                        .product(new Product(resultSet.getInt("PRODUCT_ID")))
                        .build();
                sellList.add(sell);
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return sellList;
    }

    public Sell findById(Integer id) throws SQLException {
        Sell sell = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM SELL WHERE ID=?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                sell = Sell.builder()
                        .id(resultSet.getInt("ID"))
                        .price(resultSet.getInt("PRICE"))
                        .sellTime(LocalDateTime.parse(resultSet.getString("SELL_TIME")))
                    //    .person(new Person(resultSet.getInt("PERSON_ID")))
                      //  .product(new Product(resultSet.getInt("PRODUCT_ID")))
                        .build();
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return sell;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
