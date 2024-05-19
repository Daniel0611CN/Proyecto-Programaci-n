package persistance.models;

public class Travel {
	private Integer id;
	private String arrivalDate;
	private String departureDate;
	private double price;
	private Integer categoryId;
	private Integer arrivalLocationId;
	private Integer departureLocationId;
	private Integer numberPassengers;
	private Integer userId;
	
	public Travel(Integer id, String arrivalDate, String departureDate, double price, Integer categoryId,
		Integer departureLocationId, Integer arrivalLocationId, Integer numberPassengers, Integer userId) {
		super();
		this.id = id;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.price = price;
		this.categoryId = categoryId;
		this.departureLocationId = departureLocationId;
		this.arrivalLocationId = arrivalLocationId;
		this.numberPassengers = numberPassengers;
		this.userId = userId;
	}
	
	public Travel() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String itemTravel) {
		this.arrivalDate = itemTravel;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getArrivalLocationId() {
		return arrivalLocationId;
	}

	public void setArrivalLocationId(Integer arrivalLocationId) {
		this.arrivalLocationId = arrivalLocationId;
	}

	public Integer getDepartureLocationId() {
		return departureLocationId;
	}

	public void setDepartureLocationId(Integer departureLocationId) {
		this.departureLocationId = departureLocationId;
	}

	public Integer getNumberPassengers() {
		return numberPassengers;
	}

	public void setNumberPassengers(Integer numberPassengers) {
		this.numberPassengers = numberPassengers;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
