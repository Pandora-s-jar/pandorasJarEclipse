package persistence;

import java.util.ArrayList;
import java.util.HashMap;

import model.Data;
import model.Game;
import model.User;
import utility.Acquisto;

public class DBManager {
	static private DBManager instance;
	private ArrayList<User> users;
	
	private DBManager()
	{
		users = new ArrayList<User>();
		ArrayList<User> amici = new ArrayList<User>();

		User u1 = new User("Luigi", "4562", "Sono Luigi montaleone e vengo da Gabella", null, "luigi@luigi", null);
		User u2 = new User("Prova1", "Prova", "Sono Luigi montaleone e vengo da Gabella", null, "luigi@luigi", null);
		User u3 = new User("Prova2", "4562", "Sono Luigi montaleone e vengo da Gabella", null, "luigi@luigi", null);
		User u4 = new User("Prova3", "4562", "Sono Luigi montaleone e vengo da Gabella", null, "luigi@luigi", null);
		User u5 = new User("Prova4", "4562", "Sono Luigi montaleone e vengo da Gabella", null, "luigi@luigi", null);
		amici.add(u1);
		amici.add(u2);
		amici.add(u3);
		amici.add(u4);
		User us = new User("Simone", "1234", "Sono SImone mungari e vengo da Crotone", amici, "simone@simone", null);

		ArrayList<Game> library = new ArrayList<Game>();

		HashMap<String, Float> hoursPlayedG1 = new HashMap<String,Float>();
		hoursPlayedG1.put("2018", (float)121.1);
		hoursPlayedG1.put("2019", (float)10.25);
		hoursPlayedG1.put("2020", (float)0);
		HashMap<Integer, Float> rankingG1 = new HashMap<Integer, Float>();
		rankingG1.put(u1.getId(), (float)1000.5);
		rankingG1.put(u2.getId(), (float)1500.5);
		rankingG1.put(us.getId(), (float)2000.5);
		Game pinball = new Game("pinball", 5, new Data("1998", "10", "25"), 69.99, hoursPlayedG1, rankingG1);

		HashMap<String, Float> hoursPlayedG2 = new HashMap<String,Float>();
		hoursPlayedG2.put("2018", (float)11.5);
		hoursPlayedG2.put("2019", (float)0);
		hoursPlayedG2.put("2020", (float)0);
		HashMap<Integer, Float> rankingG2 = new HashMap<Integer, Float>();
		rankingG2.put(u3.getId(), (float)1000.5);
		rankingG2.put(u1.getId(), (float)1500.5);
		rankingG2.put(us.getId(), (float)500.5);
		Game puzzleBubble = new Game("Puzzle Bubble", 5, new Data("1999", "05", "01"), 29.99, hoursPlayedG2, rankingG2);

		ArrayList<Acquisto> soldGames = new ArrayList<>();
		Acquisto a1 = new Acquisto(puzzleBubble, new Data("2019", "04", "25"));
		Acquisto a2 = new Acquisto(pinball, new Data("2019", "06", "24"));
		soldGames.add(a1);
		soldGames.add(a2);
		us.setSoldGames(soldGames);

		library.add(pinball);
		library.add(puzzleBubble);
		us.setLibrary(library);

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
