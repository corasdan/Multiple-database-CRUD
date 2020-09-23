package com.example.PersonApp.Controller;


import com.example.PersonApp.Model.Person;
import com.example.PersonApp.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class PersonController {
    @Autowired
    private PersonService service;

    @RequestMapping("/postgres")
    public String viewHomePagePg(Model model){
        List<Person> listPersons = service.listAllPostgres();
        model.addAttribute("listPersons", listPersons);
        return "indexPostgres";
    }

    @RequestMapping("/maria")
    public String viewHomePageMaria(Model model){
        List<Person> listPersons = service.listAllMaria();
        model.addAttribute("listPersons", listPersons);
        return "indexMaria";
    }

    @GetMapping("/new")
    public String createNewPersonForm(Model model){
        Person person = new Person();
        model.addAttribute("person",person);
        return "new_entry";
    }

    @RequestMapping(value="/save", method=RequestMethod.POST, params="action=SaveInPostgres")
    public String savePersonInPostgres(@ModelAttribute("person") Person person, BindingResult result){
        if(result.hasErrors()){
            return "new_entry";
        }
        service.savePostgres(person);

        return "redirect:/postgres";    //specify the page to redirect to (homePageOfPostgres)
    }

    @RequestMapping(value="/save", method=RequestMethod.POST, params="action=SaveInMaria")
    public String savePersonInMaria(@ModelAttribute("person") Person person, BindingResult result){
        if(result.hasErrors()){
            return "new_entry";
        }
        service.saveMaria(person);

        return "redirect:/maria";    //specify the page to redirect to(MariaDB)
    }

    @RequestMapping(value="/save", method=RequestMethod.POST, params="action=SaveInBoth")
    public String savePersonInMariaAndPostgres(@ModelAttribute("person") Person person, BindingResult result){
        if(result.hasErrors()){
            return "new_entry";
        }
        service.saveMaria(person);
        service.savePostgres(person);

        return "redirect:/maria";    //specify the page to redirect to(MariaDB)
    }

    @RequestMapping("/editPG/{personid}")
    public ModelAndView editPersonInPostgresForm(@PathVariable(name = "personid") Long personid){
        ModelAndView mav= new ModelAndView("edit_personPostgres");

        Person person = service.getPostgres(personid);
        mav.addObject("person", person);

        return mav;
    }

    @RequestMapping("/editMDB/{personid}")
    public ModelAndView editPersonInMariaForm(@PathVariable(name = "personid") Long personid){
        ModelAndView mav= new ModelAndView("edit_person");

        Person person = service.getMaria(personid);
        mav.addObject("person", person);

        return mav;
    }

    @RequestMapping("deletePG/{personid}")
    public String deleteFromPostgres(@PathVariable(name = "personid") Long personid){
        service.deletePostgres(personid);

        return "redirect:/postgres";    //specify the page to redirect to (homePageOfPostgres)
    }

    @RequestMapping("deleteMDB/{personid}")
    public String deleteFromMaria(@PathVariable(name = "personid") Long personid){
        service.deleteMaria(personid);

        return "redirect:/maria";    //specify the page to redirect to(MariaDB)
    }

}
