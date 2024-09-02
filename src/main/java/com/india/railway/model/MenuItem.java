package com.india.railway.model;

import java.util.ArrayList;
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

    // Constructor
    public MenuItem(String name, String url) {
        this.name = name;
        this.url = url;
        this.children = new ArrayList<>();
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for URL
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // Getter for child menu items
    public List<MenuItem> getChildren() {
        return children;
    }

    // Add a child menu item
    public void addChild(MenuItem child) {
        this.children.add(child);
    }

    // Remove a child menu item
    public void removeChild(MenuItem child) {
        this.children.remove(child);
    }

}
