/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Board game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Polygon;
import java.awt.event.MouseListener;
import java.util.*;


public class GameBoard extends JPanel{
  //Attributes
  private static final long serialVersionUID = 1L;
  private static final float ELEMENTS = 15f;
  private static final float ELEMENT_SIZE = 0.85f;
  private static final float BLOCK_LENGTH = 6f;
  private static final int QUARTER = 13;

  public static final Color[] colors = {
            new Color(0xcfff39),
            new Color(0xffe783),
            new Color(0x95e5e5),
            new Color(0xff896a)
    };

    public static final Color[] darkColors = {
            new Color(0x9acd00),
            new Color(0xffcd00),
            new Color(0x2fcdcd),
            new Color(0xff3000)
    };

    public static final Color[] pawnColors = {
            new Color(0x208000),
            new Color(0xCC7A00),
            new Color(0x001133),
            new Color(0x990000)
    };

  //Constructor
  public GameBoard()
  {

  }

  //Methods
  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    this.setBackground(Color.WHITE);

    float width = getWidth();
    float height = getHeight();

    //Direction
    Direction d = new Direction( ELEMENTS/2.f, ELEMENTS/2.f);
    Direction.Orientation ori;
    d.setScale(height/ELEMENTS);

    //Rectangles
    Rectangle rec = new Rectangle();
    rec.setDirection(d);
    rec.setScale(height/ELEMENTS);

    //Circles
    Circle cir = new Circle();
    cir.setDirection(d);
    cir.setScale(height/ELEMENTS);

    //Pawns
    DrawPawn pawn = new DrawPawn();
    pawn.setDirection(d);
    pawn.setScale(height/ELEMENTS);

    for(int i = 0;i<colors.length;++i)
    {
      //Drawing starting block
      d.resetMove();
      rec.setSide(BLOCK_LENGTH);
      ori = Direction.Orientation.values()[i];
      d.setOrientation(ori);
      d.move((0.5f + 1 + BLOCK_LENGTH/2.f), -(0.5f + 1 + BLOCK_LENGTH/2.f));
      g.setColor(darkColors[i]);
      rec.fill(g);
      g.setColor(colors[i]);
      rec.setSide(BLOCK_LENGTH*0.90f);
      rec.fill(g);

      //Drawing middle triangle
      d.resetMove();
      g.setColor(colors[i]);
      Polygon p = new Polygon();
      p.addPoint(Math.round(d.getX()), Math.round(d.getY()));
      d.up(1.5f);
      d.left(1.5f);
      p.addPoint(Math.round(d.getX()), Math.round(d.getY()));
      d.right(3f);
      p.addPoint(Math.round(d.getX()), Math.round(d.getY()));
      g.fillPolygon(p);

      //Drawing centered squares
      d.resetMove();
      rec.setSide(ELEMENT_SIZE);
      d.up(1);
      for(int j = 0;j<BLOCK_LENGTH-1;++j)
      {
        d.up(1);
        rec.fill(g);
      }

      //Drawing circles
      g.setColor(darkColors[i]);
      cir.setSide(ELEMENT_SIZE);
      d.up(1);
      cir.fill(g);
      d.right(1);
      cir.fill(g);
      for(int k = 0;k<BLOCK_LENGTH-1;++k)
      {
        d.down(1);
        cir.fill(g);
      }
      d.down(1);
      for(int k = 0;k<BLOCK_LENGTH;++k)
      {
        d.right(1);
        cir.fill(g);
      }

    }

    //Drawing pawns depending on their position
    List<Player> players = Game.getPlayers();
    pawn.setSide(0.70f);
    for (int direction = 0;direction<Direction.Orientation.values().length;++direction)
    {
      g.setColor(pawnColors[direction]);
      int pawnIndex = 0;

      //Looking for the player that has index "direction" color
      int index = 0;
      for(int i = 0;i<4;++i)
      {
        if (players.get(i).isSameColor(direction))
        {
          index = i;
          break;
        }
      }

      for(Pawn p : players.get(index).getPawns())
      {
        d.setOrientation(Direction.Orientation.values()[direction]);
        d.resetMove();
        if(p.isStartingBlock())
        {
          d.move((0.5f + 1 + BLOCK_LENGTH/2.f), -(0.5f + 1 + BLOCK_LENGTH/2.f));
          if(pawnIndex % 2 == 0) d.left(1);
          else d.right(1);

          if(pawnIndex < 2) d.up(1);
          else d.down(1);
        }
        else if(p.getSquare() >= 50)
        {
          d.up(1);
          d.up(56 - p.getSquare());
          if(p.getSquare() == 56) p.setHome(true);
        }
        else if(p.getSquare() < 50 && !p.isStartingBlock())
        {
          d.up(6);
          d.right(1);

          int tmp = Math.round(BLOCK_LENGTH)-2;
          int squares = p.getSquare();

          int i = direction;
          while (squares > 12) {
            d.setOrientation(Direction.Orientation.values()[(++i) % Direction.Orientation.values().length]);
            squares -= QUARTER;
          }

          while(squares > 0 && tmp > 0 )
          {
            d.down(1);
            --tmp;
            --squares;
          }

          if(squares > 0)
          {
            d.down(1);
            tmp = Math.round(BLOCK_LENGTH);
            while(squares > 0 && tmp > 0)
            {
              d.right(1);
              --tmp;
              --squares;
            }
          }
          d.down(squares);
        }

        pawn.fill(g);

        p.setX(pawn.getX());
        p.setY(pawn.getY());

        ++pawnIndex;
      }


    }

    Toolkit.getDefaultToolkit().sync();
  }


}
