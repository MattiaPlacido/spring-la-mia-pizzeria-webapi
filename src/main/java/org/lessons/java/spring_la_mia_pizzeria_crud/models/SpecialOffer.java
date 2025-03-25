package org.lessons.java.spring_la_mia_pizzeria_crud.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "special_offers")
public class SpecialOffer {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The Title must not be null, empty or blank.")
    @Size(max = 50, message = "The Title must not be longer than 50 characters.")
    private String title;

    @Lob
    private String description;

    @NotNull(message = "Starting date of the offer must not be null.")
    private LocalDate startingDate;

    @NotNull(message = "Ending date of the offer must not be null.")
    @FutureOrPresent(message = "The offer cannot end in the past.")
    private LocalDate endingDate;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    // Methods
    @PrePersist
    @PreUpdate
    public void validateDates() {
        if (startingDate != null && endingDate != null && startingDate.isAfter(endingDate)) {
            throw new ValidationException("Starting date must be before ending date.");
        }
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public LocalDate getStartingDate() {
        return this.startingDate;
    }

    public void setStartingDate(LocalDate value) {
        this.startingDate = value;
    }

    public LocalDate getEndingDate() {
        return this.endingDate;
    }

    public void setEndingDate(LocalDate value) {
        this.endingDate = value;
    }

    public Pizza getPizza() {
        return this.pizza;
    }

    public void setPizza(Pizza value) {
        this.pizza = value;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String formattedStart = startingDate.format(formatter);
        String formattedEnd = endingDate.format(formatter);

        // Return the formatted string
        return String.format("%s \n%s \nStarts: %s | Ends: %s", title, description, formattedStart, formattedEnd);
    }

}
