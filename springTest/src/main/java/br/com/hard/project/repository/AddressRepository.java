package br.com.hard.project.repository;

import br.com.hard.project.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {


    //Find by City Starting with Parameter and Street Ending with parameter
    List<Address> findByCityStartingWithAndStreetEndingWith(String city, String street);
    //Find street by containing as parameter
    List<Address> findByStreetContaining(String street);
    //Find street by ending with parameter
    List<Address> findByStreetEndingWith(String street);
    //Find city by starting with parameter
    List<Address> findByCityStartingWith(String city);

}
