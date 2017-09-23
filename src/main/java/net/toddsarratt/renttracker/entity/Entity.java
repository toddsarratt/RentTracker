package net.toddsarratt.renttracker.entity;

public abstract class Entity {
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Entity{" +
				"id=" + id +
				'}';
	}
}
