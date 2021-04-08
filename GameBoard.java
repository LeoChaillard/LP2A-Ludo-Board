import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameBoard extends JPanel{

	public GameBoard()
  {

	}

	public void paint(Graphics g)
  {
    g.setColor(Color.WHITE);
    g.fillRect(0,0,getWidth(),getHeight());
  }


}
