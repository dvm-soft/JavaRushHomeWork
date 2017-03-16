package com.javarush.test.level20.lesson10.bonus04;

import java.io.*;
import java.util.*;
/* Свой список
Посмотреть, как реализован LinkedList.
Элементы следуют так: 1->2->3->4  и так 4->3->2->1
По образу и подобию создать Solution.
Элементы должны следовать так:
1->3->7->15
    ->8...
 ->4->9
    ->10
2->5->11
    ->12
 ->6->13
    ->14
Удалили 2 и 9
1->3->7->15
    ->8
 ->4->10
Добавили 16,17,18,19,20 (всегда добавляются на самый последний уровень к тем элементам, которые есть)
1->3->7->15
       ->16
    ->8->17
       ->18
 ->4->10->19
        ->20
Удалили 18 и 20
1->3->7->15
       ->16
    ->8->17
 ->4->10->19
Добавили 21 и 22 (всегда добавляются на самый последний уровень к тем элементам, которые есть.
Последний уровень состоит из 15, 16, 17, 19. 19 последний добавленный элемент, 10 - его родитель.
На данный момент 10 не содержит оба дочерних элемента, поэтому 21 добавился к 10. 22 добавляется в следующий уровень.)
1->3->7->15->22
       ->16
    ->8->17
 ->4->10->19
        ->21

Во внутренней реализации элементы должны добавляться по 2 на каждый уровень
Метод getParent должен возвращать элемент, который на него ссылается.
Например, 3 ссылается на 7 и на 8, т.е.  getParent("8")=="3", а getParent("13")=="6"
Строки могут быть любыми.
При удалении элемента должна удаляться вся ветка. Например, list.remove("5") должен удалить "5", "11", "12"
Итерироваться элементы должны в порядке добавления
Доступ по индексу запрещен, воспользуйтесь при необходимости UnsupportedOperationException
Должно быть наследование AbstractList<String>, List<String>, Cloneable, Serializable
Метод main в тестировании не участвует
*/
public class Solution extends AbstractList<String> implements List<String>, Cloneable, Serializable
{
    int size = 0;
    Node<String> rootNode = new Node<String>(null, null, null);
    Node<String> first;
    Node<String> second;
    Node<String> last;

    public Solution() {
    }

