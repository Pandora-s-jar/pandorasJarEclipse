package utility;

import model.Data;
import model.Game;

public class Acquisto
{
    private Game game;
    private Data dataAcquisto;
    public Acquisto(Game game, Data dataAcquisto)
    {
        this.game = game;
        this.dataAcquisto = dataAcquisto;
    }

    public Game getGame()
    {
        return game;
    }
    public Data getDataAcquisto()
    {
        return dataAcquisto;
    }
}

