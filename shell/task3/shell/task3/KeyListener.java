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
package shell.task3;

import oop.graphics.Canvas;
import oop.shell.IShell;
import oop.streams.VirtualKeyCodes;

public class KeyListener implements Canvas.KeyListener {

	Canvas m_canvas;
	IShell m_shell;

	KeyListener(Canvas canvas, IShell shell) {
		m_canvas = canvas;
		m_shell = shell;
		canvas.set(this);
	}

	@Override
	public void pressed(Canvas canvas, int keyCode, char keyChar) {
		m_shell.pressed(keyCode, keyChar);

	}

	@Override
	public void released(Canvas canvas, int keyCode, char keyChar) {
		// System.out.printf("TextLine: released=[%d]\n", keyCode);
		m_shell.released(keyCode, keyChar);
	}

	@Override
	public void typed(Canvas canvas, char keyChar) {
		m_shell.typed(keyChar);
	}

}
