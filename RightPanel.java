import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

import java.awt.Dimension;
import java.awt.GridLayout;

import java.util.*;


public class RightPanel extends JPanel{
  //Attributes
  private JButton rollDice;
  private JButton restart;
  private JButton menu;
  private JLabel gameInfos;
  private JLabel home;

  public static final Color[] darkColors = {
          new Color(0x9acd00),
          new Color(0xffcd00),
          new Color(0x2fcdcd),
          new Color(0xff3000)
  };

  //Constructor
  public RightPanel(int size)
  {
    this.setBackground(Color.WHITE);
    this.setLayout(new GridLayout(5,1));
    this.setMinimumSize(new Dimension(size,0));
    this.setPreferredSize(new Dimension(size,0));

    this.rollDice = new JButton("Roll dice");
    this.rollDice.setBackground(Color.WHITE);
    this.restart = new JButton("Restart");
    this.restart.setBackground(Color.WHITE);
    this.menu = new JButton("Menu");
    this.menu.setBackground(Color.WHITE);


    this.gameInfos = new JLabel("<html>&emsp;&emsp;Game informations<br/><br/><br/>Playing : <br/><br/>Rolled :</html>");
    Font font1 = new Font("Arial", Font.BOLD,16);
    this.gameInfos.setFont(font1);
    this.gameInfos.setForeground(Color.BLACK);
    this.gameInfos.setHorizontalAlignment(SwingConstants.LEFT);
    this.gameInfos.setVerticalAlignment(SwingConstants.TOP);


    this.home = new JLabel();
    Font font2 = new Font("Arial", Font.BOLD,14);
    this.home.setFont(font2);
    this.home.setForeground(Color.BLACK);
    this.home.setHorizontalAlignment(SwingConstants.LEFT);
    this.home.setVerticalAlignment(SwingConstants.TOP);

    this.add(this.rollDice);
    this.add(this.menu);
    this.add(this.gameInfos);
    this.add(this.home);

  }

  //Methods
  public JButton getRoll(){return this.rollDice;}

  /***************************************************/

  public JButton getRestart(){return this.restart;}

  /***************************************************/

  public JButton getMenu(){return this.menu;}

  /***************************************************/

  public void updateInfos(List<Player> players)
  {

    this.home.setText("<html>" + players.get(0).getName() + " : " + players.get(0).getPawnsHome() + " pawn(s) at home<br/><br/><html>");
    this.home.setText(this.home.getText() + "<html>" + players.get(1).getName() + " : " + players.get(1).getPawnsHome() + " pawn(s) at home<br/><br/><html>");
    this.home.setText(this.home.getText() + "<html>" + players.get(2).getName() + " : " + players.get(2).getPawnsHome() + " pawn(s) at home<br/><br/><html>");
    this.home.setText(this.home.getText() + "<html>" +  players.get(3).getName() + " : " + players.get(3).getPawnsHome() + " pawn(s) at home<br/><br/><html>");

  }
}
