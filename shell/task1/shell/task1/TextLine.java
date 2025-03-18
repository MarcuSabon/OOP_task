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
package shell.task1;


import oop.graphics.Font;
import oop.graphics.Canvas;
import oop.graphics.Graphics;
import oop.graphics.Graphics.Colors;
import oop.streams.VirtualKeyCodes;
import oop.tasks.Task;

public class TextLine {

	/*
	 * Listener to the evolution of this line of text. The methods must be invoked
	 * on the task that set the listener.
	 * 
	 * 
	 */
	int move_x;
	int move_y;
	StringBuilder line;
	private Listener listener;
	private int CursorPosition = 0;
	private boolean CursorVisible = true;
	private Font font;

	public interface Listener {
		/*
		 * Invoked when the given character is inserted at the given position in the
		 * current line of text. The valid position are within the range [0:length] with
		 * length being the current length of the line of text.
		 */
		void inserted(int pos, char c);

		/*
		 * Invoked when the given character is inserted at the given position in the
		 * current line of text. The valid position are within the range [0:length[ with
		 * length being the current length of the line of text.
		 */
		void deleted(int pos, char c);

		/*
		 * Invoked when the line of text is validated, which happens when the character
		 * '\n' has been typed. The line must not include that character '\n'.
		 */
		void validated(String line);
	}

	public void set(Listener l) {
		this.listener = l;
	}

	public TextLine(Canvas canvas) {
		this.line = new StringBuilder();
		canvas.set(new PaintListener());
        canvas.set(new MouseListener());
        canvas.set(new KeyListener());
		canvas.repaint();
		this.set(listener);

	}

	class KeyListener implements Canvas.KeyListener {

		@Override
		public void pressed(Canvas canvas, int keyCode, char keyChar) {

			switch (keyCode) {
			case (VirtualKeyCodes.VK_ENTER):
				if (listener != null) {
                    listener.validated(line.toString());
                }
                line.setLength(0);
                CursorPosition = 0;
                canvas.repaint();
				break;
			case (VirtualKeyCodes.VK_BACK_SPACE):
				if (CursorPosition > 0) {
                    char deletedChar = line.charAt(CursorPosition - 1);
                    line.deleteCharAt(CursorPosition - 1);
                    CursorPosition--;
                    if (listener != null) {
                        listener.deleted(CursorPosition, deletedChar);
                    }
                    canvas.repaint();
                }
				break;
			case (VirtualKeyCodes.VK_DELETE):
				if (CursorPosition < line.length()) {
                    char deletedChar = line.charAt(CursorPosition);
                    line.deleteCharAt(CursorPosition);
                    if (listener != null) {
                        listener.deleted(CursorPosition, keyChar);
                    }
                    CursorPosition--;
                    canvas.repaint();
                }
				
			case (VirtualKeyCodes.VK_RIGHT):
				if (CursorPosition < line.length()) {
                    CursorPosition++;
                    canvas.repaint();
                }
				break;

			case (VirtualKeyCodes.VK_LEFT):
				if (CursorPosition > 0) {
                    CursorPosition--;
                    canvas.repaint();
                }
				
				break;
			default:
				break;
			}

		}

		@Override
		public void released(Canvas canvas, int keyCode, char keyChar) {

		}

		@Override
		public void typed(Canvas canvas, char keyChar) {
			if (CursorPosition < 0) {
		        CursorPosition = 0;
		    }
		    if (CursorPosition > line.length()) {
		        CursorPosition = line.length();
		    }
			line.insert(CursorPosition, keyChar);
			if (listener != null) {
				listener.inserted(CursorPosition, keyChar);
			}
			CursorPosition++;
			canvas.repaint();
		}

	}

	class MouseListener implements Canvas.MouseListener {

		@Override
		public void moved(Canvas canvas, int x, int y) {
			move_x = x;
			move_y = y;
			canvas.repaint();
		}

		@Override
		public void pressed(Canvas canvas, int bno, int x, int y) {

		}

		@Override
		public void released(Canvas canvas, int bno, int x, int y) {

		}
	}

	class PaintListener implements Canvas.PaintListener {

		@Override
		public void paint(Canvas canvas, Graphics g) {
			
			font = g.getFont("Arial", Font.PLAIN, 30);
		    g.setFont(font);

		    
		    int cursor = move_x + g.getFont().getWidth(line.substring(0, CursorPosition));

		    g.setColor(Colors.black);
		    g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		    g.setColor(Colors.white);
		    g.drawString(line.substring(0, CursorPosition), move_x, move_y);

		    if(CursorVisible) {
		    g.setColor(Colors.white);
	        g.fillRect(cursor, move_y - 30, g.getFont().getWidth('a'), 30);
		    }

	        if (CursorPosition < line.length()) {
	        	if(CursorVisible) {
	        		g.setColor(Colors.white);
	    	        g.fillRect(cursor, move_y - 30, g.getFont().getWidth('a'), 30);
	        		g.setColor(Colors.black);
		            g.drawString(String.valueOf(line.charAt(CursorPosition)), cursor, move_y);
	        	}
	        	else {
	        		g.setColor(Colors.black);
	    	        g.fillRect(cursor, move_y - 30, g.getFont().getWidth('a'), 30);
	        		g.setColor(Colors.white);
		            g.drawString(String.valueOf(line.charAt(CursorPosition)), cursor, move_y);
	        	}
	            
	        }
		    

		    if (CursorPosition < line.length() - 1) {
		        g.setColor(Colors.white);
		        g.drawString(line.substring(CursorPosition + 1), cursor + g.getFont().getWidth('a'), move_y);
		    }
			
			
		}

		@Override
		public void visible(Canvas canvas) {
			Task task = Task.task();
            task.post(new Runnable() {
                public void run() {
                    CursorVisible = !CursorVisible;
                    canvas.repaint();
                    task.post(this, 500);
                }
            });

			//CursorPosition++;
			canvas.repaint();

		}

		@Override
		public void revoked(Canvas canvas) {

		}
	}
}
