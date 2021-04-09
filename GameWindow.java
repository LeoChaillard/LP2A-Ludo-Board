import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class GameWindow extends JFrame{
  //Attributes
  private ImageIcon frameIcon;
  private static final long serialVersionUID = 1L;
  private JLabel playing;
  private JLabel pawn1, pawn2, pawn3, pawn4;
  private JLabel rolledText;
  private JButton rollDice;
  private JButton movePawn;
  private JButton play;

  //Constructor
  public GameWindow()
  {
    this.frameIcon = new ImageIcon("Icon.jpg");
    this.playing = new JLabel();
    this.pawn1 = new JLabel();
    this.pawn2 = new JLabel();
    this.pawn3 = new JLabel();
    this.pawn4 = new JLabel();
    this.rolledText = new JLabel();
    this.rollDice = new JButton("Roll dice");
    this.movePawn = new JButton("Move pawn");
    this.play = new JButton("Next Player");
  }

  //Methods
  public JButton getPlay(){return this.play;}

  public JButton getRoll(){return this.rollDice;}

  public JButton getMove(){return this.movePawn;}

  /***************************************************/

  private void setFrameIcon()
  {
    setIconImage(frameIcon.getImage());
  }

  /***************************************************/

  private JLabel setTitle()
  {
    JLabel title = new JLabel();
    Font font = new Font("Courier", Font.BOLD,25);
    title.setText("Ludo Board Game");
    title.setFont(font);
    title.setBounds(180,10,300,30);
    return title;
  }

  /***************************************************/

  public void setPlaying(String name)
  {
    Font font = new Font("Arial", Font.BOLD,18);
    playing.setText(name + " is playing");
    playing.setFont(font);
    playing.setBounds(200,90,300,30);
    this.add(playing);
  }

  /***************************************************/

  private JButton setDiceButton()
  {
    rollDice.setBounds(50,80,90,50);
    //rollDice.setActionCommand(Actions.ROLL.name());
    //rollDice.addActionListener(this);
    return rollDice;
  }

  /***************************************************/

  public void setRollText(int diceResult)
  {
    Font font = new Font("Arial", Font.BOLD,15);
    rolledText.setText("You rolled " + diceResult);
    rolledText.setFont(font);
    rolledText.setBounds(50,120,90,50);
    this.add(this.rolledText);
  }

  /***************************************************/

  private JButton setPlayButton()
  {
    play.setBounds(400,80,120,50);
    //play.setActionCommand(Actions.PLAY.name());
    //play.addActionListener(this);
    play.setEnabled(false);
    return play;
  }

  /***************************************************/

  private JButton setMoveButton()
  {
    movePawn.addActionListener(null);
    movePawn.setBounds(210,290,120,50);
    //movePawn.setActionCommand(Actions.MOVE.name());
    //movePawn.addActionListener(this);
    movePawn.setEnabled(false);
    return movePawn;
  }

  /***************************************************/

  public void setPawn1(int square)
  {
    Font font = new Font("Arial", Font.BOLD,15);
    pawn1.setFont(font);
    pawn1.setBounds(200,135,300,30);
    if(square == -1) pawn1.setText("Pawn1 is at starting block");
    else if (square == 56) pawn1.setText("Pawn1 is at home");
    else pawn1.setText("Pawn1 is at square " + square);
    this.add(pawn1);
  }

  /***************************************************/

  public void setPawn2(int square)
  {
    Font font = new Font("Arial", Font.BOLD,15);
    pawn2.setFont(font);
    pawn2.setBounds(200,175,300,30);
    if(square == -1) pawn2.setText("Pawn2 is at starting block");
    else if (square == 56) pawn2.setText("Pawn2 is at home");
    else pawn2.setText("Pawn2 is at square " + square);
    this.add(pawn2);
  }

  /***************************************************/

  public void setPawn3(int square)
  {
    Font font = new Font("Arial", Font.BOLD,15);
    pawn3.setFont(font);
    pawn3.setBounds(200,215,300,30);
    if(square == -1) pawn3.setText("Pawn3 is at starting block");
    else if (square == 56) pawn3.setText("Pawn3 is at home");
    else pawn3.setText("Pawn3 is at square " + square);
    this.add(pawn3);
  }

  /***************************************************/

  public void setPawn4(int square)
  {
    Font font = new Font("Arial", Font.BOLD,15);
    pawn4.setFont(font);
    pawn4.setBounds(200,255,300,30);
    if(square == -1) pawn4.setText("Pawn4 is at starting block");
    else if (square == 56) pawn4.setText("Pawn4 is at home");
    else pawn4.setText("Pawn4 is at square " + square);
    this.add(pawn4);
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
  }

  /***************************************************/

  public void draw () {
    EventQueue.invokeLater(() -> {
    setVisible(true);
    });
  }
}
