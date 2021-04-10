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
    PLAY,
    MOVE
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
      this.window.getRoll().setEnabled(false);
      this.window.getMove().setEnabled(true);
      this.diceResult = this.players.get(playerIndex).rollDice();
      this.window.updateRollText(diceResult);
      this.window.repaint();
    }

    //Move action
    else if (evt.getActionCommand() == Actions.MOVE.name())
    {
      this.displayPawnStatus(playerIndex);
      if(this.players.get(playerIndex).canPlay(diceResult) && !movablePawnsBlocked())
      {
        String [] options = {"1", "2", "3", "4"};
        int result = JOptionPane.showOptionDialog(this.window,"Which pawn do want to move ?","Move a pawn",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if(result != -1 && this.players.get(playerIndex).canMovePawn(result,diceResult)){
          JOptionPane.showMessageDialog(this.window, "You moved pawn" + (result+1));

          this.players.get(playerIndex).movePawn(result,diceResult);
          this.displayPawnStatus(playerIndex);
          this.checkEatenPawns(this.players.get(playerIndex).getPawns().get(result).getPosition());

          if(diceResult == 6) this.window.getRoll().setEnabled(true);
          else this.window.getNextPlay().setEnabled(true);
          this.window.getMove().setEnabled(false);
        }
        else JOptionPane.showMessageDialog(this.window, "Wrong index!");
      }
      else
      {
        JOptionPane.showMessageDialog(this.window, "You can't make any legal move :(");
        this.window.getNextPlay().setEnabled(true);
      }
    }

    //Next player action
    else if (evt.getActionCommand() == Actions.PLAY.name())
    {
      this.window.getMove().setEnabled(false);
      this.window.getNextPlay().setEnabled(false);
      this.window.getRoll().setEnabled(true);

      if(playerIndex == 3) playerIndex = 0;
      else playerIndex++;

      displayPawnStatus(playerIndex);
      displayCurrentPlayer(playerIndex);
    }

    if(this.players.get(playerIndex).checkWin())
    {
      this.window.getMove().setEnabled(false);
      this.window.getNextPlay().setEnabled(false);
      this.window.getRoll().setEnabled(false);

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
    assignPawnsColor();

    //Set up graphical interface
    this.window.initWindow();
    this.window.getRoll().addActionListener(this); this.window.getNextPlay().addActionListener(this); this.window.getMove().addActionListener(this);
    this.window.getNextPlay().setActionCommand(Actions.PLAY.name()); this.window.getRoll().setActionCommand(Actions.ROLL.name()); this.window.getMove().setActionCommand(Actions.MOVE.name());
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

  private void displayPawnStatus(int playerIndex)
  {
    this.window.updatePawn1(this.players.get(playerIndex).getPawns().get(0).getSquare());
    this.window.updatePawn2(this.players.get(playerIndex).getPawns().get(1).getSquare());
    this.window.updatePawn3(this.players.get(playerIndex).getPawns().get(2).getSquare());
    this.window.updatePawn4(this.players.get(playerIndex).getPawns().get(3).getSquare());
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
