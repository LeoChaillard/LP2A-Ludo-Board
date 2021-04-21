/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Board game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class GameWindow extends JFrame{
  //Attributes
  private JLabel playing;
  private JLabel rolledText;
  private RightPanel rp;
  private GameBoard gb;


  private static final int WINDOW_HEIGHT = 800;
  private static final int WINDOW_LENGTH = 1000;
  private static final int RIGHT_SIDE = WINDOW_LENGTH - WINDOW_HEIGHT;

  //Constructor
  public GameWindow()
  {
    this.playing = new JLabel();
    this.rolledText = new JLabel();
    this.rp = new RightPanel(RIGHT_SIDE);
    this.gb = new GameBoard();
  }

  //Methods

  public void resetWindow()
  {
    this.updateRollText(0,Color.WHITE);
    this.updatePlaying("",Color.WHITE);
  }

  /***************************************************/

  public JButton getRoll(){return this.rp.getRoll();}

  /***************************************************/

  public JButton getRestart(){return this.rp.getRestart();}

  /***************************************************/

  public JButton getMenu(){return this.rp.getMenu();}

  /***************************************************/

  private void setFrameIcon()
  {
    ImageIcon frameIcon = new ImageIcon("Icon.jpg");
    setIconImage(frameIcon.getImage());
  }

  /***************************************************/

  private void setPlaying()
  {
    Font font = new Font("Arial", Font.BOLD,15);
    playing.setFont(font);
    playing.setBounds(860,347,120,50);
    playing.setText("");
    this.add(playing);
  }

  /***************************************************/

  public void updatePlaying(String name,Color c)
  {
    playing.setForeground(c);
    playing.setText(name);

  }

  /***************************************************/

  private void setRollText()
  {
    Font font = new Font("Arial", Font.BOLD,18);
    rolledText.setFont(font);
    rolledText.setBounds(855,384,90,50);
    rolledText.setText("");
    this.add(this.rolledText);
  }

  /***************************************************/

  public void updateRollText(int diceResult,Color c)
  {
    rolledText.setForeground(c);
    rolledText.setText("" + diceResult);
  }

  /***************************************************/

  public RightPanel getRightPanel(){return this.rp;}

  /***************************************************/

  public void initWindow()
  {
    this.setTitle("Ludo Board");
    this.setFrameIcon();
    this.setBounds(10,10,WINDOW_LENGTH,WINDOW_HEIGHT);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setRollText();
    this.setPlaying();

    this.getContentPane().add(rp,BorderLayout.EAST);
    this.getContentPane().add(gb,BorderLayout.CENTER);

  }

  /***************************************************/

  public void draw () {
    EventQueue.invokeLater(() -> {
    setVisible(true);
    });
  }
}
