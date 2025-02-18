package oop.term;

import oop.graphics.Graphics;
import oop.graphics.Font;

public class Text {
    private char[][] grid;
    
    public Text(int rows, int cols) {
    	grid = new char[rows][cols];
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
                grid[row][j] = '\0';
            }
        }
    }
    
}