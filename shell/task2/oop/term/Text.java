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
    	
    public void clear() {
        for (int i = 0; i < grid.length; i++) {
            clearRow(i);
        }
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
                grid[cursorRow][j] = grid[cursorRow][j + 1];
            }
            grid[cursorRow][grid[cursorRow].length - 1] = ' ';
        }
    }
    
    public void backspace() {
        if (cursorCol > 0) {
            cursorCol--;
            for (int j = cursorCol; j < grid[cursorRow].length - 1; j++) {
                grid[cursorRow][j] = grid[cursorRow][j + 1];
            }
            grid[cursorRow][grid[cursorRow].length - 1] = ' ';
        }
    }
    
    public void enter() {
        if (cursorRow < grid.length - 1) {
            cursorRow++;
            cursorCol = 0;}
    }
    
    
    public void insert(char c) {
        if (cursorCol < grid[cursorRow].length) {
            for (int j = grid[cursorRow].length - 1; j > cursorCol; j--) {
                grid[cursorRow][j] = grid[cursorRow][j - 1];
            }
            grid[cursorRow][cursorCol] = c;
            if (cursorCol < grid[cursorRow].length - 1) {
                cursorCol++;
            }
        }
    }
    
}