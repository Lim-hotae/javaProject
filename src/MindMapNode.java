import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class MindMapNode 
{
	private JPanel mpanel;
	private JLabel mlabel;
	private String label;
	
	private int x = -1;
	private int y = -1;
	private int w = 25;
	private int h = 25;
	private Color color = new Color(0,0,0);
	
	public MindMapNode(String mLabel)
	{
		this.label = mLabel;
		this.mlabel = new JLabel(mLabel);
		this.mlabel.setForeground(Color.WHITE);
		
		this.mpanel = new JPanel();
		this.mpanel.setBorder(new LineBorder(Color.BLUE,2));
		
		this.mpanel.add(this.mlabel);
	}
	
	public void setPos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public JPanel getPanel()
	{
		return this.mpanel;
	}
	
	public void setBackGround(int r, int g, int b)
	{
		this.mpanel.setBackground(new Color(r,g,b));
	}
	
	public void setLabelSize(int W, int H)
	{
		this.mlabel.setSize(W,H);
	}
	
	public void setPanelBounds(int W, int H)
	{
		this.mpanel.setBounds(this.x, this.y, W, H);
	}
	
	public Color getColor()
	{
		return this.color;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public int getW()
	{
		return this.w;
	}
	
	public int getH()
	{
		return this.h;
	}
	
	public void setW(int w)
	{
		this.w = w;
	}
	
	public void setH(int h)
	{
		this.h = h;
	}
	
	public String getStringLabel()
	{
		return this.label;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
}
