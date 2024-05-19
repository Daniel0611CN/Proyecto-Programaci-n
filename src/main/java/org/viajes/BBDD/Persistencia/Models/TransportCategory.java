package org.viajes.BBDD.Persistencia.Models;

public class TransportCategory {
	private Integer id;
	private Integer transportId;
	private Integer categoryId;
	
	public TransportCategory(Integer id, Integer transportId, Integer categoryId) {
		super();
		this.id = id;
		this.transportId = transportId;
		this.categoryId = categoryId;
	}
	
	public TransportCategory() {
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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

}
