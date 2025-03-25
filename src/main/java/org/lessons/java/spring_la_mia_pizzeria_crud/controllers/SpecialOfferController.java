package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import org.lessons.java.spring_la_mia_pizzeria_crud.models.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_crud.repos.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/specialOffers")
public class SpecialOfferController {

    @Autowired
    private SpecialOfferRepository repo;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("specialOffer") SpecialOffer newOffer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "specialOffers/create-or-edit";
        }

        repo.save(newOffer);

        return "redirect:/pizzas/" + newOffer.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("specialOffer", repo.findById(id).get());
        model.addAttribute("edit", true);

        return "specialOffers/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("specialOffer") SpecialOffer updatedOffer, BindingResult result) {
        if (result.hasErrors()) {
            return "specialOffers/create-or-edit";
        }

        repo.save(updatedOffer);

        return "redirect:/pizzas/" + updatedOffer.getPizza().getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        Integer pizzaId = repo.findById(id).get().getPizza().getId();

        repo.deleteById(id);

        return "redirect:/pizzas/" + pizzaId;
    }

}
