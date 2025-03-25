package org.lessons.java.spring_la_mia_pizzeria_crud.services;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.models.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.models.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repos.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient getById(Integer id) {
        Optional<Ingredient> ingredientAttempt = ingredientRepository.findById(id);
        if (ingredientAttempt.isEmpty()) {
            // TODO
        }
        return ingredientAttempt.get();
    }

    public Ingredient create(Ingredient newIngredient) {
        return ingredientRepository.save(newIngredient);
    }

    public Ingredient update(Ingredient updatedIngredient) {
        return ingredientRepository.save(updatedIngredient);
    }

    public void delete(Ingredient toDeleteIngredient) {
        for (Pizza linkedPizza : toDeleteIngredient.getPizzas()) {
            linkedPizza.getIngredients().remove(toDeleteIngredient);
        }
        ingredientRepository.delete(toDeleteIngredient);
    }

    public void deleteById(Integer id) {
        Ingredient toDeleteIngredient = getById(id);
        delete(toDeleteIngredient);
    }

    public Boolean existsById(Integer id) {
        return ingredientRepository.existsById(id);
    }

    public Boolean exists(Ingredient ingredient) {
        return existsById(ingredient.getId());
    }

}
