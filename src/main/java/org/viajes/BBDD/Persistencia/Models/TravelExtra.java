package org.viajes.BBDD.Persistencia.Models;

public class TravelExtra {
	private Integer id;
	private Integer extraId;
	private Integer travelId;
	
	public TravelExtra(Integer id, Integer extraId, Integer travelId) {
		super();
		this.id = id;
		this.extraId = extraId;
		this.travelId = travelId;
	}
	
	public TravelExtra() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExtraId() {
		return extraId;
	}

	public void setExtraId(Integer extraId) {
		this.extraId = extraId;
	}

	public Integer getTravelId() {
		return travelId;
	}

	public void setTravelId(Integer travelId) {
		this.travelId = travelId;
	}

}
