package com.india.railway.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "train")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String train_name;
    private String train_number;
    private String start_date;
    private String end_date;
    private String noofcoaches;
    private String status; // if cancel or ontime
    private String start_station;
    private String end_station;

    // @ManyToMany(mappedBy = "trains")
    // @NotEmpty(message = "A train must have at least one passenger.")
    @ManyToMany(mappedBy = "trains")
    private Set<Passenger> passengers = new HashSet<>();

}