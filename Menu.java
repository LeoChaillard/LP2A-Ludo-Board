/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Class defining the game's menu.
 * It contains an ImagePanel object in order to
 * draw the background image of the JFrame.
 */
public class Menu extends JFrame{
  //Attributes
  private static final int WINDOW_HEIGHT = 600;
  private static final int WINDOW_LENGTH = 500;
  private JButton resume;
  private JButton newGame;
  private ImageIcon icon;
  private ImagePanel background;

  //Constructor
  public Menu()
  {
    this.icon = new ImageIcon("icon.jpg");
    this.background = new ImagePanel(icon.getImage() );
    this.resume = new JButton("Resume");
    this.newGame = new JButton("New game");
  }

  //Methods
  public JButton getResume(){return this.resume;}

  /***************************************************/

  public JButton getNewGame(){return this.newGame;}

  /***************************************************/

  public void initMenu()
  {
    //Set up of the JFrame
    this.setTitle("Ludo Board");
    this.setIconImage(icon.getImage());
    this.setBounds(10,10,WINDOW_LENGTH,WINDOW_HEIGHT);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(null);

    //Buttons
    newGame.setBackground(Color.WHITE);
    newGame.setBounds(175,420,147,70);
    resume.setBackground(Color.WHITE);
    resume.setBounds(175,325,147,70);

    //Adding components to the JFrame
    this.setContentPane(background);
    this.add(resume);
    this.add(newGame);
  }

}
