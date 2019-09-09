package fuelMC.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Proxy;

@Entity
public class Fueling {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fueling_id")
    private Long id;
	private Date date;
	private Long amount;
	private String location;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="origin_ID")
	private FuelTank origin;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="destination_ID")
	private FuelTank destination;
	
	public Fueling(){}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public FuelTank getOrigin() {
		return origin;
	}
	public void setOrigin(FuelTank origin) {
		this.origin = origin;
	}
	public FuelTank getDestination() {
		return destination;
	}
	public void setDestination(FuelTank destination) {
		this.destination = destination;
	}
}
