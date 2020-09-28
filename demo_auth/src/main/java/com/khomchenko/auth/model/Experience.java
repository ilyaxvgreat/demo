package com.khomchenko.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "experiences")
public class Experience {

    @Id
    private Long id;

    @Column(name = "company")
    private String company;

    @Column(name = "time")
    private String time;

    @Column(name = "user_id")
    private Long userId;
}
