package org.lessons.java.spring_la_mia_pizzeria_crud.services;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.models.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.models.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_crud.repos.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private SpecialOfferService offersService;

    public List<Pizza> getAll() {
        return pizzaRepository.findAll();
    }

    public List<Pizza> getByName(String searchTerm) {
        List<Pizza> pizzaList = pizzaRepository.findByNameContainingIgnoreCase(searchTerm);

        if (pizzaList.isEmpty()) {
            // TODO
        }

        return pizzaList;
    }

    public Pizza getById(Integer id) {
        Optional<Pizza> pizzaAttempt = pizzaRepository.findById(id);

        if (pizzaAttempt.isEmpty()) {
            // TODO
        }

        return pizzaAttempt.get();
    }

    public Pizza create(Pizza newPizza) {
        return pizzaRepository.save(newPizza);
    }

    public Pizza update(Pizza updatedPizza) {
        return pizzaRepository.save(updatedPizza);
    }

    public void delete(Pizza toDeletePizza) {
        for (SpecialOffer offer : toDeletePizza.getSpecialOffers()) {
            offersService.delete(offer);
        }
        pizzaRepository.delete(toDeletePizza);
    }

    public void deleteById(Integer id) {
        Pizza toDeletePizza = getById(id);

        for (SpecialOffer offer : toDeletePizza.getSpecialOffers()) {
            offersService.delete(offer);
        }
        delete(toDeletePizza);
    }

    public Boolean existsById(Integer id) {
        return pizzaRepository.existsById(id);
    }

    public Boolean exists(Pizza pizza) {
        return existsById(pizza.getId());
    }

}
