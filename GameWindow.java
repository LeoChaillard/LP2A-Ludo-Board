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
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.Color;

public class GameWindow extends JFrame implements ActionListener{
  //Attributes
  private ImageIcon frameIcon;
  private static final long serialVersionUID = 1L;
  private JPanel panel;
  private JLabel label;

  //Constructor
  public GameWindow()
  {
    this.frameIcon = new ImageIcon("Icon.jpg");
    this.panel = new JPanel();
    this.label = new JLabel();
  }

  //Methods
  public void createTextBox()
  {
    LayoutManager layout = new FlowLayout();
    this.panel.setLayout(layout);
    JButton button = new JButton("Click Me!");
    button.addActionListener(this);

    this.panel.setBackground(Color.WHITE);
    this.panel.add(button);
    this.panel.add(this.label);
    this.getContentPane().add(this.panel, BorderLayout.CENTER);
  }


  /***************************************************/

  public void actionPerformed(ActionEvent evt)
  {
    String result = (String)JOptionPane.showInputDialog(this,"Select one of the color", "Swing Tester",JOptionPane.PLAIN_MESSAGE,null,null, "Red");
    if(result != null && result.length() > 0){
         this.label.setText("You selected:" + result);
    }else {
        this.label.setText("None selected");
    }
  }

  /***************************************************/

  public void initWindow()
  {
    GameBoard gb = new GameBoard();

    this.setTitle("Ludo Board");
    this.setFrameIcon();
    this.setBounds(10,10,1000,600);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.createTextBox();

    //this.getContentPane().add(gb);




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
