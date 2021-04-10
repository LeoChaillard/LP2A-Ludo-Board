import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;


public class GameWindow extends JFrame{
  //Attributes
  private JLabel playing;
  private JLabel pawn1, pawn2, pawn3, pawn4;
  private JLabel rolledText;
  private JButton rollDice;
  private JButton movePawn;
  private JButton nextPlay;

  //Constructor
  public GameWindow()
  {
    this.playing = new JLabel();
    this.pawn1 = new JLabel();
    this.pawn2 = new JLabel();
    this.pawn3 = new JLabel();
    this.pawn4 = new JLabel();
    this.rolledText = new JLabel();
    this.rollDice = new JButton("Roll dice");
    this.movePawn = new JButton("Move pawn");
    this.nextPlay = new JButton("Next player");
  }

  //Methods
  public JButton getNextPlay(){return this.nextPlay;}

  /***************************************************/

  public JButton getRoll(){return this.rollDice;}

  /***************************************************/

  public JButton getMove(){return this.movePawn;}

  /***************************************************/

  private void setFrameIcon()
  {
    ImageIcon frameIcon = new ImageIcon("Icon.jpg");
    setIconImage(frameIcon.getImage());
  }

  /***************************************************/

  private JLabel setTitle()
  {
    Font font = new Font("Courier", Font.BOLD,25);
    JLabel title = new JLabel();
    title.setText("Ludo Board Game");
    title.setFont(font);
    title.setBounds(180,10,300,30);
    return title;
  }

  /***************************************************/

  private void setPlaying()
  {
    Font font = new Font("Arial", Font.BOLD,18);
    playing.setFont(font);
    playing.setBounds(200,90,300,30);
    this.add(playing);
  }

  /***************************************************/

  public void updatePlaying(String name)
  {
    playing.setText(name + " is playing");
  }

  /***************************************************/

  private void setRollText()
  {
    Font font = new Font("Arial", Font.BOLD,15);
    rolledText.setFont(font);
    rolledText.setBounds(50,120,90,50);
    this.add(this.rolledText);
  }

  /***************************************************/

  public void updateRollText(int diceResult)
  {
    rolledText.setText("You rolled " + diceResult);
  }

  /***************************************************/

  private JButton setDiceButton()
  {
    rollDice.setBounds(50,80,90,50);
    return rollDice;
  }

  /***************************************************/

  private JButton setPlayButton()
  {
    nextPlay.setBounds(400,80,120,50);
    nextPlay.setEnabled(false);
    return nextPlay;
  }

  /***************************************************/

  private JButton setMoveButton()
  {
    movePawn.addActionListener(null);
    movePawn.setBounds(210,290,120,50);
    movePawn.setEnabled(false);
    return movePawn;
  }

  /***************************************************/

  private void setPawn1()
  {
    Font font = new Font("Arial", Font.BOLD,15);
    pawn1.setFont(font);
    pawn1.setBounds(200,135,300,30);
    this.add(pawn1);
  }

  /***************************************************/

  private void setPawn2()
  {
    Font font = new Font("Arial", Font.BOLD,15);
    pawn2.setFont(font);
    pawn2.setBounds(200,175,300,30);
    this.add(pawn2);
  }

  /***************************************************/

  private void setPawn3()
  {
    Font font = new Font("Arial", Font.BOLD,15);
    pawn3.setFont(font);
    pawn3.setBounds(200,215,300,30);
    this.add(pawn3);
  }

  /***************************************************/

  private void setPawn4()
  {
    Font font = new Font("Arial", Font.BOLD,15);
    pawn4.setFont(font);
    pawn4.setBounds(200,255,300,30);
    this.add(pawn4);
  }

  /***************************************************/

  public void updatePawn1(int square)
  {
    if(square == -1) pawn1.setText("Pawn1 is at starting block");
    else if (square == 56) pawn1.setText("Pawn1 is at home");
    else pawn1.setText("Pawn1 is at square " + square);
  }

  /***************************************************/

  public void updatePawn2(int square)
  {
    if(square == -1) pawn2.setText("Pawn2 is at starting block");
    else if (square == 56) pawn2.setText("Pawn2 is at home");
    else pawn2.setText("Pawn2 is at square " + square);
  }

  /***************************************************/

  public void updatePawn3(int square)
  {
    if(square == -1) pawn3.setText("Pawn3 is at starting block");
    else if (square == 56) pawn3.setText("Pawn3 is at home");
    else pawn3.setText("Pawn3 is at square " + square);
  }

  /***************************************************/

  public void updatePawn4(int square)
  {
    if(square == -1) pawn4.setText("Pawn4 is at starting block");
    else if (square == 56) pawn4.setText("Pawn4 is at home");
    else pawn4.setText("Pawn4 is at square " + square);
  }

  /***************************************************/

  public void initWindow()
  {
    this.setTitle("Ludo Board");
    this.setFrameIcon();
    this.setBounds(10,10,600,450);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(Color.WHITE);
    this.setLayout(null);

    this.add(this.setTitle());
    this.add(this.setDiceButton());
    this.add(this.setPlayButton());
    this.add(this.setMoveButton());

    this.setRollText();
    this.setPlaying();
    this.setPawn1();
    this.setPawn2();
    this.setPawn3();
    this.setPawn4();
  }

  /***************************************************/

  public void draw () {
    EventQueue.invokeLater(() -> {
    setVisible(true);
    });
  }
}
