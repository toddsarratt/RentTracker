package net.toddsarratt.renttracker.entity;

public class Stb extends Entity {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Stb{" +
				"name='" + name + '\'' +
				", id=" + id +
				'}';
	}
}
