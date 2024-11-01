package com.india.railway.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // getter and setter and tostring
@AllArgsConstructor
@NoArgsConstructor
// @EntityListeners(CustomIdGenerationListener.class)
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // Name of the menu item
    private String url; // URL or route associated with the menu item

    @OneToMany(mappedBy = "menuitem", cascade = CascadeType.ALL)
    private List<MenuItem> children;

    // Many employees report to one manager
    @ManyToOne
    @JoinColumn(name = "parentmenuitem_id")
    private MenuItem menuitem;

}
