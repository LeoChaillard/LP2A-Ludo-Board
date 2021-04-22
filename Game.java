/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/
import play.*;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;

/**
 * Class managing the game.
 * It contains a list of Player objects,
 * but also Menu and GameWindow objects.
 */
public class Game implements ActionListener,MouseListener {
  //Attributes
  private static List<Player> players = new ArrayList<Player>(4);
  private Random randPlayer;
  private Random randColor;
  private GameWindow window;
  private Menu menu;
  private int diceResult;
  private int playerIndex;

  private enum Actions {
    ROLL,
    MENU,
    NEWGAME,
    RESUME
  }

  //Constructor
  public Game()
  {
    this.randPlayer = new Random();
    this.randColor = new Random();
    this.window = new GameWindow();
    this.menu = new Menu();
    this.diceResult = 0;
  }

  //Methods
  @Override
  public void mouseClicked(MouseEvent evt)
  {
    int x = evt.getX();
    int y = evt.getY();
    Player player = this.players.get(playerIndex);

    if(player.canPlay(this.diceResult) && !movablePawnsBlocked()) //Player can play and there's at least one pawn that won't meet a block
    {
      for(int pawnIndex : player.getMovablePawns())
      {
        Pawn p = player.getPawns().get(pawnIndex);

        if( (p.getX() - 11 <= x) && (p.getX() + 22 >= x)&& (p.getY() + 14 <= y)&& (p.getY() + 44 >= y) ) //Checking coordinates of the clicked pawn (there's a certain offset)
        {

          player.movePawn(pawnIndex,diceResult);
          this.checkEatenPawns(player.getMapPosition().get(p.getSquare()));
          this.window.getRightPanel().updateInfos(this.players,player.getColor(),player.getName(),this.diceResult);
          this.window.getRoll().setEnabled(true);
          this.checkWin();

          if(diceResult != 6) playerIndex = (playerIndex + 1) % 4;
          diceResult = 0;

          break;
        }
      }
    }
    this.window.repaint();
  }

  /***************************************************/

  @Override
  public void mousePressed(MouseEvent evt){}
  public void mouseEntered(MouseEvent evt){}
  public void mouseExited(MouseEvent evt){}
  public void mouseReleased(MouseEvent evt){}

  /***************************************************/

  @Override
  public void actionPerformed(ActionEvent evt)
  {
    if (evt.getActionCommand() == Actions.ROLL.name())
    {
      Player player = this.players.get(playerIndex);

      this.diceResult = player.rollDice();
      this.window.getRightPanel().updateInfos(this.players,player.getColor(),player.getName(),this.diceResult);
      this.window.repaint();

      if(player.canPlay(diceResult) && !movablePawnsBlocked()) this.window.getRoll().setEnabled(false);
      else
      {
        playerIndex = (playerIndex + 1) % 4;
        diceResult = 0;
      }
    }

    else if(evt.getActionCommand() == Actions.MENU.name())
    {
      this.menu.setVisible(true);
      this.window.setVisible(false);
    }

    else if (evt.getActionCommand() == Actions.RESUME.name())
    {
      this.menu.setVisible(false);
      this.window.setVisible(true);
    }

    else if (evt.getActionCommand() == Actions.NEWGAME.name())
    {
      this.menu.setVisible(false);
      this.window.setVisible(true);
     resetGame();
    }

  }

  /***************************************************/

  public static List<Player> getPlayers(){return players;}

  /***************************************************/

  private void checkWin()
  {
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
    createPlayers();
    assignPawnsColor();
    setUpMapping();
  }

  /***************************************************/

  private void initWindow()
  {
    //Initializing windows
    this.menu.initMenu();
    this.window.initWindow();

    //Adding action listeners
    this.window.getRoll().addActionListener(this);
    this.window.getMenu().addActionListener(this);
    this.menu.getResume().addActionListener(this);
    this.menu.getNewGame().addActionListener(this);
    this.window.addMouseListener(this);

    //Setting action commands
    this.menu.getResume().setActionCommand(Actions.RESUME.name());
    this.menu.getNewGame().setActionCommand(Actions.NEWGAME.name());
    this.window.getRoll().setActionCommand(Actions.ROLL.name());
    this.window.getMenu().setActionCommand(Actions.MENU.name());

    this.window.draw();

  }
  /***************************************************/

  private void createPlayers()
  {
    //Creating default players
    for(int i = 0;i<4;++i) this.players.add(new Player("Player" + (i+1)));
  }

  /***************************************************/

  private void setPlayerNames()
  {
    //Asking for the player's name
    for(int i = 0;i<4;++i)
    {
      String name = JOptionPane.showInputDialog( "Player name:" );
      if(name != null && !name.equals("")) this.players.get(i).setName(name);
    }
  }

  /***************************************************/

  private void setUpMapping()
  {
    //Setting a map depending on the player's color (orientation)
    for(Player p : this.players)
    {
      if(p.getColor() == Colors.getDarkColors()[0]) p.setMap( (new FourthPlayerPositions()).getMap() );
      else if(p.getColor() == Colors.getDarkColors()[1]) p.setMap( (new SecondPlayerPositions()).getMap() );
      else if(p.getColor() == Colors.getDarkColors()[2]) p.setMap( (new FirstPlayerPositions()).getMap() );
      else p.setMap( (new ThirdPlayerPositions()).getMap() );
    }
  }

  /***************************************************/

  public void runGame()
  {
    setUpGame();
    initWindow();
    playerIndex = whoStarts();
    setPlayerNames();

    this.window.getRightPanel().updateInfos(this.players,this.players.get(playerIndex).getColor(),this.players.get(playerIndex).getName(),this.diceResult);
  }

  /***************************************************/

  private void resetGame()
  {
    this.players = null;
    this.players = new ArrayList<Player>(4);

    setUpGame();
    playerIndex = whoStarts();
    setPlayerNames();

    this.window.getRightPanel().updateInfos(this.players,this.players.get(playerIndex).getColor(),this.players.get(playerIndex).getName(),this.diceResult);
    this.window.getRoll().setEnabled(true);
    this.window.repaint();
  }

  /***************************************************/

  private int whoStarts()
  {
    int max = 0;
    List<Player> rolledSame = new ArrayList<Player>();
    int iPlayFirst = -1;

    //Rolling the dice once for each player
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
    for(int i = 0;i<4;++i)
      this.players.get(i).setColor(Colors.getDarkColors()[i]);
  }

  /***************************************************/

  private boolean movablePawnsBlocked()
  {
    boolean block = false;
    for(int j : this.players.get(playerIndex).getMovablePawns()) //Checking if there's a block on the way of movable pawns
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
            GameVector pos = this.players.get(playerIndex).getMapPosition().get(k);
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
      if(!block) break; //If there's no block for at least one pawn, we break
    }


    return block;
  }

  /***************************************************/

  private void checkEatenPawns(GameVector pos)
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
