package sixth.model.da;

import sixth.model.bl.PersonBl;
import sixth.model.entity.Person;
import sixth.model.entity.Product;
import sixth.model.entity.Sell;
import sixth.model.utils.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
            if (resultSet.next()) {
                sell.setId(resultSet.getInt("NEXT_ID"));
            }

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO SELL (ID, PRICE, SELL_TIME, USERNAME, PRODUCT_ID, DELETED) VALUES (?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, sell.getId());
            preparedStatement.setDouble(2, sell.getPrice());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(sell.getSellTime()));
            preparedStatement.setString(4, String.valueOf(sell.getPersonUsername()));
            preparedStatement.setInt(5, sell.getProductId());
            preparedStatement.setInt(6, sell.isDeleted() ? 1 : 0);

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
                    "UPDATE SELL SET PRICE=?, SELL_TIME=?, USERNAME=?, PRODUCT_ID=?, DELETED=? WHERE ID=?"
            );

            preparedStatement.setDouble(1, sell.getPrice());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(sell.getSellTime()));
            preparedStatement.setString(3, sell.getPersonUsername());
            preparedStatement.setInt(4, sell.getProductId());
            preparedStatement.setInt(5, sell.isDeleted() ? 1 : 0);
            preparedStatement.setInt(6, sell.getId());

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
                    "UPDATE SELL SET DELETED=1 WHERE ID=?"
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
                    "SELECT * FROM SELL WHERE DELETED=0 ORDER BY SELL_TIME DESC"
            );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Sell sell = Sell.builder()
                        .id(resultSet.getInt("ID"))
                        .price(resultSet.getDouble("PRICE"))
                        .sellTime(resultSet.getTimestamp("SELL_TIME").toLocalDateTime())
                        .personUsername(resultSet.getString("PERSON_USERNAME"))
                        .productId(resultSet.getInt("PRODUCT_ID"))
                        .deleted(resultSet.getInt("DELETED") == 1)
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

    public Sell findById(int id) throws SQLException {
        Sell sell = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM SELL WHERE ID=? AND DELETED=0"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                sell = Sell.builder()
                        .id(resultSet.getInt("ID"))
                        .price(resultSet.getDouble("PRICE"))
                        .sellTime(resultSet.getTimestamp("SELL_TIME").toLocalDateTime())
                        .personUsername(resultSet.getString("PERSON_USERNAME"))
                        .productId(resultSet.getInt("PRODUCT_ID"))
                        .deleted(resultSet.getInt("DELETED") == 1)
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
        if (preparedStatement != null && !preparedStatement.isClosed()) {
            preparedStatement.close();
        }
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
