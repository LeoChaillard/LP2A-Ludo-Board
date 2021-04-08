/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Board game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

import java.lang.*;
import java.util.*;
import javax.swing.JLabel;

public class Game {
  //Attributes
  private List<Player> players;
  private Random randPlayer;
  private Random randColor;
  private GameWindow window;
  private boolean running;

  //Constructor
  public Game()
  {
    randPlayer = new Random();
    randColor = new Random();
    players = new ArrayList<Player>(4);
    window = new GameWindow();
    running = false;
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
      int tmp = players.get(i).rollDice();
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
        for(int i=0;i<rolledSame.size();++i)
        {
          int tmp = rolledSame.get(i).rollDice();
          if(tmp >= max)
          {
            max = tmp;
            iPlayFirst = players.indexOf(rolledSame.get(i));
          }
          else {rolledSame.remove(i);}
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

  private void setUpPlayers()
  {
    this.players.add(new Player("Joueur1"));
    this.players.add(new Player("Joueur2"));
    this.players.add(new Player("Joueur3"));
    this.players.add(new Player("Joueur4"));
  }

  /***************************************************/

  private void setUpGame()
  {
    setUpPlayers();
    assignPawnsColor();
    //Set up graphical interface
    this.window.initWindow();
    this.window.draw();
    this.running = true;
  }

  /***************************************************/

  public void runGame()
  {
    setUpGame();

    int playerIndex = whoStarts();

    while(running)
    {
      this.players.get(playerIndex).play();
      if(this.players.get(playerIndex).checkWin()) running = false;

      //Mettre à jour indice du Joueur


      running = false; //En attendant pour eviter boucle infini
    }
  }

  /***************************************************/

  private void resetGame()
  {

  }

}
