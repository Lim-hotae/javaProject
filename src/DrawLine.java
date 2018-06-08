import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

public class DrawLine 
{
	private int x;
	private int y;
	private int pex;
	private int pey;
	private int pwx;
	private int pwy;
	private int pnx;
	private int pny;
	private int psx;
	private int psy;

	public DrawLine(TreeNode node, JPanel mpanel)
	{
		Set(node);
		//Draw(node,mpanel);
	}
	
	public void Set(TreeNode node)
	{
		this.x = (int)node.getX();
		this.y = (int)node.getY();
		
		this.pex = x + (int)node.getW()/2;
		this.pey = this.y;
		this.pwx = this.x - (int)node.getW()/2;;
		this.pwy = this.y;
		this.pnx = this.x;
		this.pny = this.y + (int)node.getH()/2;
		this.psx = this.x;
		this.psy = this.y - (int)node.getH()/2;
	}
	
//	public void Draw(TreeNode node, MyPanel mpanel)
//	{
//		mpanel.paintComponenet(Graphic g, this.pex, this.pey, this.px, this.py);
//		
//		for(TreeNode child:node.getChildren())
//		{
//			Draw(child, mpanel);
//		}
//	}
	
}
