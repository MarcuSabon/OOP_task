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
import oop.shell.IShell.Listener;

public class Environment {
	
	
	private Variable head; 

    public Environment() {
        this.head = null;
    }

    
    private static class Variable {
        String name;
        String value;
        Variable next;

        Variable(String name, String value) {
            this.name = name;
            this.value = value;
            this.next = null;
        }
    }

   
    public String[] listNames() {
        int count = 0;
        Variable temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        String[] names = new String[count];
        temp = head;
        for (int i = 0; i < count; i++) {
            names[i] = temp.name;
            temp = temp.next;
        }
        return names;
    }

    
    public String get(String name) {
        Variable temp = head;
        while (temp != null) {
            if (temp.name.equals(name)) {
                return temp.value;
            }
            temp = temp.next;
        }
        return null;
    }

    
    public String del(String name) {
        Variable temp = head;
        Variable prev = null;

        while (temp != null) {
            if (temp.name.equals(name)) {
                if (prev == null) {
                    head = temp.next; 
                } else {
                    prev.next = temp.next; 
                }
                return temp.value;
            }
            prev = temp;
            temp = temp.next;
        }
        return null;
    }

    
    public void put(String name, String val) {
        Variable temp = head;

      
        while (temp != null) {
            if (temp.name.equals(name)) {
                temp.value = val;
                return;
            }
            temp = temp.next;
        }

       
        Variable newVar = new Variable(name, val);
        newVar.next = head;
        head = newVar;
    }
	
    
    
    
	
}
