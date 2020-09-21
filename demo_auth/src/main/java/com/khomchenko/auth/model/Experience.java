package com.khomchenko.auth.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "experiences")
public class Experience {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "company")
    private String company;

    @Column(name = "time")
    private String time;
}
