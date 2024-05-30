package com.india.railway.model;





import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket")
public class Ticket {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String from_station;
    private String to_station;
    private String train_no;
    private String train_start_time; 
    private String train_end_time;
    private String status; // confirmed // WL // RLWL
    private String passenger;
    @OneToMany
    @JoinTable(
        name = "ticket_passenger",
        joinColumns = @JoinColumn(name = "ticket_id"),
        inverseJoinColumns = @JoinColumn(name = "passenger_id")
    )
    private Set<Passenger> department = new HashSet<>();
  
}