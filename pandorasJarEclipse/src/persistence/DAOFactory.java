package persistence;

import model.HoursPlayed;

public class DAOFactory {
    static private DAOFactory instance = null;
    private DAOFactory() {
        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static public DAOFactory getInstance(){
        if(instance == null)
            instance = new DAOFactory();
        return instance;
    }

    public UserDAO makeUserDAO()
    {
        return UserDAO.getInstance();
    }

    public HoursPlayedDAO makeHoursPlayedDAO()
    {
        return HoursPlayedDAO.getInstance();
    }
}
