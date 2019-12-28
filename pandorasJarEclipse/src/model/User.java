package model;

import java.util.ArrayList;

public class User {
	static private int contId = 0;
	private int id;
	private String username;
	private String password;
	private String description;
	private ArrayList<User> friends;
	private String image;
	private String email;
	
	public User(String username, String password, String description, ArrayList<User> friends, String email) {
		id = contId++;
		this.username = username;
		this.password = password;
		this.description = description;
		this.friends = friends;
		this.email = email;
		image = null;
	}

	public static int getContId() {
		return contId;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDescription() {
		return description;
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public String getImage() {
		return image;
	}
	
	public String getEmail() {
		return email;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static void setContId(int contId) {
		User.contId = contId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}
}
