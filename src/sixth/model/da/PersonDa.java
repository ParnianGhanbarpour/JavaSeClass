package sixth.model.da;

import sixth.model.entity.Gender;
import sixth.model.entity.Person;
import sixth.model.utils.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonDa implements AutoCloseable{
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public PersonDa() throws SQLException {
        connection = JdbcProvider.getConnection();
    }


    public void save(Person person) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO PERSON VALUES (?,?,?,?,?,?)"
        );
        preparedStatement.setString(1, person.getUsername());
        preparedStatement.setString(2, person.getPassword());
        preparedStatement.setString(3, person.getName());
        preparedStatement.setString(4, person.getFamily());
        preparedStatement.setString(5, String.valueOf(person.getGender()));
        preparedStatement.setString(6, String.valueOf(person.getBirthDate()));
        preparedStatement.execute();
    }


    public void edit(Person person) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "UPDATE PERSON SET PASSWORD=?, NAME=?, FAMILY=?,GENDER=?,BIRTHDATE=? WHERE USERNAME=?"
        );

        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getFamily());
        preparedStatement.setString(3, String.valueOf(person.getGender()));
        preparedStatement.setString(4, String.valueOf(person.getBirthDate()));
        preparedStatement.setString(5, String.getUsername);
        preparedStatement.execute();
    }


    public void remove(String username) throws SQLException {

        preparedStatement = connection.prepareStatement(
                "UPDATE PERSON SET ACTIVE=0 WHERE USERNAME=?"
        );
        preparedStatement.setString(1, username);
        preparedStatement.execute();
    }


    public List<Person> findAll() throws SQLException {
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PERSON WHERE ACTIVE=1 ORDER BY USERNAME"

        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Person> personList = new ArrayList<>();

        while (resultSet.next()) {

            Person person =
                    Person
                            .builder()
                            .username(resultSet.getString("USERNAME"))
                            .password(resultSet.getString("PASSWORD"))
                            .name(resultSet.getString("NAME"))
                            .family(resultSet.getString("FAMILY"))
                            .gender(Gender.valueOf(resultSet.getString("GENDER")))
                            .birthDate(LocalDate.parse(resultSet.getString("BIRTHDATE")))
//                            todo : I should fill productList
//                            .productList()
                            .build();
            personList.add(person);
        }
        return personList;
    }


    public Person findByUsername(String username) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PERSON WHERE USERNAME=? AND ACTIVE=1"
        );
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        Person person = null;

        if (resultSet.next()) {

            person =
                    Person
                            .builder()
                            .username(resultSet.getString("USERNAME"))
                            .name(resultSet.getString("NAME"))
                            .family(resultSet.getString("FAMILY"))
                            .gender(Gender.valueOf(resultSet.getString("GENDER")))
                            .birthDate(LocalDate.parse(resultSet.getString("BIRTHDATE")))
//                            todo : I should fill productList
//                            .productList()
                            .build();
        }
        return person;
    }

    public List<Person> findByFamily(String family) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PERSON WHERE FAMILY=? AND ACTIVE=1"
        );
        preparedStatement.setString(1, family);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Person> personList = new ArrayList<>();

        while (resultSet.next()) {
            Person person =
                    Person
                            .builder()
                            .username(resultSet.getString("USERNAME"))
                            .password(resultSet.getString("PASSWORD"))
                            .name(resultSet.getString("NAME"))
                            .family(resultSet.getString("FAMILY"))
                            .gender(Gender.valueOf(resultSet.getString("GENDER")))
                            .birthDate(LocalDate.parse(resultSet.getString("BIRTHDATE")))
//                            todo : I should fill productList
//                            .productList()
                            .build();
            personList.add(person);
        }
        return personList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}