    public static void main(String[] args) throws Exception {
        Solution listTree = new Solution();
        System.out.println("Check isEmpty: " + listTree.isEmpty() + " Size: " + listTree.size());
        listTree.add("1");
        listTree.add("2");
        listTree.add("3");
        System.out.println(listTree);
        listTree.remove("1");
        System.out.println(listTree);
        listTree.add("3");
        listTree.add("4");
        listTree.add("5");
        listTree.add("6");
        System.out.println(listTree);
        listTree.remove("5");
        listTree.add("7");
        listTree.add("8");
        listTree.remove("6");
        listTree.add("9");
        listTree.add("10");
        listTree.remove("2");
        listTree.add("11");
        System.out.println(listTree);
        System.out.println("3 Expected null, actual is " + listTree.getParent("2"));
        System.out.println("3 Expected null, actual is " + listTree.getParent("3"));

        listTree.clear();
        for (int i = 1; i < 16; i++) {
            listTree.add(String.valueOf(i));
        }
        System.out.println(listTree);
        listTree.remove("15");
        System.out.println(listTree);
        listTree.add("15");
        System.out.println(listTree);

        System.out.println("Check isEmpty: " + listTree.isEmpty() + " Size: " + listTree.size());
        List<String> list2222 = new Solution();
        System.out.println("Check isEmpty: " + list2222.isEmpty() + " Size: " + list2222.size());
        list2222.add("test");
        System.out.println("Check isEmpty: " + list2222.isEmpty() + " Size: " + list2222.size());
        List<String> list1111 = new Solution();
        System.out.println("Check isEmpty: " + list1111.isEmpty() + " Size: " + list1111.size());

        System.out.println("\nExpected 3, actual is " + ((Solution) listTree).getParent("8"));
        listTree.remove("5");
        System.out.println("Expected null, actual is " + ((Solution) listTree).getParent("11"));
        listTree.clear();
        for (int i = 1; i < 16; i++) {
            listTree.add(String.valueOf(i));
        }

        //For additional check correct work clone method
        Solution list = ((Solution)listTree).clone();

        System.out.println("Start value with using clone: " + listTree);
        System.out.println("\n===== REMOVE Remove 2 and 9 =====");
        list.remove("2");
        list.remove("9");
        for (String s : list) System.out.print(s+ " ");
        System.out.println("\n===== ADD 16, 17, 18, 19, 20 =====");
        list.add("16");
        list.add("17");
        list.add("18");
        list.add("19");
        list.add("20");
        for (String s : list) System.out.print(s+ " ");
        System.out.println("\n===== REMOVE 18 and 20 =====");
        list.remove("18");
        list.remove("20");
        for (String s : list) System.out.print(s+ " ");
        System.out.println("\n===== ADD 21 and 22 =====");
        list.add("21");
        list.add("22");
        list.add("23");
        list.add("24");
        list.add("25");
        list.add("26");
        list.add("27");
        list.add("28");
        list.add("29");
        list.add("30");
        list.add("31");
        list.add("32");
        //list.add(null);
        for (String s : list) System.out.print(s+ " ");
        System.out.println("\n---------------------------------------");
        System.out.println("3 Expected 1, actual is " + ((Solution) list).getParent("3"));
        System.out.println("4 Expected 1, actual is " + ((Solution) list).getParent("4"));
        System.out.println("8 Expected 3, actual is " + ((Solution) list).getParent("8"));
        System.out.println("11 Expected null, actual is " + ((Solution) list).getParent(null));
        System.out.println("15 Expected 7, actual is " + ((Solution) list).getParent("15"));
        System.out.println("16 Expected 7, actual is " + ((Solution) list).getParent("16"));
        System.out.println("10 Expected 4, actual is " + ((Solution) list).getParent("10"));
        System.out.println("17 Expected 8, actual is " + ((Solution) list).getParent("17"));
        System.out.println("19 Expected 10, actual is " + ((Solution) list).getParent("19"));
        System.out.println("21 Expected 10, actual is " + ((Solution) list).getParent("21"));
        System.out.println("22 Expected 15, actual is " + ((Solution) list).getParent("22"));
        System.out.println("--->> By task and add more item:");
        System.out.println("23 Expected 15, actual is " + ((Solution) list).getParent("23"));
        System.out.println("24 Expected 16, actual is " + ((Solution) list).getParent("24"));
        System.out.println("25 Expected 16, actual is " + ((Solution) list).getParent("25"));
        System.out.println("26 Expected 17, actual is " + ((Solution) list).getParent("26"));
        System.out.println("27 Expected 17, actual is " + ((Solution) list).getParent("27"));
        System.out.println("28 Expected 19, actual is " + ((Solution) list).getParent("28"));
        System.out.println("29 Expected 19, actual is " + ((Solution) list).getParent("29"));
        System.out.println("30 Expected 21, actual is " + ((Solution) list).getParent("30"));
        System.out.println("31 Expected 21, actual is " + ((Solution) list).getParent("31"));
        System.out.println("32 Expected 22, actual is " + ((Solution) list).getParent("32"));
        System.out.println("---------------------------------------");
        System.out.println("Size array = " + list.size() + " expected = 22");
        System.out.println();

        System.out.println("=============== Clone test ==================");

        System.out.println("Object: " + list + " --> Size = " + list.size());
        Solution sol = list.clone();
        //list.remove("7"); //Select for test
        System.out.println("Clone object: " + sol + " --> Size = " + sol.size());
        System.out.println("Result: " + list.containsAll(sol));

        System.out.println("\nTest addAll: ");
        Solution add = new Solution();
        add.addAll(sol);
        System.out.println(add + " --> Size: " + add.size() + " = " + sol.size());

        System.out.println("=============== Iterator test ===============");
        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            String a = itr.next();
            System.out.print(a + " ");
        }
        System.out.println("\nExpected size 22 = " + list.size());

