/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.EventQueue;

/**
 * Class defining the main window of the game.
 * It contains RightPanel and GameBoard objects.
 */
public class GameWindow extends JFrame{
  //Attributes
  private JLabel playing;
  private JLabel rolledText;
  private RightPanel rightPanel;
  private GameBoard boardPanel;

  private static final int WINDOW_HEIGHT = 800;
  private static final int WINDOW_LENGTH = 1000;
  private static final int RIGHT_SIDE = WINDOW_LENGTH - WINDOW_HEIGHT;

  //Constructor
  public GameWindow()
  {
    this.playing = new JLabel();
    this.rolledText = new JLabel();
    this.rightPanel = new RightPanel(RIGHT_SIDE);
    this.boardPanel = new GameBoard();
  }

  //Methods
  public JButton getRoll(){return this.rightPanel.getRoll();}

  /***************************************************/

  public JButton getMenu(){return this.rightPanel.getMenu();}

  /***************************************************/

  public RightPanel getRightPanel(){return this.rightPanel;}

  /***************************************************/

  private void setFrameIcon()
  {
    ImageIcon frameIcon = new ImageIcon("Icon.jpg");
    setIconImage(frameIcon.getImage());
  }

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

    this.getContentPane().add(rightPanel,BorderLayout.EAST);
    this.getContentPane().add(boardPanel,BorderLayout.CENTER);
  }

  /***************************************************/

  public void draw () {
    EventQueue.invokeLater(() -> {
    setVisible(true);
    });
  }
}
