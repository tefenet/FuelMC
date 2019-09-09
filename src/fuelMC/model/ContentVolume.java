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
public class ContentVolume {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContentVolume_id")
	private Long id;
	private Long amount;
	private Date timestamp;
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="fuelTank_ID")
	private FuelTank tank;
	
	
	public ContentVolume() {
		// TODO Auto-generated constructor stub
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Date getTimeStamp() {
		return timestamp;
	}
	public void setTimeStamp(Date date) {
		this.timestamp = date;
	}
	public FuelTank getTank() {
		return tank;
	}
	public void setTank(FuelTank tank) {
		this.tank = tank;
	}

}
