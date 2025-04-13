package oop.utils.collections;

import oop.collections.ICollection;
import oop.collections.IList;

public class LinkedList implements IList {

    private static class noeud {
        Object donnee;
        noeud next;

        noeud(Object donnee) {
            this.donnee = donnee;
            this.next = null;
        }
    }

    private noeud tete;
    private noeud queue;
    private int thai;

    public static class Iterator implements ICollection.Iterator {
        private noeud atm;

        public Iterator(LinkedList linkedList) {
            this.atm = linkedList.tete;
        }

        @Override
        public boolean hasNext() {
            return atm != null;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("No elements anymore");
            }
            Object donnee = atm.donnee;
            atm = atm.next;
            return donnee;
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator(this);
    }

    public LinkedList() {
        this.tete = null;
        this.queue = null;
        this.thai = 0;
    }

    public LinkedList(Object array[]) {
        
        for (Object elem : array) {
            insertAt(thai, elem);
        }
    }

    public LinkedList(LinkedList v) {

        for (noeud noeud = v.tete; noeud != null; noeud = noeud.next) {
            insertAt(thai, noeud.donnee);
        }
    }

    public LinkedList(ICollection c) {
        
        ICollection.Iterator it = c.iterator();
        while (it.hasNext()) {
            insertAt(thai, it.next());
        }
    }

    @Override
    public int length() {
        return thai;
    }

    /**
     * return element a index
     */ 
    @Override
    public Object elementAt(int index) {
        if (index < 0 || index >= thai) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        noeud atm = tete;
        for (int i = 0; i < index; i++) {
            atm = atm.next;
        }
        return atm.donnee;
    }

    /**
     * update a l'index et return oldonne
     */
    @Override
    public Object updateAt(int index, Object niu) {
        if (index < 0 || index >= thai) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        noeud atm = tete;
        for (int i = 0; i < index; i++) {
            atm = atm.next;
        }
        Object olddonnee = atm.donnee;
        atm.donnee = niu;
        return olddonnee;
    }
    
    /**
     * va a l'injdex et insert
     */

    @Override
    public void insertAt(int index, Object elem) {
    	if (index < 0) {
            throw new IndexOutOfBoundsException("Invalid Index");
        }

        // Cas où index > thai
        while (thai < index) {
            insertAt(thai, null);
        }

        noeud cell = new noeud(elem);
        if (index == 0) {
            cell.next = tete;
            tete = cell;
        } else {
            noeud current = tete;
            for (int i = 0; i < index - 1; i++) {
                // on se place à la position où on veut insérer
                current = current.next;
            }
            cell.next = current.next;
            current.next = cell;
        }
        thai++;

    }
    /**
     * trouve l'endroit et Supp 
     */
    @Override
    public Object removeAt(int index) {
        if (index < 0 || index >= thai) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Object removeddonnee;
        if (index == 0) {
            removeddonnee = tete.donnee;
            tete = tete.next;
            if (tete == null) {
                queue = null;
            }
        } else {
            noeud pre = tete;
            for (int i = 0; i < index - 1; i++) {
                pre = pre.next;
            }
            removeddonnee = pre.next.donnee;
            pre.next = pre.next.next;
            if (pre.next == null) {
                queue = pre;
            }
        }
        thai--;
        return removeddonnee;
    }

    /**
     * Supp tout 
     */
    @Override
    public boolean remove(Object elem) {
        noeud atm = tete;
        noeud pre = null;
        while (atm != null) {
            if (atm.donnee.equals(elem)) {
                if (pre == null) {
                    tete = atm.next;
                } else {
                    pre.next = atm.next;
                }
                if (atm.next == null) {
                    queue = pre;
                }
                thai--;
                return true;
            }
            pre = atm;
            atm = atm.next;
        }
        return false;
    }

    
    @Override
    public boolean contains(Object elem) {
        noeud atm = tete;
        while (atm != null) {
            if (atm.donnee.equals(elem)) {
                return true;
            }
            atm = atm.next;
        }
        return false;
    }

    @Override
    public void toArray(Object[] elems) {
        tete = null;
        thai = 0;
       for(Object obj: elems) {
    	   insertAt(thai,obj);
       }
    }
}
