/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Board game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

import java.lang.*;
import java.util.*;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;


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
    if (evt.getActionCommand() == Actions.ROLL.name())
    {
      this.window.getRoll().setEnabled(false);
      this.window.getMove().setEnabled(true);
      this.diceResult = this.players.get(playerIndex).rollDice();
      this.window.setRollText(diceResult);
      this.window.repaint();

    }
    else if (evt.getActionCommand() == Actions.MOVE.name())
    {
      displayPawnStatus(playerIndex);
      if(this.players.get(playerIndex).canPlay(diceResult))
      {
        String result = (String)JOptionPane.showInputDialog(this.window,"Which pawn do want to move ?", "Move a pawn",JOptionPane.PLAIN_MESSAGE,null,null, "1,2,3 or 4");
        if(result != null && result.length() > 0 && this.players.get(playerIndex).canMovePawn(Integer.valueOf(result)-1,diceResult)){
          JOptionPane.showMessageDialog(this.window, "You moved pawn" + result);
          this.players.get(playerIndex).movePawn(Integer.valueOf(result)-1,diceResult);
          displayPawnStatus(playerIndex);
          if(diceResult == 6) this.window.getRoll().setEnabled(true);
          else this.window.getPlay().setEnabled(true);
          this.window.getMove().setEnabled(false);

        }else JOptionPane.showMessageDialog(this.window, "Wrong index!");
      } else
      {
        JOptionPane.showMessageDialog(this.window, "You can't make any legal move :(");
        this.window.getPlay().setEnabled(true);
      }
    }
    else if (evt.getActionCommand() == Actions.PLAY.name())
    {
      this.window.getMove().setEnabled(false);
      this.window.getPlay().setEnabled(false);
      this.window.getRoll().setEnabled(true);
      if(playerIndex == 3) playerIndex = 0;
      else playerIndex++;

      displayPawnStatus(playerIndex);
      displayCurrentPlayer(playerIndex);
    }

    if(this.players.get(playerIndex).checkWin())
    {
      String result = (String)JOptionPane.showInputDialog(this.window,"Do you want to restart the game?", "End of game",JOptionPane.PLAIN_MESSAGE,null,null, "yes or no");
      if(result.toLowerCase().equals("no")) System.exit(0);
      else if (result.toLowerCase().equals("yes"))resetGame();
    }
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

  private void setUpPlayers()
  {
    this.players.add(new Player("Player1"));
    this.players.add(new Player("Player2"));
    this.players.add(new Player("Player3"));
    this.players.add(new Player("Player4"));
  }

  /***************************************************/

  private void displayCurrentPlayer(int playerIndex)
  {
    this.window.setPlaying(this.players.get(playerIndex).getName());
    this.window.repaint();
  }

  /***************************************************/

  private void displayPawnStatus(int playerIndex)
  {
    this.window.setPawn1(this.players.get(playerIndex).getPawns().get(0).getSquare());
    this.window.setPawn2(this.players.get(playerIndex).getPawns().get(1).getSquare());
    this.window.setPawn3(this.players.get(playerIndex).getPawns().get(2).getSquare());
    this.window.setPawn4(this.players.get(playerIndex).getPawns().get(3).getSquare());
    this.window.repaint();
  }

  /***************************************************/

  private void setUpGame()
  {
    setUpPlayers();
    assignPawnsColor();

    //Set up graphical interface
    this.window.initWindow();
    this.window.getRoll().addActionListener(this); this.window.getPlay().addActionListener(this); this.window.getMove().addActionListener(this);
    this.window.getPlay().setActionCommand(Actions.PLAY.name()); this.window.getRoll().setActionCommand(Actions.ROLL.name()); this.window.getMove().setActionCommand(Actions.MOVE.name());
    this.window.draw();

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

}
