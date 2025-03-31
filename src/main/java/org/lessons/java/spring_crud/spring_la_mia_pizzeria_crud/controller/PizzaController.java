package org.lessons.java.spring_crud.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring_crud.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.lessons.java.spring_crud.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_crud.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.lessons.java.spring_crud.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/pizze")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @Autowired
    private IngredientRepository ingredientRepository;
    
    /* show */
    @GetMapping("/{id}")
    public String getPizzaDetails(@PathVariable("id") Integer id, Model model) {
        Pizza pizza = repository.findById(id).get();

        System.out.println("Ingredienti per " + pizza.getNome() + ": " + pizza.getIngredients());
    
        model.addAttribute("pizza", pizza);
        return "pizze/show";
    }

    /* index */
    @GetMapping
    public String getPizzaList(@RequestParam(name ="nome", required = false) String nome, Model model) {
        List<Pizza> pizze;
        
        if (nome != null && !nome.isEmpty()) {
            pizze = repository.findByNomeContaining(nome); 
        } else {
            pizze = repository.findAll(); 
        }

        model.addAttribute("pizze", pizze);
        return "pizze/index";
    }
    
    //create control
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientRepository.findAll());

        return "pizze/create";
    }

    @PostMapping("/create")
    public String store( @Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "pizze/create";
        }

        repository.save(pizza);

        return "redirect:/pizze";
    }

    //update
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,Model model) {
        model.addAttribute("pizza", repository.findById(id).get());
        model.addAttribute("ingredients", ingredientRepository.findAll());

        return "pizze/edit";
    }

    @PostMapping("/edit/{id}")
    public String update( @Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "pizze/edit";
        }

        repository.save(pizza);

        return "redirect:/pizze";
    }

    //delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        repository.deleteById(id);
    
        return "redirect:/pizze";
    }

    //create special offer
    @GetMapping("/{id}/specialOffer")
    public String specialOffer(@PathVariable Integer id, Model model) {
        SpecialOffer specialOffer = new SpecialOffer();
        specialOffer.setPizza(repository.findById(id).get());

        model.addAttribute("specialOffer", specialOffer);
        return "specialOffers/create-or-update";
    }
    
}
