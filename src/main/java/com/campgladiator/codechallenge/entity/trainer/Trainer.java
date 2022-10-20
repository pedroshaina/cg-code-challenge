package com.campgladiator.codechallenge.entity.trainer;

import com.campgladiator.codechallenge.api.trainer.request.NewTrainerRequest;
import com.campgladiator.codechallenge.api.trainer.response.TrainerResponse;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String trainerNumber;

    public static Trainer fromJSON(final NewTrainerRequest json, final String trainerNumber) {
        final Trainer trainer = new Trainer();
        trainer.setEmail(json.getEmail());
        trainer.setPhone(json.getPhone());
        trainer.setFirstName(json.getFirstName());
        trainer.setLastName(json.getLastName());
        trainer.setTrainerNumber(trainerNumber);
        return trainer;
    }

    public TrainerResponse toJSON() {
        final TrainerResponse json = new TrainerResponse();
        json.setFirstName(this.getFirstName());
        json.setLastName(this.getLastName());
        json.setEmail(this.getEmail());
        json.setPhone(this.getPhone());
        json.setTrainerNumber(this.getTrainerNumber());
        return json;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getTrainerNumber() {
        return trainerNumber;
    }

    public void setTrainerNumber(final String trainerNumber) {
        this.trainerNumber = trainerNumber;
    }
}
