package com.carol.rentacar.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Integer cpf;

    @Column(unique = true)
    private String phoneNumber;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    protected Customer() {
    }

    public Customer(String name, Integer cpf, String phoneNumber, String email, Address address) {
        this.name = name;
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

}
