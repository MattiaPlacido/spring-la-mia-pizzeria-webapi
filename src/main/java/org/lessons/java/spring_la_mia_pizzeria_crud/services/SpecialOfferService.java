package org.lessons.java.spring_la_mia_pizzeria_crud.services;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.models.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_crud.repos.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService {

    @Autowired
    private SpecialOfferRepository offerRepository;

    public List<SpecialOffer> getAll() {
        return offerRepository.findAll();
    }

    public SpecialOffer getById(Integer id) {
        Optional<SpecialOffer> offerAttempt = offerRepository.findById(id);

        if (offerAttempt.isEmpty()) {
            // TODO
        }

        return offerAttempt.get();
    }

    public void delete(SpecialOffer toDeleteOffer) {
        offerRepository.delete(toDeleteOffer);
    }

    public void deleteById(Integer id) {
        SpecialOffer toDeletOffer = getById(id);
        delete(toDeletOffer);
    }

    public SpecialOffer create(SpecialOffer newOffer) {
        return offerRepository.save(newOffer);
    }

    public SpecialOffer update(SpecialOffer updatedOffer) {
        return offerRepository.save(updatedOffer);
    }

    public Boolean existsById(Integer id) {
        return offerRepository.existsById(id);
    }

    public Boolean exists(SpecialOffer offer) {
        return existsById(offer.getId());
    }

}
