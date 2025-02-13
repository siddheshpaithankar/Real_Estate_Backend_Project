package com.example.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "properties")
public class Properties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Types type;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin createdByAdmin;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private User createdByUser;

    @Column(nullable = false)
    private boolean status;

    @ManyToMany
    @JoinTable(
        name = "property_amenities",
        joinColumns = @JoinColumn(name = "property_id"),
        inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private List<Amenities> amenities;

    @Column(nullable = false)
    private String price;

    @Column(name = "owner_name")
    private String ownerName;

    @ElementCollection
    @CollectionTable(name = "property_photos", joinColumns = @JoinColumn(name = "property_id"))
    @Column(name = "photo_url")
    private List<String> photos; // New field to store photo URLs

    public Properties() { // default constructer 
    }

    public Properties(Integer id, String title, String description, Types type, Location location,
                      Admin createdByAdmin, User createdByUser, boolean status, List<Amenities> amenities,
                      String price, String ownerName, List<String> photos) {  // parameteriesed constructer 
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.location = location;
        this.createdByAdmin = createdByAdmin;
        this.createdByUser = createdByUser;
        this.status = status;
        this.amenities = amenities;
        this.price = price;
        this.ownerName = ownerName;
        this.photos = photos;
    }

    // Getters and Setters
    public List<String> getPhotos() { return photos; }
    public void setPhotos(List<String> photos) { this.photos = photos; }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Types getType() {
		return type;
	}

	public void setType(Types type) {
		this.type = type;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Admin getCreatedByAdmin() {
		return createdByAdmin;
	}

	public void setCreatedByAdmin(Admin createdByAdmin) {
		this.createdByAdmin = createdByAdmin;
	}

	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Amenities> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<Amenities> amenities) {
		this.amenities = amenities;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
}
