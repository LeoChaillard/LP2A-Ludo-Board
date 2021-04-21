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
  private static List<Player> players = new ArrayList<Player>(4);
  private Random randPlayer;
  private Random randColor;
  private GameWindow window;
  private int diceResult;
  private int playerIndex;
  private Menu menu;


  public static final Color[] darkColors = {
          new Color(0x9acd00),
          new Color(0xffcd00),
          new Color(0x2fcdcd),
          new Color(0xff3000)
  };

  private enum Actions {
    ROLL,
    MENU,
    NEWGAME,
    RESUME
  }

  //Constructor
  public Game()
  {
    randPlayer = new Random();
    randColor = new Random();
    window = new GameWindow();
    this.diceResult = 0;
    this.menu = new Menu();
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

    if(this.players.get(playerIndex).canPlay(this.diceResult) && !movablePawnsBlocked())
    {
      for(int pawnIndex : this.players.get(playerIndex).getMovablePawns())
      {
        Pawn p = this.players.get(playerIndex).getPawns().get(pawnIndex);

        if( (p.getX() - 11 <= x) && (p.getX() + 22 >= x)&& (p.getY() + 14 <= y)&& (p.getY() + 44 >= y) )
        {

          this.players.get(playerIndex).movePawn(pawnIndex,diceResult);
          this.checkEatenPawns(this.players.get(playerIndex).getMapPosition().get(p.getSquare()));
          this.window.getRightPanel().updateInfos(this.players);
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
  @Override
  public void actionPerformed(ActionEvent evt)
  {
    //Roll dice action
    if (evt.getActionCommand() == Actions.ROLL.name())
    {
      this.diceResult = this.players.get(playerIndex).rollDice();
      this.window.updateRollText(diceResult,this.players.get(playerIndex).getColor());
      this.window.updatePlaying(this.players.get(playerIndex).getName(),this.players.get(playerIndex).getColor());

      this.window.repaint();
      if(this.players.get(playerIndex).canPlay(diceResult) && !movablePawnsBlocked()) this.window.getRoll().setEnabled(false);
      else
      {
        playerIndex = (playerIndex + 1) % 4;
        diceResult = 0;
      }
    }
    else if(evt.getActionCommand() == Actions.MENU.name())
    {
      this.menu.setVisible(true);
      this.window.setEnabled(false);
    }
    else if (evt.getActionCommand() == Actions.RESUME.name())
    {
      this.menu.setVisible(false);
      this.window.setEnabled(true);
    }
    else if (evt.getActionCommand() == Actions.NEWGAME.name())
    {
      resetGame();
      this.menu.setVisible(false);
      this.window.setEnabled(true);
    }

  }


  /***************************************************/

  public static List<Player> getPlayers(){return players;}

  /***************************************************/
  private void setUpGame()
  {
    setUpPlayers();
    assignPawnsColor();
    setUpMapping();

  }

  /***************************************************/

  private void initWindow()
  {
    this.menu.initMenu();
    this.window.initWindow();
   this.window.getRoll().addActionListener(this);
   this.window.getMenu().addActionListener(this);
   this.menu.getResume().addActionListener(this);
   this.menu.getNewGame().addActionListener(this);
   this.menu.getResume().setActionCommand(Actions.RESUME.name());
   this.menu.getNewGame().setActionCommand(Actions.NEWGAME.name());
    this.window.getRoll().setActionCommand(Actions.ROLL.name());
    this.window.getMenu().setActionCommand(Actions.MENU.name());
    this.window.addMouseListener(this);
    this.window.draw();

  }
  /***************************************************/

  private void setUpPlayers()
  {
    for(int i = 0;i<4;++i) this.players.add(new Player("Player"+(i+1)));
  }

  /***************************************************/

  private void setUpMapping()
  {
    for(Player p : this.players)
    {
      if(p.getColor() == darkColors[0]) p.setMap( (new FourthPlayerPositions()).getMap() );
      else if(p.getColor() == darkColors[1]) p.setMap( (new SecondPlayerPositions()).getMap() );
      else if(p.getColor() == darkColors[2]) p.setMap( (new FirstPlayerPositions()).getMap() );
      else p.setMap( (new ThirdPlayerPositions()).getMap() );
    }
  }

  /***************************************************/

  public void runGame()
  {
    setUpGame();
    initWindow();
    playerIndex = whoStarts();
    this.window.getRightPanel().updateInfos(this.players);
  }

  /***************************************************/

  private void resetGame()
  {
    this.players = null;
    this.players = new ArrayList<Player>(4);


    setUpGame();
    this.window.resetWindow();
    this.window.repaint();
    this.window.getRightPanel().updateInfos(this.players);
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

    boolean dispensed = false;
    while(!dispensed)
    {
      if(colorsSet.size() != 4)
      {
          int c = this.randColor.nextInt(4);
          int p = this.randPlayer.nextInt(4);
          if(!colorsSet.contains(c) && !playerSet.contains(p))
          {
            this.players.get(p).setColor(darkColors[c]);
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
        if(i != playerIndex)
        {
          for(int k = currentSquare+1;k<=finalSquare;++k)
          {
            Vector pos = this.players.get(playerIndex).getMapPosition().get(k);
            for(int l = 0;l<4;++l)
            {
              if(this.players.get(i).getPawns().get(l).getBlock() && this.players.get(i).getPawns().get(l).getPosition().compare(pos))
              {
                block = true;
                break;
              }
            }
            if(block) break;
          }
          if(block) break;
        }
      }
      if(!block) break;
    }


    return block;
  }

  /***************************************************/

  private void checkEatenPawns(Vector pos)
  {
    for(int i =0;i<4;++i)
    {
      for(int j =0;j<4;++j)
      {
        if(this.players.get(i).getPawns().get(j).getSquare() != -1 && i != playerIndex)
        {
          if(this.players.get(i).getMapPosition().get(this.players.get(i).getPawns().get(j).getSquare()).compare(pos) && !this.players.get(i).getPawns().get(j).isOnSafeZone())
          {
            this.players.get(i).getPawns().get(j).backStartingBlock();
          }
        }

      }

    }
  }


}
