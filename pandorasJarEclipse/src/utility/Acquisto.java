package utility;

import model.Game;

import java.util.Date;

public class Acquisto
{
    private Game game;
    private Date dataAcquisto;
    public Acquisto(Game game, Date dataAcquisto)
    {
        this.game = game;
        this.dataAcquisto = dataAcquisto;
    }

    public Game getGame()
    {
        return game;
    }
    public Date getDataAcquisto()
    {
        return dataAcquisto;
    }
}

