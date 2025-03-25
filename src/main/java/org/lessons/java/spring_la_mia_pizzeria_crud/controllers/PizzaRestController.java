package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.models.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public ResponseEntity<List<Pizza>> index(@RequestParam(required = false) String name) {
        if (name == null || name.isEmpty()) {
            return new ResponseEntity<List<Pizza>>(pizzaService.getAll(), HttpStatus.OK);
        }

        List<Pizza> pizzas = pizzaService.getByName(name);

        if (pizzas.isEmpty()) {
            return new ResponseEntity<List<Pizza>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Pizza>>(pizzas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable Integer id) {
        Optional<Pizza> pizzaAttempt = pizzaService.getById(id);

        if (pizzaAttempt.isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Pizza>(pizzaAttempt.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pizza> store(@Valid @RequestBody Pizza newPizza) {
        return new ResponseEntity<Pizza>(pizzaService.create(newPizza), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@PathVariable Integer id, @Valid @RequestBody Pizza updatedPizza) {
        if (!pizzaService.existsById(id)) {
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }
        updatedPizza.setId(id);
        return new ResponseEntity<Pizza>(pizzaService.update(updatedPizza), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pizza> delete(@Valid @PathVariable Integer id) {
        if (!pizzaService.existsById(id)) {
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }

        pizzaService.deleteById(id);
        return new ResponseEntity<Pizza>(HttpStatus.OK);
    }

}