        System.out.println("\nIter remove");
        Iterator<String> itr2 = list.iterator();
        while (itr2.hasNext()) {
            if (itr2.next().contains("31")) {
                itr2.remove();
            }
        }
        System.out.println("For test " + list + " --> Size = " + list.size());
        System.out.println("Collect size " + list.size() + " Expected 21");

        System.out.println("\n===== SERIALIZATION and DESERIALIZATION =====");
        System.out.println("Collect before serializable " + list);
        System.out.print("Save list");
        FileOutputStream fos = new FileOutputStream("file");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
        fos.close();
        System.out.println(" Serializable done");
        System.out.print("Load list");
        FileInputStream fis = new FileInputStream("file");
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<String> list2 = (List<String>) ois.readObject();
        ois.close();
        fis.close();
        System.out.println(" Deserializable done");
        System.out.println("Collect after deserializable " + list2);

        System.out.println("\n================ Clear test =================");
        System.out.println("Before: " + listTree);
        listTree.clear();
        System.out.println("After clear: " + listTree + (listTree.isEmpty() ? " OK" : " Badly"));


        System.out.println("===========Adding all 1=============");
        list = new Solution();
        for (int i = 1; i < 15; i++) {
            list.add("1");
        }
        System.out.println(list);
        System.out.println("List is empty? " + list.isEmpty());
        System.out.println("List size is " + list.size() + ", expected 14");
        list.remove("1");
        System.out.println("==========After removing 1===========");
        System.out.println(list);
        System.out.println("List is empty? " + list.isEmpty());
        System.out.println("List size is " + list.size() + ", expected 0");

