package com.example.PersonApp.Repository.Postgres;

import com.example.PersonApp.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface PostgresPersonRepository extends JpaRepository<Person, Long> {
}
