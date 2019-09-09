package fuelMC.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class FuelTank {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fuelTank_id")
    private Long id;
    private String name;
    private Long capacity;
    private Long content;
    @OneToMany(mappedBy = "origin")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Fueling> fuelingsDone;
	@OneToMany(mappedBy = "destination")
	@LazyCollection(LazyCollectionOption.FALSE)
    private Set<Fueling> fuelingsReceived;
    @OneToMany(mappedBy = "tank")	
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<ContentVolume> history;
    @OneToOne(mappedBy="tank")
    private Vehicle vehicle;
    
    public FuelTank() {}
    
    public String getName() {
        return this.name;
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getCapacity() {
		return capacity;
	}


	public void setCapacity(Long capacity) {
		this.capacity = capacity;
	}


	public Long getContent() {
		return content;
	}


	public void setContent(Long content) {
		this.content = content;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Fueling> getFuelingsDone() {
		return fuelingsDone;
	}

	public Set<Fueling> getFuelingsReceived() {
		return fuelingsReceived;
	}

	public Set<ContentVolume> getHistory() {
		return history;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}
}
