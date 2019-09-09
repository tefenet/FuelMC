package fuelMC.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Vehicle {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fuelTank_ID")
    private Long id;
	private String plate;
	private String brand;
	private String model;
	private String location;
	@OneToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="fuelTank_ID")
	private FuelTank tank;
	
	
	public Vehicle() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String position) {
		this.location = position;
	}
	public FuelTank getTank() {
		return tank;
	}
	public void setTank(FuelTank tank) {
		this.tank = tank;
	}
}
