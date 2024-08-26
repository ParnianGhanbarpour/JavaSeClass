package sixth.model.da;



import sixth.model.entity.Person;
import sixth.model.entity.Sell;
import sixth.model.utils.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SellDa implements AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public SellDa() throws SQLException {
        connection = JdbcProvider.getConnection();
    }

    public void save(Sell sell) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "SELECT SELL_SEQ.NEXTVAL AS NEXT_ID FROM DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        sell.setId(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO SELL VALUES (?,?,?,?,?)"
        );
        preparedStatement.setInt(1, sell.getId());
        preparedStatement.setString(2, sell.getPerson().getName());
        preparedStatement.setString(3, sell.getPerson().getFamily());
        preparedStatement.setString(4, sell.getProduct().getName());

        preparedStatement.execute();
    }



    @Override
    public void close() throws Exception {

    }
}
