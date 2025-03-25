package org.lessons.java.spring_la_mia_pizzeria_crud.repos;

import org.lessons.java.spring_la_mia_pizzeria_crud.models.Ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
