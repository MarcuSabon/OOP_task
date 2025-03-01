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
import oop.tasks.Task;

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
	private boolean visible;

	public Terminal(Canvas canvas, String fontName, int fontSize) {
		this.canvas = canvas;
		this.fontName = fontName;
		this.fontSize = fontSize;
		this.text = new Text(30, 80);
		this.cursor = new Cursor(0, 0);
		this.visible = true;
		startCursorBlinking();

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
		text.setText(row, col);
	}

	/*
	 * Request this terminal to repaint itself on the given canvas with the given
	 * graphics.
	 */
	public void paint(Canvas canvas, Graphics g) {
		int posex = 0;
		int posey = 0;
		g.setColor(Colors.black);
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		font = g.getFont(fontName, Font.PLAIN, fontSize);
		this.cursor.setLimits(nrows(), ncols());
		int x = column() * font.getWidth('a');
		int y = row() * font.getHeight();
		g.setColor(Colors.green);
		g.setFont(font);
		for (int i = 0; i < nrows(); i++) {
			for (int j = 0; j < ncols(); j++) {
				char c = text.grid_returner(i, j);
				if (c != ' ' && (i != row()+1 || j != column())) {
					
						g.drawString(String.valueOf(c), j * font.getWidth('a'), i * font.getHeight());
					
				} else {
					posex = row() +1;
					posey = column();
				}
			}
		}
		if (visible) {
			g.setColor(Colors.green);
			g.fillRect(x, y, font.getWidth('a'), font.getHeight());
			g.setColor(Colors.black);
			g.drawString(String.valueOf(text.grid_returner(posex, posey)), posey * font.getWidth('a'),posex * font.getHeight());
		} else if (!visible) {
			g.setColor(Colors.black);
			g.fillRect(x, y, font.getWidth('a'), font.getHeight());
			g.setColor(Colors.green);
			g.drawString(String.valueOf(text.grid_returner(posex, posey)), posey * font.getWidth('a'),posex * font.getHeight());
		}
		// g.setColor(Colors.green);
		// g.fillRect(x, y, font.getWidth('a'), font.getHeight());

	}

	private void startCursorBlinking() {
		Task task = Task.task();
		task.post(new Runnable() {
			public void run() {
				visible = !visible;
				canvas.repaint();
				task.post(this, 500);
			}
		}, 500);
	}

	@Override
	public int nrows() {
		return canvas.getHeight() / font.getHeight();
	}

	@Override
	public int ncols() {
		return canvas.getWidth() / font.getWidth('a');
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
		canvas.repaint();
	}

	@Override
	public void right() {
		cursor.right();
		canvas.repaint();
	}

	@Override
	public void up() {
		cursor.up();
		canvas.repaint();
	}

	@Override
	public void down() {
		cursor.down();
		canvas.repaint();
	}

	@Override
	public void delete() {
		text.setText(row(), column());
		text.delete();
		canvas.repaint();
	}

	@Override
	public void backspace() {
		text.setText(row(), column());
		text.backspace();
		cursor.left();
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
		text.setText(row(), column());
		text.enter();
		cursor.setCursor(row() + 1, 0);
		canvas.repaint();
	}

	@Override
	public void insert(char c) {
		text.setText(row(), column());
		text.insert(c);
		cursor.right();
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
