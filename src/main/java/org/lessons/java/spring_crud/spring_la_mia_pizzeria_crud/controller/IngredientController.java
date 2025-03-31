package org.lessons.java.spring_crud.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring_crud.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.spring_crud.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_crud.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    /* index */
    @GetMapping
    public String index(Model model) {
        List<Ingredient> ingredients;

        ingredients = ingredientRepository.findAll();
        model.addAttribute("ingredients", ingredients);

        return "ingredients/index";
    }

    /* Create and Store */
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());

        return "ingredients/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-or-edit";
        }

        ingredientRepository.save(ingredient);

        return "redirect:/ingredient";
    }

    /* Edit and update */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
        model.addAttribute("edit", true);

        return "ingredients/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-or-edit";
        }

        ingredientRepository.save(ingredient);

        return "redirect:/ingredient";
    }

    /* delete */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Ingredient ingredientToDelete = ingredientRepository.findById(id).get();

        for (Pizza linkedPizza : ingredientToDelete.getPizze()) {
            linkedPizza.getIngredients().remove(ingredientToDelete);
        }

        ingredientRepository.delete(ingredientToDelete);
        return "redirect:/ingredient";
    }
}
