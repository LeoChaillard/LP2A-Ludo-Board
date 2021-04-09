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
    g.drawString("Ludo Board Game", 400, 400);
  }


}
