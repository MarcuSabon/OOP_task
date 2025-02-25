/*
 *  Copyright (C) Pr. Olivier Gruber <olivier dot gruber at acm dot org>
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package oop.term;

import oop.graphics.Canvas;
import oop.graphics.Font;
import oop.graphics.Graphics;
import oop.graphics.Graphics.Colors;
import oop.shell.ITerminal;

public class Terminal implements ITerminal {

	private Canvas canvas;
	private Text text;
	private Cursor cursor;
	private ITerminal.Listener listener;
	private Font font;
	private String fontName;
	private int fontSize;
	private Graphics g;
	private Monitor monitor;

	public Terminal(Canvas canvas, String fontName, int fontSize) {
		this.canvas = canvas;
		this.fontName = fontName;
		this.fontSize = fontSize;
		this.text = new Text(30, 80);
		this.cursor = new Cursor(0, 0);

	}

	/*
	 * Sets the cursor at the given coordinates, coordinates given in pixels on the
	 * canvas. To translate (x,y) in (row,column), one needs to use the font used to
	 * display the characters, because each characters has its own width, for a
	 * given font.
	 */
	public void clicked(int x, int y) {
		int col = x / font.getWidth('a');
		int row = y / font.getHeight();
		setCursor(row, col);
	}

	/*
	 * Request this terminal to repaint itself on the given canvas with the given
	 * graphics.
	 */
	public void paint(Canvas canvas, Graphics g) {
		g.setColor(Colors.green);
		g.setFont(g.getFont(fontName, font.PLAIN, fontSize));
		
		g.setColor(Colors.black);
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		
		g.setColor(Colors.green);
		//setCursor(0,0);
		g.fillRect(ncols()*font.getWidth('a'), nrows()*font.getHeight(), font.getWidth('a'), font.getHeight());
		
	}

	@Override
	public int ncols() {
		return canvas.getWidth() / font.getWidth('a');
	}

	@Override
	public int nrows() {
		return canvas.getHeight() / font.getHeight();
	}

	@Override
	public void setCursor(int row, int col) {
		cursor.setCursor(row, col);
		canvas.repaint();
	}

	@Override
	public int column() {
		return cursor.column();
	}

	@Override
	public int row() {
		return cursor.row();
	}

	@Override
	public void left() {
		cursor.left();
	}

	@Override
	public void right() {
		cursor.right();
	}

	@Override
	public void up() {
		cursor.up();
	}

	@Override
	public void down() {
		cursor.down();
	}

	@Override
	public void delete() {
		text.delete();
		canvas.repaint();
	}

	@Override
	public void backspace() {
		text.backspace();
		canvas.repaint();
	}

	@Override
	public void clear() {
		text.clear();
		canvas.repaint();
	}

	@Override
	public void clear(int row) {
		text.clearRow(row);
		canvas.repaint();
	}

	@Override
	public void enter() {
		text.enter();
		canvas.repaint();
	}

	@Override
	public void insert(char c) {
		text.insert(c);
		canvas.repaint();
	}

	@Override
	public void set(Listener l) {
		this.listener = l;
	}

	@Override
	public void monitor(Monitor l) {
		this.monitor = l;
		
	}


}
