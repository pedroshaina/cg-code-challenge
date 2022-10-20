package com.campgladiator.codechallenge.api.trainer.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrainerResponse {

    @JsonProperty("trainer_number")
    private String trainerNumber;

    private String email;

    private String phone;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    public String getTrainerNumber() {
        return trainerNumber;
    }

    public void setTrainerNumber(final String trainerNumber) {
        this.trainerNumber = trainerNumber;
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
}
