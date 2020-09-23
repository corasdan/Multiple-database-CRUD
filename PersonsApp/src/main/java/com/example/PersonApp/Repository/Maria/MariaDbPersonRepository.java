package com.example.PersonApp.Repository.Maria;

import com.example.PersonApp.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface MariaDbPersonRepository extends JpaRepository<Person, Long>{

}
