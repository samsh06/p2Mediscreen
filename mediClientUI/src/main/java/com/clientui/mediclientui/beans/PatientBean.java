package com.clientui.mediclientui.beans;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import com.clientui.mediclientui.constant.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PatientBean {

    private Long id;
    @NotBlank(message = "FirstName is mandatory")
    @Size(min = 1, max = 50)
    private String firstName;
    @NotBlank(message = "LastName is mandatory")
    @Size(min = 1, max = 50)
    private String lastName;
    @NotNull(message = "Select your birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @NotNull(message = "Select your Gender")
    private Gender gender;
    @Size(min = 0, max = 150)
    private String address;
    @Size(min = 0, max = 20)
    private String phone;

    public PatientBean() {
    }

    public PatientBean(Long id, String firstName, String lastName, LocalDate birthday, Gender gender, String address, String phone) {
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
        return "PatientBean{" +
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
