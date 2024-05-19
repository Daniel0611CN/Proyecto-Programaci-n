package org.viajes.BBDD.Persistencia.Models;

public class Transport {
	private Integer id;
	private String name;
	
	public Transport(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Transport() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
