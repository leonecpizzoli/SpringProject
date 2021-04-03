package br.com.hard.project;

import br.com.hard.project.entity.Address;
import br.com.hard.project.entity.Document;
import br.com.hard.project.entity.Person;
import br.com.hard.project.entity.Phone;
import br.com.hard.project.repository.AddressRepository;
import br.com.hard.project.repository.DocumentRepository;
import br.com.hard.project.repository.PersonRepository;
import br.com.hard.project.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
//@ImportResource(value = "spring-data.xml")
public class SpringTestApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private AddressRepository addressRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //testConfiguration();
        testSave();
    }

    private void testSave() {
        Person person = new Person();
        person.setFirstName("Zach");
        person.setLastName("LaVine");
        person.setAge(25);
        person.setDocument(new Document("432.123.432-23", 352149987));

        Address address = new Address();
        address.setCity("Chicago");
        address.setStreet("1901 West Madison Street");
        address.setType(Address.TypeAddress.COMERCIAL);

        person.setAddresses(Arrays.asList(address));
        person.addPhone(new Phone(Phone.TypePhone.COMERCIAL, "32220303"));

        personRepository.save(person);


    }

    private void testConfiguration(){
        long totalPerson = personRepository.count();
        List<Person> people = personRepository.findAll();
        people.forEach(System.out::println);
        System.out.println("Total of People: "+ totalPerson);

        long totalPhone = phoneRepository.count();
        List<Phone> phones = phoneRepository.findAll();
        phones.forEach(System.out::println);
        System.out.println("Total of Phones: "+ totalPhone);

        long totalDocument = documentRepository.count();
        List<Document> documents = documentRepository.findAll();
        documents.forEach(System.out::println);
        System.out.println("Total of Documents: "+ totalDocument);

        long totalAddress = addressRepository.count();
        List<Address> addresses = addressRepository.findAll();
        addresses.forEach(System.out::println);
        System.out.println("Total of Addresses: "+ totalAddress);

    }
}
