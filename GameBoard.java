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
  private Random rand;

  //Constructor
  public GameBoard()
  {
    rand = new Random();
    players = new ArrayList<Player>(4);
  }

  //Methods
  private int whoStarts()
  {
    int max = 0, index = 0;
    List<Player> rolledSame = new ArrayList<Player>();
    int iPlayFirst = -1;

    //Letting each player roll the dice once
    for (int i = 0;i<4;++i)
    {
      int tmp = players.get(i).rollDice();
      if(tmp > max)
      {
        index = i;
        max = tmp;
        rolledSame.clear();
        rolledSame.add(players.get(i));
      }
      else if (tmp == max) {rolledSame.add(players.get(i));}
    }

    //Looping if several players rolled the same number
    while(rolledSame.size() > 1)
    {
      max = 0;
      for(Player p : rolledSame)
      {
        int tmp = p.rollDice();
        if(tmp >= max)
        {
          max = tmp;
          iPlayFirst = players.indexOf(p);
        }
        else
        {
          rolledSame.remove(p);
        }
      }
    }

    return iPlayFirst;
  }

  /***************************************************/

  public void assignPawnsColor()
  {
    Set<Integer> colorArray = new HashSet<Integer>();
    Color [] arr = Color.values();
    Player p = players.get(0);
    while(colorArray.size() != 4)
    {
      int n = this.rand.nextInt(4);
      if(!colorArray.contains(n))
      {
         this.players.get(n).setColor(arr[n]);
         colorArray.add(n);
       }
    }

  }

  /***************************************************/

  private void resetGame()
  {

  }

  /***************************************************/

  private void setUpGame()
  {
    //Set up graphical interface
  }

  /***************************************************/

  public void runGame()
  {
    setUpGame();

    assignPawnsColor();
    int playerIndex = whoStarts();

    //Let players roll dice : movePawn method

  }

  /***************************************************/

  private List<Player> getPlayers(){return this.players;}

  /***************************************************/


  //Tests
  public static void main (String [] arg)
  {
    GameBoard g = new GameBoard();
    g.getPlayers().add(new Player("jean"));
    g.getPlayers().add(new Player("david"));
    g.getPlayers().add(new Player("lucien"));
    g.getPlayers().add(new Player("paul"));
    g.assignPawnsColor();
    for(Player p : g.getPlayers()) System.out.println(p.getColor());
  }


}
