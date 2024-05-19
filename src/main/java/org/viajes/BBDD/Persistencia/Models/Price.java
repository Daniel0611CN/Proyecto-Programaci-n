package org.viajes.BBDD.Persistencia.Models;

public class Price {
	private Integer id;
	private Integer departureLocationId;
	private Integer arrivalLocationId;
	private double planePrice;
	private double trainPrice;
	private double boatPrice;
	

	public Price(Integer id, Integer departureLocationId, Integer arrivalLocationId, double planePrice,
			double trainPrice, double boatPrice) {
		super();
		this.id = id;
		this.departureLocationId = departureLocationId;
		this.arrivalLocationId = arrivalLocationId;
		this.planePrice = planePrice;
		this.trainPrice = trainPrice;
		this.boatPrice = boatPrice;
	}

	public Price() {
		super();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public double getPlanePrice() {
		return planePrice;
	}

	public void setPlanePrice(double planePrice) {
		this.planePrice = planePrice;
	}

	public double getTrainPrice() {
		return trainPrice;
	}

	public void setTrainPrice(double trainPrice) {
		this.trainPrice = trainPrice;
	}

	public double getBoatPrice() {
		return boatPrice;
	}

	public void setBoatPrice(double boatPrice) {
		this.boatPrice = boatPrice;
	}

	public Integer getDepartureLocationId() {
		return departureLocationId;
	}

	public void setDepartureLocationId(Integer departureLocationId) {
		this.departureLocationId = departureLocationId;
	}

	public Integer getArrivalLocationId() {
		return arrivalLocationId;
	}

	public void setArrivalLocationId(Integer arrivalLocationId) {
		this.arrivalLocationId = arrivalLocationId;
	}
}
