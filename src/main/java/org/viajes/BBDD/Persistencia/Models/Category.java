package persistance.models;

public class Category {
	private Integer id;
	private String name;
	private double increment;
	
	public Category(Integer id, String name, double increment) {
		this.setId(id);
		this.setName(name);
		this.setIncrement(increment);
	}
	public Category() {
		super();
	}
	
	public double getIncrement() {
		return increment;
	}

	public void setIncrement(double increment) {
		this.increment = increment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