        System.out.println("=========Adding 1,2 & all 1===========");
        list.add("1");
        list.add("2");
        for (int i = 1; i < 13; i++) {
            list.add("1");
        }
        System.out.println(list);
        System.out.println("List is empty? " + list.isEmpty());
        System.out.println("List size is " + list.size() + ", expected 14");
        list.remove("1");
        System.out.println("==========After removing 1===========");
        System.out.println(list);
        System.out.println("List is empty? " + list.isEmpty());
        System.out.println("List size is " + list.size() + ", expected 1");
    }

    @Override
    public String toString()
    {
        String result = "";
        for (Node<String> x = first; x != null; x = x.next)
        {
            result += (getParentByNode(x) == null ? null : getParentByNode(x).item)
                    + " >> " + x.item
                    + "(" +
                    (x.child1 != null ? x.child1.item : null)
                    + "," +
                    (x.child2 != null ? x.child2.item : null)
                    + "); ";
        }

//        for (Object i : toArray())
//            result += (result.isEmpty() ? "" : ", ") + i;
        return "[" + result + "]";
    }

    @Override
    public Object[] toArray()
    {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<String> x = first; x != null; x = x.next)
        {
            result[i++] = x.item;
        }
        return result;
    }



    public String getParent(String value)
    {
        if (value == null)
            return null;

        //have to be implemented
        Node<String> child = null;
        Node<String> x = first;

        for (int i = 0; i < size; i++)
        {
            if (value.equals((String) x.item))
            {
                child = x;
                break;
            }
            x = x.next;
        }
        if(child != null)
        {
            if (getParentByNode(child) == null || getParentByNode(child).item == null)
                return null;
            else
                return getParentByNode(child).item.toString();
        }
        else
            return null;
    }



    @Override
    public String get(int index)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size() == 0;
    }



    public Node<String> getParentByNode(Node<String> child)
    {
        if (child == null || child.equals(rootNode.child1) || child.equals(rootNode.child2))
            return null;
        else
        {
            Node<String> x = first;
            for (int i = 0; i < size; i++)
            {
                if (child.equals(x.child1) || child.equals(x.child2))
                    break;
                x = x.next;
            }
            return x;
        }
    }

    public Node<String> getNextParent()
    {
        Node<String> parent = getParentByNode(last);
        if(parent == null)
            parent = first;
        while (parent.child1 != null & parent.child2 != null)
            parent = parent.next;
        return parent;
    }

    @Override
    public boolean contains(Object o)
    {
        Node<String> x = first;
        for (int i = 0; i < size; i++)
        {
            if (x.item != null && o.equals((String) x.item))
                return true;
            x = x.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }



    @Override
    public boolean add(String e)
    {
        linkLast(e);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends String> c)
    {
        for (String s : c)
            linkLast(s);
        return true;
    }


    void linkLast(String e) {
        if (size == 0)
        {
            final Node<String> newNode = new Node<>(null, e, null);
            newNode.prev = null;
            newNode.next = null;
            first = newNode;
            last = newNode;
            size ++;
            modCount++;
            rootNode.child1 = newNode;
        } else if (size == 1)
        {
            final Node<String> l = last;
            final Node<String> newNode = new Node<>(l, e, null);
            last = newNode;
            second = newNode;
            l.next = newNode;
            size ++;
            modCount++;
            if (rootNode.child1 == null)
                rootNode.child1 = newNode;
            else
                rootNode.child2 = newNode;
        } else
        {
            final Node<String> l = last;
            Node<String> nextParent = getNextParent();
            final Node<String> newNode = new Node<>(l, e, null);
            if (nextParent.child1 == null)
                nextParent.child1 = newNode;
            else
                nextParent.child2 = newNode;
            last = newNode;
            if (l == null)
                first = newNode;
            else
                l.next = newNode;
            size++;
            modCount++;
        }
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        for (Object o : c)
        {
            if (!remove(o))
                return false;
        }
        rootNode.child1 = null;
        rootNode.child2 = null;
        return true;
    }


    public boolean remove(Object o) {
        //have to be implemented
        Node<String> x = first;
        for (int i = 0; i < size; i++)
        {
            if (x.item != null && o.equals((String) x.item))
            {
                Node<String> parent = getParentByNode(x);
                if (x.equals(rootNode.child1))
                    rootNode.child1 = null;
                if (x.equals(rootNode.child2))
                    rootNode.child2 = null;
                if (parent != null)
                {
                    if (parent.child1 != null && parent.child1.equals(x))
                        parent.child1 = null;
                    if (parent.child1 != null && parent.child2.equals(x))
                        parent.child2 = null;
                }
                removeNode(x);
                x = first;
                i = 0;
            }
            else
                x = x.next;
        }
        return true;
    }

    @Override
    public void clear()
    {
        for (Node<String> x = first; x != null; ) {
            Node<String> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
        modCount++;
    }


    public boolean removeNode(Node<String> node) {
        for (Node<String> x = first; x != null; x = x.next)
        {
            if (node.equals(x))
            {
                if(x.child1 != null)
                {
                    removeNode(x.child1);
                    x.child1 = null;
                }
                if(x.child2 != null)
                {
                    removeNode(x.child2);
                    x.child2 = null;
                }

                unlink(x);
                return true;
            }
        }
        return false;
    }


    private static class Node<String> implements Serializable{
        String item;
        Node<String> next;
        Node<String> parent;
        Node<String> child1;
        Node<String> child2;
        Node<String> prev;

        Node(Node<String> prev, String element, Node<String> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
        public java.lang.String toString() {return (java.lang.String) (this.item != null ? this.item : ""); }
    }


    /**
     * Unlinks non-null node x.
     */
    String unlink(Node<String> x) {
        // assert x != null;

        final String element = x.item;
        final Node<String> next = x.next;
        final Node<String> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        modCount++;
        return element;
    }


    @Override
    public boolean equals(Object o)
    {
        Solution tmpList = (Solution) o;
        if (size != tmpList.size())
            return false;
        Node<String> x = first;
        Node<String> y = tmpList.first;
        for (int i = 0; i < size; i++)
        {
            if (!x.item.equals(y.item))
                return false;
            x = x.next;
            y = y.next;
        }
        return true;
//        return super.equals(o);
    }



    /**
     * Returns a shallow copy of this {@code LinkedList}. (The elements
     * themselves are not cloned.)
     *
     * @return a shallow copy of this {@code LinkedList} instance
     */
    public Solution clone() {
//        Solution<String> clone = superClone();
        Solution clone = new Solution();

        // Put clone into "virgin" state
        clone.first = clone.last = null;
        clone.size = 0;
        clone.modCount = 0;

        // Initialize clone with our elements
        for (Node<String> x = first; x != null; x = x.next)
            clone.add((String) x.item);
        for (Node<String> x = clone.first; x != null; x = x.next)
        {
            x.child1 = null;
            x.child2 = null;
        }
        for (Node<String> x = first; x != null; x = x.next)
        {
            Node<String> tmpChild1 = null;
            Node<String> tmpChild2 = null;
            if (x.child1 != null)
            {
                String childName = x.child1.item;
                for (Node<String> y = clone.first; y != null; y = y.next)
                {
                    if(y.item.equals(childName))
                    {
                        tmpChild1 = y;
                        break;
                    }
                }
            }
            if (x.child2 != null)
            {
                String childName = x.child2.item;
                for (Node<String> y = clone.first; y != null; y = y.next)
                {
                    if(y.item.equals(childName))
                    {
                        tmpChild2 = y;
                        break;
                    }
                }
            }
            for (Node<String> y = clone.first; y != null; y = y.next)
            {
                if (x.item.equals(y.item) && tmpChild1 != null)
                    y.child1 = tmpChild1;
                if (x.item.equals(y.item) && tmpChild2 != null)
                    y.child2 = tmpChild2;
            }
        }
        return clone;
    }

    private static final long serialVersionUID = 876323262645176354L;

    /**
     * Saves the state of this {@code LinkedList} instance to a stream
     * (that is, serializes it).
     *
     * @serialData The size of the list (the number of elements it
     *             contains) is emitted (int), followed by all of its
     *             elements (each an Object) in the proper order.
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        // Write out any hidden serialization magic
//        s.defaultWriteObject();

        // Write out size
        s.writeInt(size);

        // Write out all elements in the proper order.
        for (Node<String> x = first; x != null; x = x.next)
        {
            s.writeUTF((String) x.item);
            Node<String> parent = getParentByNode(x);
            if (parent == null)
            {
                s.writeUTF("null");
                s.writeUTF("null");
            }
            else
            {
                if (x.equals(parent.child1))
                    s.writeUTF((String) "1");
                else
                    s.writeUTF((String) "2");
                s.writeUTF((String) parent.item);
            }
        }
    }

    /**
     * Reconstitutes this {@code LinkedList} instance from a stream
     * (that is, deserializes it).
     */
    @SuppressWarnings("unchecked")
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        // Read in any hidden serialization magic
//        s.defaultReadObject();

        // Read in size
        int size = s.readInt();
        rootNode = new Node<String>(null, null, null);

        // Read in all elements in the proper order.
        for (int i = 0; i < size; i++)
        {
            add((String) s.readUTF());
            if (!last.equals(first))
                for (Node<String> x = first; x != null; x = x.next)
                {
                    if(last.equals(x.child1))
                        x.child1 = null;
                    if(last.equals(x.child2))
                        x.child2 = null;
                }



            String parentNumber = s.readUTF();
            String parentName = s.readUTF();
            if (!"null".equals(parentName))
            {
                for (Node<String> x = first; x != null; x = x.next)
                    if (parentName.equals(x.item))
                        if ("1".equals(parentNumber))
                            x.child1 = last;
                        else
                            x.child2 = last;
            }
        }
    }

    @Override
    public Iterator<String> iterator()
    {
        return new IteratorSolution();
    }
    private class IteratorSolution implements Iterator<String>
    {
        public Node<String> current;
        int pos = 0;

        private IteratorSolution()
        {
            current = first;
        }
        @Override
        public boolean hasNext()
        {
            return pos < size;
        }
        @Override
        public String next()
        {
            Node<String> tmp = current;
            current = current.next;
            pos ++;
            return tmp.item;
        }
        @Override
        public void remove()
        {
            removeNode(current);
            current = first;
        }
    }


}

