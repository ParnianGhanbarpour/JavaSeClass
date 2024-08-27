package sixth.model.bl;

import sixth.model.da.PersonDa;
import sixth.model.entity.Person;

public class PersonBl {
    public static void save(Person person) throws Exception {
        if(person.getAge()>18){
            try(PersonDa personDa = new PersonDa()){
                personDa.save(person);
            }
        }else{
            throw new Exception("Age is out of range !!!");
        }
    }

    public static Person findByUsername(String username) throws Exception {
        try(PersonDa personDa = new PersonDa()){
            return personDa.findByUsername(username);
        }
    }
}

