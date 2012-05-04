package com.handstandtech.foursquare.shared.model.v2;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

public class FoursquareUser implements Serializable {

	/**
	 * Default Serialization UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String photo;
	private String gender;
	private String homeCity;
	private String type;
	private Date lastUpdate;

	@Transient
	private String relationship;

	@Embedded
	private Contact contact;

	@Embedded
	private Badges badges;

	@Embedded
	private Mayorships mayorships;

	@Embedded
	private Friends friends;

	@Embedded
	private Followers followers;

	@Embedded
	private Tips tips;

	@Embedded
	private Todos todos;

	public FoursquareUser() {

	}

	public static class Contact implements Serializable {

		/**
		 * Default Serialization UID
		 */
		private static final long serialVersionUID = 1L;

		private String phone;
		private String email;

		private String twitter;
		private String facebook;

		public Contact() {

		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getTwitter() {
			return twitter;
		}

		public void setTwitter(String twitter) {
			this.twitter = twitter;
		}

		public String getFacebook() {
			return facebook;
		}

		public void setFacebook(String facebook) {
			this.facebook = facebook;
		}
	}

	public static class Badges implements Serializable {

		/**
		 * Default Serialization UID
		 */
		private static final long serialVersionUID = 1L;

		private Long count;

		public Badges() {

		}

		public Long getCount() {
			return count;
		}

		public void setCount(Long count) {
			this.count = count;
		}
	}

	public static class Mayorships implements Serializable {

		/**
		 * Default Serialization UID
		 */
		private static final long serialVersionUID = 1L;
		private Long count;

		public Mayorships() {

		}

		public Long getCount() {
			return count;
		}

		public void setCount(Long count) {
			this.count = count;
		}
	}

	public static class Tips implements Serializable {

		/**
		 * Default Serialization UID
		 */
		private static final long serialVersionUID = 1L;
		private Long count;

		public Tips() {

		}

		public Long getCount() {
			return count;
		}

		public void setCount(Long count) {
			this.count = count;
		}
	}

	public static class Friends implements Serializable {

		/**
		 * Default Serialization UID
		 */
		private static final long serialVersionUID = 1L;

		private Long count;

		public Friends() {

		}

		public Long getCount() {
			return count;
		}

		public void setCount(Long count) {
			this.count = count;
		}
	}

	public static class Followers implements Serializable {

		/**
		 * Default Serialization UID
		 */
		private static final long serialVersionUID = 1L;

		private Long count;

		public Followers() {

		}

		public Long getCount() {
			return count;
		}

		public void setCount(Long count) {
			this.count = count;
		}
	}

	public static class Todos implements Serializable {

		/**
		 * Default Serialization UID
		 */
		private static final long serialVersionUID = 1L;

		private Long count;

		public Todos() {

		}

		public Long getCount() {
			return count;
		}

		public void setCount(Long count) {
			this.count = count;
		}
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

	public String getName() {
		String first = getFirstName();
		String last = getLastName();
		if (last == null) {
			return first;
		} else {
			return first + " " + last;
		}
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public FoursquareUser.Contact getContact() {
		return contact;
	}

	public void setContact(FoursquareUser.Contact contact) {
		this.contact = contact;
	}

	public FoursquareUser.Badges getBadges() {
		return badges;
	}

	public void setBadges(FoursquareUser.Badges badges) {
		this.badges = badges;
	}

	public FoursquareUser.Mayorships getMayorships() {
		return mayorships;
	}

	public void setMayorships(FoursquareUser.Mayorships mayorships) {
		this.mayorships = mayorships;
	}

	@SuppressWarnings("unused")
	@PrePersist
	private void onPersist() {
		setLastUpdate(new Date());
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setFriends(Friends friends) {
		this.friends = friends;
	}

	public Friends getFriends() {
		return friends;
	}

	public void setTips(Tips tips) {
		this.tips = tips;
	}

	public Tips getTips() {
		return tips;
	}

	public void setTodos(Todos todos) {
		this.todos = todos;
	}

	public Todos getTodos() {
		return todos;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setFollowers(Followers followers) {
		this.followers = followers;
	}

	public Followers getFollowers() {
		return followers;
	}

	@Override
	public String toString() {
		return getName() + " (" + id.toString() + ")";
	}

}
