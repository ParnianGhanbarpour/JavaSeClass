package sixth.test;



import sixth.model.da.PersonDa;
import sixth.model.entity.Person;

    public class PersonTest {
        public static void main(String[] args) throws Exception {
            Person person = Person.builder().id(4).name("Parnian").family("Ghanbarpour").build();

            try(PersonDa personDa = new PersonDa()){
//            personDa.save(person);
//            personDa.edit(person);
//            personDa.remove(4);
//            System.out.println(personDa.findAll());
//            System.out.println(personDa.findById(4));
//            System.out.println(personDa.findByFamily("Ghanbarpour"));
            }
        }
    }


