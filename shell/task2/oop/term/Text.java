package oop.term;

import oop.graphics.Graphics;
import oop.graphics.Font;

public class Text {
    private char[][] grid;
    private int cursorRow;
    private int cursorCol;
    
    public Text(int rows, int cols) {
    	grid = new char[rows][cols];
    	this.cursorRow = rows;
    	this.cursorCol = cols;
        clear();
    }
   
    public char grid_returner(int rows, int cols) {
    	return grid[rows][cols];
    }
    
    public void setText(int r , int c) {
    	cursorRow = r;
    	cursorCol = c;
    }
    	
    public void clear() {
        for (int i = 0; i < grid.length; i++) {
            clearRow(i);
        }
        cursorRow=0;
        cursorCol=0;
    }
    
    public void clearRow(int row) {
        if (row < grid.length) {
            for (int j = 0; j < grid[row].length; j++) {
                grid[row][j] = ' ';
            }
        }
    }
    
    public void delete() {
        if (cursorCol < grid[cursorRow].length - 1) {
            for (int j = cursorCol; j < grid[cursorRow].length - 1; j++) {
                grid[cursorRow+1][j] = grid[cursorRow+1][j + 1];
            }
            grid[cursorRow+1][grid[cursorRow+1].length - 1] = ' ';
        }
    }
    
    public void backspace() {
        if (cursorCol > 0) {
            cursorCol--;
            for (int j = cursorCol; j < grid[cursorRow].length - 1; j++) {
                grid[cursorRow+1][j] = grid[cursorRow+1][j + 1];
            }
            grid[cursorRow+1][grid[cursorRow+1].length - 1] = ' ';
        }
    }
    
    public void enter() {
        if (cursorRow < grid.length - 1) {
            cursorRow++;
            cursorCol = 0;}
    }
    
    
    public void insert(char c) {
        if (cursorRow < grid.length && cursorCol < grid[cursorRow].length) {
            grid[cursorRow+1][cursorCol] = c;
            cursorCol = Math.min(cursorCol + 1, grid[cursorRow].length - 1);
        }
    }

    
    
}