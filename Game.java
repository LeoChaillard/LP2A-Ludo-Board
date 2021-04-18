/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Board game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

import java.lang.*;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Game implements ActionListener {
  //Attributes
  private List<Player> players;
  private Random randPlayer;
  private Random randColor;
  private GameWindow window;
  private int diceResult;
  private int playerIndex;

  private enum Actions {
    ROLL,
    RESTART;
  }

  //Constructor
  public Game()
  {
    randPlayer = new Random();
    randColor = new Random();
    players = new ArrayList<Player>(4);
    window = new GameWindow();
  }

  //Methods
  public void actionPerformed(ActionEvent evt)
  {
    //Roll dice action
    if (evt.getActionCommand() == Actions.ROLL.name())
    {
      this.diceResult = this.players.get(playerIndex).rollDice();
      this.window.updateRollText(this.players.get(playerIndex).getName(),diceResult);
      this.window.repaint();
    }

    else if(evt.getActionCommand() == Actions.RESTART.name())
    {
      resetGame();
    }

    playerIndex = (playerIndex + 1) % 4;

    if(this.players.get(playerIndex).checkWin())
    {

      String [] options = {"yes", "no"};
      int result = JOptionPane.showOptionDialog(this.window,"Do you want to restart the game?","End of game",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
      if(result == 0) resetGame();
      else System.exit(0);
   }
  }


  /***************************************************/

  private void setUpGame()
  {
    setUpPlayers();
    //assignPawnsColor();

    //Set up graphical interface
    this.window.initWindow();
    this.window.getRoll().addActionListener(this); this.window.getRestart().addActionListener(this);
    this.window.getRoll().setActionCommand(Actions.ROLL.name()); this.window.getRestart().setActionCommand(Actions.RESTART.name());
    this.window.draw();

  }

  /***************************************************/

  private void setUpPlayers()
  {
    for(int i = 0;i<4;++i) this.players.add(new Player("Player"+(i+1)));
    this.players.get(0).setMap( (new FirstPlayerPositions()).getMap() );
    this.players.get(1).setMap( (new SecondPlayerPositions()).getMap() );
    this.players.get(2).setMap( (new ThirdPlayerPositions()).getMap() );
    this.players.get(3).setMap( (new FourthPlayerPositions()).getMap() );
  }

  /***************************************************/

  public void runGame()
  {
    setUpGame();
    playerIndex = whoStarts();
    displayCurrentPlayer(playerIndex);
  }

  /***************************************************/

  private void resetGame()
  {
    runGame();
  }

  /***************************************************/

  private void displayCurrentPlayer(int playerIndex)
  {
    this.window.updatePlaying(this.players.get(playerIndex).getName());
    this.window.repaint();
  }

  /***************************************************/

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

  /*public void assignPawnsColor()
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
  }*/

  /***************************************************/

  private boolean movablePawnsBlocked()
  {
    boolean block = false;
    for(int j : this.players.get(playerIndex).getMovablePawns())
    {
      int currentSquare = this.players.get(playerIndex).getPawns().get(j).getSquare();
      int finalSquare = currentSquare + diceResult;
      block = false;
      for(int i = 0;i<4;++i)
      {
        for(int k = currentSquare+1;k<=finalSquare;++k)
        {
          for(int l = 0;l<4;++l)
          {
            Vector pos = this.players.get(playerIndex).getMapPosition().get(k);
            if(this.players.get(i).getPawns().get(l).getBlock() && this.players.get(i).getPawns().get(l).getPosition() == pos)
            {
              block = true;
              break;
            }
          }
          if(block) break;
        }
        if(block) break;
      }
      if(!block) break;
    }


    return block;
  }

  /***************************************************/

  public void checkEatenPawns(Vector pos)
  {
    for(int i =0;i<4;++i)
    {
      for(int j =0;j<4;++j)
      {
        if(i != playerIndex && this.players.get(i).getPawns().get(j).getPosition() == pos && !this.players.get(i).getPawns().get(j).isOnSafeZone())
        {
          this.players.get(i).getPawns().get(j).backStartingBlock();
          System.out.println("Pawns"+j + "player"+i+" got eaten");
        }

      }

    }
  }


}
