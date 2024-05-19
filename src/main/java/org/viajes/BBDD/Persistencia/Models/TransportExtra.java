package org.viajes.BBDD.Persistencia.Models;

public class TransportExtra {
	private Integer id;
	private Integer transportId;
	private Integer extraId;
	
	public TransportExtra(Integer id, Integer transportId, Integer extraId) {
		super();
		this.id = id;
		this.transportId = transportId;
		this.extraId = extraId;
	}
	
	public TransportExtra() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTransportId() {
		return transportId;
	}

	public void setTransportId(Integer transportId) {
		this.transportId = transportId;
	}

	public Integer getExtraId() {
		return extraId;
	}

	public void setExtraId(Integer extraId) {
		this.extraId = extraId;
	}
	
}
