package org.lessons.java.spring_la_mia_pizzeria_crud.repos;

import org.lessons.java.spring_la_mia_pizzeria_crud.models.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Integer> {

}
