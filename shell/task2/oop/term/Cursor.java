package oop.term;

import oop.graphics.Graphics;
import oop.tasks.Task;
import oop.graphics.Canvas;
import oop.graphics.Font;

public class Cursor {
    private int row;
    private int col;
    private int row_max;
    private int col_max;
    private boolean visible;
    
    public Cursor(int r, int c) {
        this.row = r;
        this.col = c;
        this.row_max = 0; 
        this.col_max = 0;
    }
    

    
    public void setCursor(int r, int c) {
        if (r >= 0 && r < row_max) row = r;
        if (c >= 0 && c < col_max) col = c;
    }
    
    public int column() {
        return col;
    }
    
    public int row() {
        return row;
    }
    
    public void left() {
        if (col > 0) col--;
    }
    
    public void right() {
        if (col < col_max - 1) col++;
    }
    
    public void up() {
        if (row > 0) row--;
    }
    
    public void down() {
        if (row < row_max - 1) row++;
    }
    
    public void toggleVisibility() {
        visible = !visible;
    }

  
    public void setLimits(int maxRow, int maxCol) {
        this.row_max = maxRow;
        this.col_max = maxCol;
    }
}
