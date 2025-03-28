package org.lessons.java.spring_crud.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring_crud.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.lessons.java.spring_crud.spring_la_mia_pizzeria_crud.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/specialOffer")
public class SpecialOfferController {
    
    @Autowired
    private SpecialOfferRepository repository;

    /* show */
    @GetMapping
    public String getSpecialOffersList( Model model) {
        List<SpecialOffer> specialOffers;

        specialOffers = repository.findAll(); 

        model.addAttribute("specialOffers", specialOffers);
        return "specialOffers/index";
    }

    /* store */
    @PostMapping("/create")
    public String store( @Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer, BindingResult bindingResult, Model model){
        
        if(bindingResult.hasErrors()){
            return "specialOffer/create-or-update";
        }

        repository.save(formSpecialOffer);

        return "redirect:/pizze/" + formSpecialOffer.getPizza().getId();
    }

    /* update */

    /* delete */
}
