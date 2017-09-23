package net.toddsarratt.renttracker.entity;

public class Asset extends Entity {
	private String title;
	private String provider;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	@Override
	public String toString() {
		return "Asset{" +
				"title='" + title + '\'' +
				", provider='" + provider + '\'' +
				", id=" + id +
				'}';
	}
}
