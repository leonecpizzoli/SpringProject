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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


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
        //testSave();
        //testUpdate();
        //testDelete();
        //testSavePeople();
        //testDeletePeople();
        //testFindAndSort();
        //testFindByIds();
        //testIfExists();
        testPagination();
    }

    private void testPagination() {
        Page<Person> pages =
            personRepository.findAll(PageRequest.of(0, 4));
            pages.getContent().forEach(System.out::println);

            pages = personRepository.findAll(PageRequest.of(1, 4));
            pages.getContent().forEach(System.out::println);

            pages = personRepository.findAll(PageRequest.of(2, 4));
            pages.getContent().forEach(System.out::println);

    }

    private void testIfExists() {
        Boolean person = personRepository.existsById(13L);
        System.out.println("Result of Person 1 exists is: " + person);

        Boolean person2 = personRepository.existsById(30L);
        System.out.println("Result of Person 2 exists is: " + person2);

    }

    private void testFindByIds() {
        List<Person> people = personRepository.findAllById(Arrays.asList(12L,15L,16L,17L));
        people.forEach(System.out::println);
    }

    private void testFindAndSort() {
        List<Person> people = personRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName" ));
        people.forEach(System.out::println);
    }

    private void testDeletePeople() {

        Optional<Person> person1 = personRepository.findById(20L);
        Optional<Person> person2 = personRepository.findById(19L);
        Optional<Person> person3 = personRepository.findById(18L);

        List<Person> people = Arrays.asList(person1.get(),person2.get(),person3.get());
        personRepository.deleteInBatch(people);

    }

    private void testSavePeople() {
        Person person1 = new Person();
        person1.setFirstName("Alisson");
        person1.setLastName("Souza");
        person1.setAge(25);
        person1.setDocument(new Document("145.366.234.66",435673334));

        Person person2 = new Person();
        person2.setFirstName("Josias");
        person2.setLastName("Pereira");
        person2.setAge(65);
        person2.setDocument(new Document("145.366.142.55",135623314));

        Person person3 = new Person();
        person3.setFirstName("Ricardo");
        person3.setLastName("Santos");
        person3.setAge(45);
        person3.setDocument(new Document("225.136.254-66",212673334));

        List<Person> people = Arrays.asList(person1, person2, person3);
        personRepository.saveAll(people);

    }

    private void testDelete() {
        personRepository.deleteById(14L);

        Optional<Person> person = personRepository.findById(16L);

        personRepository.delete(person.get());
    }

    private void testUpdate() {
        Optional<Person> person = personRepository.findById(14L);
        System.out.println(person.toString());

        person.get().setFirstName("Michael");
        person.get().setLastName("Douglas");
        personRepository.save(person.get());

        Optional<Person> personConsult = personRepository.findById(14L);
        System.out.println(personConsult.get().toString());
    }

    private void testSave() {
        Person person = new Person();
        person.setFirstName("Lauri");
        person.setLastName("Markkanen");
        person.setAge(23);
        person.setDocument(new Document("355.113.422-23", 352124587));

        Address address = new Address();
        address.setCity("Chicago");
        address.setStreet("1902 West Madison Street");
        address.setType(Address.TypeAddress.RESIDENCIAL);

        person.setAddresses(Arrays.asList(address));
        person.addPhone(new Phone(Phone.TypePhone.COMERCIAL, "38320405"));

        personRepository.save(person);

        Optional<Person> consultant = personRepository.findById(person.getId());

        System.out.println(consultant.toString());

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
