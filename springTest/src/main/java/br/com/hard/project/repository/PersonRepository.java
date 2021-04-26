package br.com.hard.project.repository;

import br.com.hard.project.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    //Find by age that's is not in a list of parameters
    List<Person> findByAgeNotIn(Integer... ages);

    //Find by age in a list of parameters
    List<Person> findByAgeIn(Integer... ages);

    //Find by firstName greater than parameter
    List<Person> findByFirstNameGreaterThan(String firstName);

    //Find by age less than or equals the parameter
    List<Person> findByAgeLessThanEqual(Integer age);

    //Find by age greater than or equals the parameter
    List<Person> findByAgeGreaterThanEqual(Integer age);

    //Find by age less than the parameter
    List<Person> findByAgeLessThan(Integer age);

    //Find by age greater than the parameter
    List<Person> findByAgeGreaterThan(Integer age);

    //Find by lastName and age between the parameters
    List<Person> findByLastNameAndAgeBetween(String lastName, int min, int max);

    //Find by age using Between ages from parameters
    List<Person> findByAgeBetween(Integer min, Integer max);

    //Find by age or firstName equals from parameters
    List<Person> findByAgeOrFirstName(Integer age, String firstName);

    //Find by firstName AND lastName equals from parameters
    Person findByFirstNameAndLastName(String firstName, String lastName);

    //Find firstName equals from parameter
    List<Person> findByFirstNameLike(String firstName);

    //Find firstName different from parameter
    List<Person> findByFirstNameNotLike(String firstName);

    //Find ages equals from parameter
    List<Person> findByAge(Integer age);

    //Find ages different from parameter
    List<Person> findByAgeNot(Integer age);
}
