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
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;


public class Game implements ActionListener,MouseListener {
  //Attributes
  private static final List<Player> players = new ArrayList<Player>(4);
  private Random randPlayer;
  private Random randColor;
  private GameWindow window;
  private int diceResult;
  private int playerIndex;

  public static final Color[] pawnColors = {
          new Color(0x208000),
          new Color(0xCC7A00),
          new Color(0x001133),
          new Color(0x990000)
  };

  private enum Actions {
    ROLL,
    RESTART;
  }

  //Constructor
  public Game()
  {
    randPlayer = new Random();
    randColor = new Random();
    window = new GameWindow();
    this.diceResult = 0;
  }

  //Methods
  @Override
  public void mouseReleased(MouseEvent evt){}

  /***************************************************/

  @Override
  public void mouseClicked(MouseEvent evt)
  {
    int x = evt.getX();
    int y = evt.getY();

    /*System.out.println(x);
    System.out.println(this.players.get(playerIndex).getPawns().get(0).getX());*/

    if(this.players.get(playerIndex).canPlay(this.diceResult))
    {
      for(int pawnIndex : this.players.get(playerIndex).getMovablePawns())
      {
        Pawn p = this.players.get(playerIndex).getPawns().get(pawnIndex);
        if( (p.getX() - 11 <= x) && (p.getX() + 22 >= x)&& (p.getY() + 14 <= y)&& (p.getY() + 44 >= y) )
        {
          this.players.get(playerIndex).movePawn(pawnIndex,diceResult);
          if(this.players.get(playerIndex).checkWin())
          {
            String [] options = {"yes", "no"};
            int result = JOptionPane.showOptionDialog(this.window,"Do you want to restart the game?","End of game",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if(result == 0) resetGame();
            else System.exit(0);
          }
          if(diceResult != 6) playerIndex = (playerIndex + 1) % 4;
          diceResult = 0;
          this.window.getRoll().setEnabled(true);
          break;
        }
      }
    }

  this.window.repaint();

  }

  /***************************************************/

  public void mousePressed(MouseEvent evt){}
  public void mouseEntered(MouseEvent evt){}
  public void mouseExited(MouseEvent evt){}


  /***************************************************/

  public void actionPerformed(ActionEvent evt)
  {
    //Roll dice action
    if (evt.getActionCommand() == Actions.ROLL.name())
    {
      this.diceResult = this.players.get(playerIndex).rollDice();
      this.window.updateRollText(this.players.get(playerIndex).getName(),diceResult);
      this.window.repaint();
      if(this.players.get(playerIndex).canPlay(diceResult)) this.window.getRoll().setEnabled(false);
      else
      {
        playerIndex = (playerIndex + 1) % 4;
        diceResult = 0;
      }
    }

    else if(evt.getActionCommand() == Actions.RESTART.name())
    {
      String [] options = {"yes", "no"};
      int result = JOptionPane.showOptionDialog(this.window,"Do you want to restart the game?","End of game",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
      if(result == 0) resetGame();
      else System.exit(0);
    }

  }


  /***************************************************/

  public static List<Player> getPlayers(){return players;}


  /***************************************************/
  private void setUpGame()
  {
    setUpPlayers();
    //assignPawnsColor();

    //Set up graphical interface
    this.window.initWindow();
   this.window.getRoll().addActionListener(this);
   this.window.getRestart().addActionListener(this);
    this.window.getRoll().setActionCommand(Actions.ROLL.name());
    this.window.getRestart().setActionCommand(Actions.RESTART.name());
    this.window.addMouseListener(this);
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
    //displayCurrentPlayer(playerIndex);
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
