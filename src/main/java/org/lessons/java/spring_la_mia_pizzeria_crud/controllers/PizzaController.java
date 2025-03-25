package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import org.lessons.java.spring_la_mia_pizzeria_crud.services.IngredientService;
import org.lessons.java.spring_la_mia_pizzeria_crud.services.PizzaService;
import org.lessons.java.spring_la_mia_pizzeria_crud.services.SpecialOfferService;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.models.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.models.SpecialOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private SpecialOfferService offerService;

    @Autowired
    private IngredientService ingredientService;

    @GetMapping("")
    public String index(@RequestParam(name = "search", required = false) String search, Model model) {
        List<Pizza> pizzas;
        if (search == null || search.isEmpty()) {
            pizzas = pizzaService.getAll();
        } else {
            pizzas = pizzaService.getByName(search);
        }

        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(
            @PathVariable("id") Integer id, Model model) {

        model.addAttribute("pizza", pizzaService.getById(id));
        return "pizzas/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientService.getAll());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza newPizza, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("ingredients", ingredientService.getAll());
            return "pizzas/create";
        }

        pizzaService.create(newPizza);

        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", pizzaService.getById(id));
        model.addAttribute("ingredients", ingredientService.getAll());
        return "pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza updatedPizza, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("ingredients", ingredientService.getAll());
            return "pizzas/edit";
        }

        pizzaService.update(updatedPizza);

        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Pizza toRemovePizza = pizzaService.getById(id);

        for (SpecialOffer offer : toRemovePizza.getSpecialOffers()) {
            offerService.delete(offer);
        }

        pizzaService.delete(toRemovePizza);

        return "redirect:/pizzas";
    }

    @GetMapping("/{id}/newoffer")
    public String newoffer(@PathVariable Integer id, Model model) {
        SpecialOffer specialOffer = new SpecialOffer();

        specialOffer.setPizza(pizzaService.getById(id));

        model.addAttribute("specialOffer", specialOffer);
        model.addAttribute("edit", false);

        return "specialOffers/create-or-edit";
    }

}
