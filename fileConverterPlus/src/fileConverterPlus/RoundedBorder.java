package fileConverterPlus;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.border.Border;

public class RoundedBorder implements Border {

	private int radius;
	Color color;
	RoundedBorder(int radius, Color color){
		this.radius = radius;
		this.color = color;	
	}
	
	public void fillInvertedArc(Component c, Graphics g, int height, int width, int radius)
	{
		for (int i = 0; i < radius; i++)
		{
			for (int j = 0; i < radius; j++) {
				if (Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2)) > radius) {
					//g.drawLine(i, j, i, j);
				}
			}
		}
	}
	
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Color col = new Color(93, 93, 93);
		g.setColor(col);
		g.drawRoundRect(x, y, width-1, height-1, radius, radius);
		for (int i = 0; i <= radius; i++) {
			for (int j = 0; j <= radius; j++) {
				if (Math.sqrt(Math.pow(i-radius, 2) + Math.pow(j-radius, 2)) > radius) {
					g.drawLine(i, j, i, j);
					g.drawLine(width - i, j, width - i, j);
					g.drawLine(i, height - j, i, height - j);
					g.drawLine(width - i, height - j, width - i, height - j);
				}
			}
		} 
	}

	@Override
	public Insets getBorderInsets(Component c) {
		
		return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	}

	@Override
	public boolean isBorderOpaque() {

		return false;
	}

}
