package persistence;

import java.util.ArrayList;

import model.User;

public class DBManager {
	static private DBManager instance;
	private ArrayList<User> users;
	
	private DBManager()
	{
		users = new ArrayList<User>();
		ArrayList<User> amici = new ArrayList<User>();
		User u1 = new User("Luigi", "4562", "Sono Luigi montaleone e vengo da Gabella", null, "luigi@luigi");
		User u2 = new User("Prova1", "Prova", "Sono Luigi montaleone e vengo da Gabella", null, "luigi@luigi");
		User u3 = new User("Prova2", "4562", "Sono Luigi montaleone e vengo da Gabella", null, "luigi@luigi");
		User u4 = new User("Prova3", "4562", "Sono Luigi montaleone e vengo da Gabella", null, "luigi@luigi");
		User u5 = new User("Prova4", "4562", "Sono Luigi montaleone e vengo da Gabella", null, "luigi@luigi");
		amici.add(u1);
		amici.add(u2);
		amici.add(u3);
		amici.add(u4);
		User us = new User("Simone", "1234", "Sono SImone mungari e vengo da Crotone", amici, "simone@simone");


		addUser(us);
		addUser(u1);
		addUser(u2);
		addUser(u3);
		addUser(u4);
		addUser(u5);
	}
	
	static public DBManager getInstance()
	{
		if(instance == null)
			instance = new DBManager();
		return instance;
	}
	
	public void addUser(User u)
	{
		users.add(u);
	}
	
	public User getUser(int idUser)
	{
		for(User u: users)
		{
			if(u.getId() == idUser)
			{
				return u;
			}
		}
		return null;
	}

	public User getUser(String username)
	{
		for(User u: users)
		{
			if(u.getUsername().equals(username))
			{
				return u;
			}
		}
		return null;
	}

}
