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

public class PersonDa implements DataAccess<Person, Integer>{
    private final Connection connection;
    private PreparedStatement preparedStatement;
    public PersonDa() throws SQLException {
        connection = JdbcProvider.getConnection();
    }

    @Override
    public void save(Person person) throws Exception {
        preparedStatement = connection.prepareStatement(
                "SELECT PERSON_SEQ.NEXTVAL AS NEXT_ID FROM DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        person.setId(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO PERSON VALUES (?,?,?,?,?)"
        );
        preparedStatement.setInt(1, person.getId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.setString(4, String.valueOf(person.getGender()));
        preparedStatement.setString(5, String.valueOf(person.getBirthDate()));
        preparedStatement.execute();
    }

    @Override
    public void edit(Person person) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE PERSON SET NAME=?, FAMILY=?,GENDER=?,BIRTHDATE=? WHERE ID=?"
        );

        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getFamily());
        preparedStatement.setString(3, String.valueOf(person.getGender()));
        preparedStatement.setString(4, String.valueOf(person.getBirthDate()));
        preparedStatement.setInt(5, person.getId());
        preparedStatement.execute();
    }

    @Override
    public void remove(Integer id) throws Exception {

        preparedStatement = connection.prepareStatement(
                "DELETE FROM PERSON WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Person> findAll() throws Exception {
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PERSON"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Person> personList = new ArrayList<>();

        while (resultSet.next()) {

            Person person =
                    Person
                            .builder()
                            .id(resultSet.getInt("ID"))
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
    public Person findById(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PERSON WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Person person = null;

        if (resultSet.next()) {

            person =
                    Person
                            .builder()
                            .id(resultSet.getInt("ID"))
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

    public List<Person> findByFamily(String family) throws Exception {
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PERSON WHERE FAMILY=?"
        );
        preparedStatement.setString(1, family);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Person> personList = new ArrayList<>();

        while (resultSet.next()) {
            Person person =
                    Person
                            .builder()
                            .id(resultSet.getInt("ID"))
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

