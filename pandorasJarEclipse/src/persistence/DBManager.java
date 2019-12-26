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
		amici.add(new User("Luigi", "4562", "Sono Luigi montaleone e vengo da Gabella", null, "luigi@luigi"));
		User us = new User("Simone", "1234", "Sono SImone mungari e vengo da Crotone", amici, "simone@simone");
		addUser(us);	
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

}
