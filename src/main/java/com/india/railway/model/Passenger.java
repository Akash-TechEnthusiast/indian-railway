package com.india.railway.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Data   // getter and setter and tostring 
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "passenger")
public class Passenger {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String pnumber;
    private String age;
    private String aadhar_no; 
    private String email;
    private String cellNo;
    private String address;
    private String gender;
    private String dob;
    private String city;
    private String state;
    private String pincode;
}
