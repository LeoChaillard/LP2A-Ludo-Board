/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.util.*;

/**
 * Right panel of our JFrame, containing
 * all the games' utilities / informations.
 */
public class RightPanel extends JPanel{
  //Attributes
  private JButton rollDice;
  private JButton menu;
  private JLabel gameInfos;
  private JLabel home;

  //Constructor
  public RightPanel(int size)
  {
    //Set up of the panel
    this.setBackground(Color.WHITE);
    this.setLayout(new GridLayout(4,1));
    this.setMinimumSize(new Dimension(size,0));
    this.setPreferredSize(new Dimension(size,0));

    //Buttons
    this.rollDice = new JButton("Roll dice");
    this.rollDice.setBackground(Color.WHITE);
    this.menu = new JButton("Menu");
    this.menu.setBackground(Color.WHITE);

    //First JLabel for updating the current player infos
    this.gameInfos = new JLabel();
    Font font1 = new Font("Arial", Font.BOLD,16);
    this.gameInfos.setFont(font1);
    this.gameInfos.setForeground(Color.BLACK);
    this.gameInfos.setHorizontalAlignment(SwingConstants.LEFT);
    this.gameInfos.setVerticalAlignment(SwingConstants.TOP);

    //Second JLabel for updating the number of pawns home
    this.home = new JLabel();
    Font font2 = new Font("Arial", Font.BOLD,14);
    this.home.setFont(font2);
    this.home.setForeground(Color.BLACK);
    this.home.setHorizontalAlignment(SwingConstants.LEFT);
    this.home.setVerticalAlignment(SwingConstants.TOP);

    //Adding the components to the panel
    this.add(this.rollDice);
    this.add(this.menu);
    this.add(this.gameInfos);
    this.add(this.home);
  }

  //Methods
  public JButton getRoll(){return this.rollDice;}

  /***************************************************/

  public JButton getMenu(){return this.menu;}

  /***************************************************/

  public void updateInfos(List<Player> players,Color color,String name,int diceResult)
  {
    String hex = "#"+Integer.toHexString(color.getRGB()).substring(2);
    this.gameInfos.setText("<html>&emsp;&emsp;Game's informations<br/><br/><br/>Playing : <font color='" + hex + "'>" + name + "</font><br/><br/>Rolled : <font color='" + hex + "'>" + diceResult + "</font></html>");

    this.home.setText("");
    for(int i = 0;i< 4;++i)
    {
      hex = "#"+Integer.toHexString(players.get(i).getColor().getRGB()).substring(2);
      this.home.setText(this.home.getText() + "<html><font color='" + hex + "'>" + players.get(i).getName() + "</font> : " + players.get(i).getPawnsHome() + " pawn(s) at home<br/><br/><html>");
    }

  }
}
