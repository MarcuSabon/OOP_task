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
package oop.utils;

import oop.shell.IRegistry;
import oop.shell.IShell;
import oop.shell.ITerminal;
import oop.shell.ITerminal.Monitor;
import oop.streams.VirtualKeyCodes;

public class Shell implements IShell {
	private ITerminal m_term;
	private String m_history[];
	private int historyIndex = 0;
	private int hist_idx = -1;
	private int lineIndex = 0;
	private StringBuilder currentLine;
	private Monitor monitor;
	private String prompt = "$ ";
	private Listener listener;
	private Environment env;
	private boolean clear_command = false;

	/*
	 * DO NOT MODIFY THE SIGNATURE OF THIS CONSTRUCTOR
	 */
	public Shell(ITerminal term, int capacity) {
		m_history = new String[capacity];
		m_term = term;

		env = new Environment();
		currentLine = new StringBuilder();
		prompt();

	}

	@Override
	public void pressed(int keyCode, char keyChar) {
		switch (keyCode) {
		case (VirtualKeyCodes.VK_ENTER):
			exec(line());
			module_historique();
			currentLine.setLength(0);
			lineIndex = 0;
			currentLine = new StringBuilder();
			if (!clear_command) {
				m_term.enter();
			}
			clear_command = false;
			prompt();
			hist_idx = -1;
			break;
		case (VirtualKeyCodes.VK_BACK_SPACE):
			if (lineIndex != 0) {
				currentLine.deleteCharAt(lineIndex - 1);
				m_term.backspace();
				lineIndex--;
			}
			break;
		case (VirtualKeyCodes.VK_DELETE):
			if (lineIndex != currentLine.length()) {
				currentLine.deleteCharAt(lineIndex);
				m_term.delete();
			}
			break;
		case (VirtualKeyCodes.VK_RIGHT):
			if (lineIndex < currentLine.length()) {
				m_term.right();
				lineIndex++;
			}

			break;
		case (VirtualKeyCodes.VK_LEFT):
			if (lineIndex > 0) {
				m_term.left();
				lineIndex--;
			}
			break;
		case (VirtualKeyCodes.VK_HOME):
			while (lineIndex > 0) {
				m_term.left();
				lineIndex--;
			}
			break;

		case (VirtualKeyCodes.VK_END):// end va trop loin quand on réécrit sur du texte
			if (lineIndex < currentLine.length()) {
				for (int i = lineIndex; i < currentLine.length(); i++) {
					m_term.right();
					lineIndex++;
				}
			}

			break;
		case (VirtualKeyCodes.VK_UP):
			if (historyIndex != 0) {
				if (hist_idx < historyIndex - 1) {
					hist_idx++;
				}

				while (lineIndex > 0) {
					m_term.backspace();
					lineIndex--;
				}
				if (hist_idx < historyIndex) {
					currentLine.setLength(0);
					currentLine.append(m_history[hist_idx]);
					lineIndex = currentLine.length();
					insert_str(currentLine.toString());
				} else {
					currentLine.setLength(0);
					lineIndex = 0;
				}
			}
			break;

		case (VirtualKeyCodes.VK_DOWN):
			if(historyIndex != 0) {
			if (historyIndex > 0 && hist_idx > 0) {
				hist_idx--;
			}

			while (lineIndex > 0) {
				m_term.backspace();
				lineIndex--;
			}

			if (hist_idx < historyIndex) {
				currentLine.setLength(0);
				currentLine.append(m_history[hist_idx]);
				lineIndex = currentLine.length();
				insert_str(currentLine.toString());
			}
			}
			break;

		default:
			break;
		}

	}

	@Override
	public void released(int keyCode, char keyChar) {

	}

	@Override
	public void typed(char keyChar) {

		currentLine.insert(lineIndex, keyChar);
		lineIndex++;
		m_term.insert(keyChar);

	}

	public void exec(String line) {
		String[] list = line.split(" ");
		if (list.length == 0) {
			return;
		}

		String command = list[0];
		switch (command) {
		case "clear":
			m_term.clear();
			currentLine.setLength(0);
			lineIndex = 0;
			currentLine = new StringBuilder();
			m_term.setCursor(0, 0);// pb
			clear_command = true;
			break;

		case "history":
			String[] m_history = history();
			for (int i = m_history.length - 1; i >= 0; i--) {
				if (m_history[i] == null) {

				} else {

					m_term.enter();
					insert_str("[" + i + "] " + m_history[i]);
				}
			}
			break;

		case "echo":
			m_term.enter();
			for (int i = 1; i < list.length; i++) {
				String arg = list[i];
				if (arg.startsWith("$")) {
					String varName = arg.substring(1);
					String varValue = env.get(varName);
					if (varValue != null) {
						insert_str(varValue);
					} else {
						insert_str("null");
					}
				} else {
					insert_str(arg);
				}
				if (i < list.length - 1) {
					m_term.insert(' ');
				}
			}
			break;

		case "env":
			for (String name : env.listNames()) {
				m_term.enter();
				insert_str(name + "=" + env.get(name));
			}
			break;

		case "set":
			if (list.length >= 3) {
				StringBuilder value = new StringBuilder();
				for (int i = 2; i < list.length; i++) {
					value.append(list[i]).append(" ");
				}
				env.put(list[1], value.toString().trim());
			} else {
				insert_str("Usage: set name <args>");
			}
			break;

		case "unset":
			if (list.length >= 2) {
				for (int i = 1; i < list.length; i++) {
					env.del(list[i]);
				}
			} else {
				insert_str("Usage: unset <name>");
			}
			break;

		default:
			m_term.enter();
			insert_str("Unknown Command\n");
			break;
		}

	}

	public void module_historique() {
		if (historyIndex >= m_history.length) {
			for (int i = m_history.length - 1; i > 0; i--) {
				m_history[i] = m_history[i - 1];
			}
		} else {
			for (int i = historyIndex; i > 0; i--) {
				m_history[i] = m_history[i - 1];
			}
			historyIndex++;
		}
		m_history[0] = line();
	}

	@Override
	public String prompt(String prompt) {
		this.prompt = prompt;
		insert_str(prompt);
		return prompt;

	}

	@Override
	public String prompt() {
		insert_str(prompt);
		return prompt;

	}

	public void insert_str(String str) {
		for (int i = 0; i < str.length(); i++) {
			m_term.insert(str.charAt(i));
		}
	}

	@Override
	public String[] history() {
		return m_history;
	}

	@Override
	public String line() {
		return currentLine.toString();
	}

	@Override
	public String valueOf(String name) {
		return env.get(name);
	}

	@Override
	public void set(Listener l) {
		this.listener = l;
	}

	@Override
	public void set(IRegistry reg) {
		// Empty implementation is ok for now,
		// this is ignored in task3
	}

}
