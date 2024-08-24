package com.india.railway.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.india.railway.service.CustomGeneratedValue;
import com.india.railway.service.CustomIdGenerationListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Entity
@Data // getter and setter and tostring
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(CustomIdGenerationListener.class)
@Table(name = "passenger")
public class Passenger {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CustomGeneratedValue(sequenceName = "my_entity")
    private Long id;

    @Size(min = 5, max = 15, message = "Name should be atlease 5 characters and not more than 15 characters")
    private String name;

    @Min(value = 18, message = "Age must be greater than or equal to 18")
    private int age;

    @NotNull(message = "Aadhar number cannot be null")
    @Pattern(regexp = "\\d{12}", message = "Aadhar number must be exactly 12 digits")
    private String aadhar_no;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "\\+91[0-9]{10}", message = "Phone number must start with +91 and be followed by 10 digits")
    private String cellNo;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be a past date")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Ensures proper date formatting
    private LocalDate dob;

    @NotNull(message = "Pincode number cannot be null")
    @Pattern(regexp = "\\d{6}", message = "Pincode number must be exactly 6 digits")
    private String pincode;

    @DecimalMin(value = "100.00", message = "Salary must be at least 100.00")
    @DecimalMax(value = "100000.00", message = "Salary cannot exceed 10000.00")
    private Long salary;

    // extra fileds for verification
    private String pnumber;
    private String gender;
    // private String dob;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "passenger_address", // Name of the join table
            joinColumns = @JoinColumn(name = "passenger_id", referencedColumnName = "id"), // Foreign key in join table
                                                                                           // pointing to Passenger
            inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id") // Foreign key in join
    )
    // @NotEmpty(message = "Passenger must have at least one address.")
    // @NotEmptyAdress
    @Valid // Ensure this is present to validate related entities (posts)
    @Size(min = 1, message = "User must have at least one post.") // Ensure at least one post
    private List<Address> address;

}
