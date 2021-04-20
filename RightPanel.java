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
  private JLabel gameInfos;
  private JLabel home;

  //Constructor
  public RightPanel(int size)
  {
    this.setLayout(new GridLayout(4,1));
    this.setMinimumSize(new Dimension(size,0));
    this.setPreferredSize(new Dimension(size,0));

    this.rollDice = new JButton("Roll dice");
    this.restart = new JButton("Restart");

    this.gameInfos = new JLabel("<html>&emsp;&emsp;Game informations<br/><br/><br/>Playing : <br/><br/>Rolled :</html>");
    Font font1 = new Font("Arial", Font.BOLD,16);
    this.gameInfos.setFont(font1);
    this.gameInfos.setHorizontalAlignment(SwingConstants.LEFT);
    this.gameInfos.setVerticalAlignment(SwingConstants.TOP);

    this.home = new JLabel();
    Font font2 = new Font("Arial", Font.BOLD,14);
    this.home.setFont(font2);
    this.home.setHorizontalAlignment(SwingConstants.LEFT);
    this.home.setVerticalAlignment(SwingConstants.TOP);


    this.add(this.rollDice);
    this.add(this.restart);
    this.add(this.gameInfos);
    this.add(this.home);

  }

  //Methods
  public JButton getRoll(){return this.rollDice;}

  /***************************************************/

  public JButton getRestart(){return this.restart;}

  /***************************************************/

  public void updateInfos(List<Player> players)
  {
    this.home.setText("<html>");
    for(int i = 0;i<4;++i)
    {
     this.home.setText(this.home.getText() + players.get(i).getName() + " : " + players.get(i).getPawnsHome() + " pawn(s) at home<br/><br/>");
    }
    this.home.setText(this.home.getText() + "<html>");
  }
}
