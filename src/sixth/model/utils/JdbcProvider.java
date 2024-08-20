package sixth.model.utils;

import org.apache.commons.dbcp2.BasicDataSource;
import lombok.Getter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcProvider {
    @Getter
    private final static JdbcProvider jdbcProvider = new JdbcProvider();
    private final static BasicDataSource basicDataSource = new BasicDataSource();
    private JdbcProvider() {
    }
    public static Connection getConnection() throws SQLException {
        basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        basicDataSource.setUsername("javaee");
        basicDataSource.setPassword("java123");
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(20);
        return basicDataSource.getConnection();
    }
    public int getNextId(String sequenceName) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(String.format("SELECT %s.NEXTVAL AS NEXT FROM DUAL", sequenceName));
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int nextId = resultSet.getInt("NEXT");
        preparedStatement.close();
        connection.close();
        return nextId;
    }
}
