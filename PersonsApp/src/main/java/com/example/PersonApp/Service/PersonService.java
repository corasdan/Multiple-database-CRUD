package com.example.PersonApp.Service;

import com.example.PersonApp.Model.Person;
import com.example.PersonApp.Repository.Maria.MariaDbPersonRepository;
import com.example.PersonApp.Repository.Postgres.PostgresPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@EnableTransactionManagement
public class PersonService {
    @Autowired
    private MariaDbPersonRepository mariaRepo;

    @Autowired
    private PostgresPersonRepository pgRepo;

//    public List<Person> listAll(){
//        return repo.findAll();
//    }
//
//
//    public void save (Person person){
//        repo.save(person);
//    }
//
//    public Person get(Long personid){
//        return repo.findById(personid).get();
//    }
//
//    public void delete(Long personid){
//        repo.deleteById(personid);
//    }

    //MariaDb CRUD operations
    @Transactional("mariadbTransactionManager")
    public List<Person> listAllMaria(){
        return mariaRepo.findAll();
    }

    @Transactional("mariadbTransactionManager")
    public void saveMaria (Person person){
        mariaRepo.save(person);
    }

    @Transactional("mariadbTransactionManager")
    public Person getMaria(Long personid){
        return mariaRepo.findById(personid).get();
    }

    @Transactional("mariadbTransactionManager")
    public void deleteMaria(Long personid){
        mariaRepo.deleteById(personid);
    }

    //Postgres CRUD operations
    @Transactional("postgresTransactionManager")
    public List<Person> listAllPostgres(){
        return pgRepo.findAll();
    }

    @Transactional("postgresTransactionManager")
    public void savePostgres (Person person){
        pgRepo.save(person);
    }

    @Transactional("postgresTransactionManager")
    public Person getPostgres(Long personid){
        return pgRepo.findById(personid).get();
    }

    @Transactional("postgresTransactionManager")
    public void deletePostgres(Long personid){
        pgRepo.deleteById(personid);
    }

}
