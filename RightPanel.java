import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.GridLayout;



public class RightPanel extends JPanel{
  //Attributes
  private JButton rollDice;
  private JButton restart;
  private JLabel gameInfos;

  //Constructor
  public RightPanel(int size)
  {
    this.setLayout(new GridLayout(3,1));
    this.setMinimumSize(new Dimension(size,0));
    this.setPreferredSize(new Dimension(size,0));

    this.rollDice = new JButton("Roll dice");
    this.restart = new JButton("Restart");
    this.gameInfos = new JLabel("Informations:");
    this.gameInfos.setHorizontalAlignment(SwingConstants.LEFT);
    this.gameInfos.setVerticalAlignment(SwingConstants.CENTER);

    this.add(this.rollDice);
    this.add(this.restart);
    this.add(this.gameInfos);
  }

  //Methods
  public JButton getRoll(){return this.rollDice;}

  /***************************************************/

  public JButton getRestart(){return this.restart;}

}
