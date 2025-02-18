package oop.term;

import oop.graphics.Graphics;
import oop.graphics.Font;

public class Cursor {
    private int row;
    private int col;
    private boolean visible;
    
    public Cursor(int r, int c) {
    	this.row = r;
        this.col = c;
        this.visible = true;
    }
    
    public void setCursor(int row, int col)
    {
    	this.row = row;
    	this.col = col;
    }
    public int column()
    {
    	return col;
    }
    public int row()
    {
    	return row;
    }
    public int left() 
    {
    	return col--;
    }
    public int right() 
    {
    	return col++;
    }
    public int up() 
    {
    	return row--;
    }
    public int down() 
    {
    	return row++;
    }
}