package com.sample.web.controller;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import com.sample.model.Person;
import com.sample.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by U0128754 on 3/21/2016.
 */

@Controller
public class PersonController {

    public static final Logger logger = LoggerFactory.getLogger(PersonController.class);
    private PersonService personService;

    {
        StatusPrinter.print((LoggerContext) LoggerFactory.getILoggerFactory());
    }

    @Autowired(required = true)
    @Qualifier(value = "personService")
    public void setPersonService(PersonService ps) {
        this.personService = ps;
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String listPersons(ModelMap model) {
        List<Person> lps = this.personService.listPersons();

        model.addAttribute("person", new Person());

        if (lps != null) {
            model.addAttribute("listOfPersons", lps);

            lps.forEach(ps -> logger.info(ps.toString()));
        }

        return "persons";
    }

    @RequestMapping(value = "/persons/save", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute("person") Person p) {
        if (p.getId() > 0)
            this.personService.updatePerson(p);
        else
            this.personService.addPerson(p);

        return "redirect:/persons";
    }

    @RequestMapping(value = "/persons/remove/{id}", method = RequestMethod.GET, produces = "text/html")
    public String removePerson(@PathVariable int id, RedirectAttributes redirectAttributes) {
        if (id > 0) {
            this.personService.removePerson(id);
            redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE", id + " has been removed."
            );
        }
        return "redirect:/persons";
    }

    @RequestMapping(value = "/persons/edit/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, ModelMap model) {
        if (id > 0) {
            Person p = this.personService.getPersonById(id);

            if (p != null)
                model.addAttribute("person", p);
        }
        model.addAttribute("listOfPersons", this.personService.listPersons());
        return "persons";
    }


    //Replaced by ExceptionControllerAdvice
//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleControllerErr(Exception ex) {
//        ModelAndView model = new ModelAndView("errors");
//        model.addObject("exception", ex);
//        model.addObject("errMsg", "this is Exception.class");
//
//        return model;
//    }
}
