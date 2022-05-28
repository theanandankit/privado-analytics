package com.analytics.analytics.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;



@SuppressWarnings("serial")
@Entity
@Table(name = "account")
public class Account implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq_gen")
    @SequenceGenerator(name = "account_seq_gen", sequenceName = "account_id_seq", allocationSize = 1)
    @Getter
    @Setter
    private Integer id;

    @Column(unique = true)
    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    @Column
    private String firstName;


    @Getter
    @Setter
    @Column(unique = true)
    private String email;

    @Getter
    @Setter
    @JsonIgnore
    private String password;

    @Getter
    @Setter
    @Column
    private boolean confirmed = false;

    @Getter
    @Setter
    @Column
    private String language = "en";

    @Getter
    @Setter
    @Column
    private String name;


//    @Getter
//    @Setter
//    @Enumerated(EnumType.STRING)
//    private Roles role;

    @Column
    @Getter
    @Setter
    private String uuid;
}
