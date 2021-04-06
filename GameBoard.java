/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Board game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

import java.lang.*;
import java.util.*;

public class GameBoard {
  //Attributes
  private List<Player> players;
  private Random randPlayer;
  private Random randColor;

  //Constructor
  public GameBoard()
  {
    randPlayer = new Random();
    randColor = new Random();
    players = new ArrayList<Player>(4);
  }

  //Methods
  private int whoStarts()
  {
    int max = 0;
    List<Player> rolledSame = new ArrayList<Player>();
    int iPlayFirst = -1;

    //Letting each player roll the dice once
    for (int i = 0;i<4;++i)
    {
      int tmp = players.get(i).rollDice(); //Il faudrait ajouter une méthode pour que le joueur click sur le dé
      if(tmp > max)
      {
        max = tmp;
        rolledSame.clear();
        rolledSame.add(players.get(i));
        iPlayFirst = i; //Assign the player's index who rolled the current max
      }
      else if (tmp == max) {rolledSame.add(players.get(i));}
    }

    //Looping if several players rolled the same number
    boolean same = true;
    while(same)
    {
      max = 0;
      if(rolledSame.size()>1)
      {
        for(Player p : rolledSame)
        {
          int tmp = p.rollDice();
          if(tmp >= max)
          {
            max = tmp;
            iPlayFirst = players.indexOf(p);
          }
          else {rolledSame.remove(p);}
        }
      } else same = false;
    }

    return iPlayFirst;
  }

  /***************************************************/

  public void assignPawnsColor()
  {
    Set<Integer> colorsSet = new HashSet<Integer>(4);
    Set<Integer> playerSet = new HashSet<Integer>(4);
    Color [] arr = Color.values();
    boolean dispensed = false;

    while(!dispensed)
    {
      if(colorsSet.size() != 4)
      {
          int c = this.randColor.nextInt(4);
          int p = this.randPlayer.nextInt(4);
          if(!colorsSet.contains(c) && !playerSet.contains(p))
          {
            this.players.get(p).setColor(arr[c]);
            colorsSet.add(c);
            playerSet.add(p);
         }
      } else dispensed = true;
    }
  }

  /***************************************************/

  private void resetGame()
  {

  }

  /***************************************************/

  private void setUpPlayers()
  {
    this.players.add(new Player("jean"));
    this.players.add(new Player("david"));
    this.players.add(new Player("lucien"));
    this.players.add(new Player("paul"));
  }

  /***************************************************/

  private void setUpGame()
  {
    setUpPlayers();
    //Set up graphical interface
  }

  /***************************************************/

  public void runGame()
  {
    setUpGame();

    assignPawnsColor();
    //for(Player p : this.players) System.out.println(p.getColor());
    int playerIndex = whoStarts();
    //System.out.println(playerIndex);

    //Let players roll dice : movePawn method...

  }

  /***************************************************/

  private List<Player> getPlayers(){return this.players;}

  /***************************************************/


  //Tests
  /*public static void main (String [] arg)
  {
    GameBoard g = new GameBoard();
    g.getPlayers().add(new Player("jean"));
    g.getPlayers().add(new Player("david"));
    g.getPlayers().add(new Player("lucien"));
    g.getPlayers().add(new Player("paul"));
    g.assignPawnsColor();
    for(Player p : g.getPlayers()) System.out.println(p.getColor());
  }*/


}
