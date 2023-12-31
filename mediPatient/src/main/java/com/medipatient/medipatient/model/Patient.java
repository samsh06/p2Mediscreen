package com.medipatient.medipatient.model;

import java.time.LocalDate;

import com.medipatient.medipatient.constant.Gender;

import io.swagger.annotations.ApiParam;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Patient {

    @ApiParam(
            value = "id of patient",
            example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ApiParam(
            value = "firstName of patient",
            example = "Mario")
    @Column(nullable = false)
    @NotBlank(message = "required")
    private String firstName;

    @ApiParam(
            value = "lastName of patient",
            example = "Bros")
    @Column(nullable = false)
    @NotBlank(message = "required")
    private String lastName;

    @ApiParam(
            value = "birthday of patient",
            example = "1985-11-23")
    @Column(nullable = false)
//    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull
    private LocalDate birthday;

    //Gender
    @ApiParam(
            value = "gender of patient's",
            example = "Male, Female")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String address;

    private String phone;

    public Patient() {
    }

    public Patient(Long id, String firstName, String lastName, LocalDate birthday, Gender gender, String address, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}