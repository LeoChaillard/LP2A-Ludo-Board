import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Image;


public class GameWindow extends JFrame {
  //Attributes
  private ImageIcon frameIcon;

  //Constructor
  public GameWindow()
  {
    this.frameIcon = new ImageIcon("Icon.jpg");
  }

  //Methods
  public void initWindow()
  {
    this.setTitle("Ludo Board");
    this.setFrameIcon();
    this.setBounds(10,10,1000,600);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  /***************************************************/

  private void setFrameIcon()
  {
    setIconImage(frameIcon.getImage());
  }

  /***************************************************/

  public void draw () {
    EventQueue.invokeLater(() -> {
    setVisible(true);
    });
  }
}
