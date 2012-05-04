package com.handstandtech.foursquare.shared.model.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Id;

public class FoursquareCheckin {
	@Id
	private String id;

	private Long createdAt;
	private String type;
	private String shout;
	private String timeZone;

	@Embedded
	private User user;

	@Embedded
	private Venue venue;

	// @Embedded
	// private List<Category> categories = new ArrayList<Category>(0);

	// @Embedded
	// private List<Photo> photos = new ArrayList<Photo>(0);

	private Boolean verified;

	@Embedded
	private Stats stats;

	@Embedded
	private Source source;

	// @Embedded
	// private Todos todos;

	public static class Source implements Serializable {
		/**
		 * Default Serialization UID
		 */
		private static final long serialVersionUID = 1L;
		private String name;
		private String url;

		public Source() {
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

	}

	public static class Photo implements Serializable {
		/**
		 * Default Serialization UID
		 */
		private static final long serialVersionUID = 1L;

		public Photo() {
		}
	}

	public static class Category implements Serializable {
		/**
		 * Default Serialization UID
		 */
		private static final long serialVersionUID = 1L;
		private String id;
		private String name;
		private String icon;
		private List<String> parents = new ArrayList<String>(0);
		private Boolean primary;
		private String gender;
		private String homeCity;
		private String relationship;

		public Category() {
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		public List<String> getParents() {
			return parents;
		}

		public void setParents(List<String> parents) {
			this.parents = parents;
		}

		public Boolean getPrimary() {
			return primary;
		}

		public void setPrimary(Boolean primary) {
			this.primary = primary;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getHomeCity() {
			return homeCity;
		}

		public void setHomeCity(String homeCity) {
			this.homeCity = homeCity;
		}

		public String getRelationship() {
			return relationship;
		}

		public void setRelationship(String relationship) {
			this.relationship = relationship;
		}

	}

	public static class Stats implements Serializable {
		/**
		 * Default Serialization UID
		 */
		private static final long serialVersionUID = 1L;
		private Integer checkinsCount;
		private Integer usersCount;

		public Stats() {
		}

		public Integer getCheckinsCount() {
			return checkinsCount;
		}

		public void setCheckinsCount(Integer checkinsCount) {
			this.checkinsCount = checkinsCount;
		}

		public Integer getUsersCount() {
			return usersCount;
		}

		public void setUsersCount(Integer usersCount) {
			this.usersCount = usersCount;
		}
	}

	public static class Todos implements Serializable {
		/**
		 * Default Serialization UID
		 */
		private static final long serialVersionUID = 1L;

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

		private Integer count;

		public Todos() {
		}
	}

	public static class User implements Serializable {
		/**
		 * Default Serialization UID
		 */
		private static final long serialVersionUID = 1L;
		private String id;
		private String firstName;
		private String lastName;
		private String photo;
		private String gender;
		private String homeCity;
		private String relationship;

		public User() {
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getPhoto() {
			return photo;
		}

		public void setPhoto(String photo) {
			this.photo = photo;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getHomeCity() {
			return homeCity;
		}

		public void setHomeCity(String homeCity) {
			this.homeCity = homeCity;
		}

		public String getRelationship() {
			return relationship;
		}

		public void setRelationship(String relationship) {
			this.relationship = relationship;
		}

	}

	public static class Venue implements Serializable {
		/**
		 * Default Serialization UID
		 */
		private static final long serialVersionUID = 1L;
		private String id;
		private String name;
		@Embedded
		private Contact contact;
		@Embedded
		private Location location;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Contact getContact() {
			return contact;
		}

		public void setContact(Contact contact) {
			this.contact = contact;
		}

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

		public static class Location implements Serializable {

			/**
			 * Default Serialization UID
			 */
			private static final long serialVersionUID = 1L;
			private String address;
			private String crossStreet;
			private String city;
			private String state;
			private String postalCode;
			private Double lat;
			private Double lng;

			public Location() {
			}

			public String getAddress() {
				return address;
			}

			public void setAddress(String address) {
				this.address = address;
			}

			public String getCrossStreet() {
				return crossStreet;
			}

			public void setCrossStreet(String crossStreet) {
				this.crossStreet = crossStreet;
			}

			public String getCity() {
				return city;
			}

			public void setCity(String city) {
				this.city = city;
			}

			public String getState() {
				return state;
			}

			public void setState(String state) {
				this.state = state;
			}

			public String getPostalCode() {
				return postalCode;
			}

			public void setPostalCode(String postalCode) {
				this.postalCode = postalCode;
			}

			public Double getLat() {
				return lat;
			}

			public void setLat(Double lat) {
				this.lat = lat;
			}

			public Double getLng() {
				return lng;
			}

			public void setLng(Double lng) {
				this.lng = lng;
			}
		}

		public static class Contact implements Serializable {
			/**
			 * Default Serialization UID
			 */
			private static final long serialVersionUID = 1L;

			public Contact() {

			}
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Double getLatitude() {
		if (venue != null) {
			return venue.location.lat;
		} else {
			return null;
		}
	}

	public Double getLongitude() {
		if (venue != null) {
			return venue.location.lng;
		} else {
			return null;
		}
	}

	public Date getDate() {
		if (createdAt != null) {
			return new Date(createdAt * 1000);
		}
		return null;
	}

	public String getText() {
		StringBuffer sb = new StringBuffer();

		if (type.equals("checkin")) {
			if (venue != null) {
				sb.append("Checked in at " + venue.name);
				if (shout != null) {
					sb.append(" and Shouted " + shout);
				}
			}
		} else if (type.equals("shout")) {
			sb.append(shout);
		} else {
			sb.append("Unhandled Type: " + type);
		}

		return sb.toString();
	}

	public void setShout(String shout) {
		this.shout = shout;
	}

	public String getShout() {
		return shout;
	}

	public String getActorId() {
		return user.getId();
	}

	public String getUserIconUrl() {
		return user.getPhoto();
	}

	public String getUserProfileLink() {
		return "http://foursquare.com/user/" + getActorId();
	}

	public String getActorName() {
		StringBuffer sb = new StringBuffer();
		if (user != null) {
			String firstName = user.getFirstName();
			if (firstName != null && !firstName.isEmpty()) {
				sb.append(firstName);
			}

			String lastName = user.getLastName();
			if (lastName != null && !lastName.isEmpty()) {
				if (firstName != null && !firstName.isEmpty()) {
					sb.append(" ");
				}
				sb.append(lastName);
			}
		}
		return sb.toString();
	}

	public String getPermalink() {
		return "https://foursquare.com/user/" + getUser().getId() + "/checkin/" + id;
	}
}
