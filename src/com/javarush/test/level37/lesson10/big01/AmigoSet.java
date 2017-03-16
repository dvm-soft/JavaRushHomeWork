package com.javarush.test.level37.lesson10.big01;


import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Дмитрий on 01.02.2017.
 */
public class AmigoSet<E> extends AbstractSet implements Serializable, Cloneable
{
    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet()
    {
        map = new HashMap<E, Object>();
    }

    public AmigoSet(Collection<? extends E> collection)
    {
        map = new HashMap<E, Object>((int) Math.max(collection.size()/.75f, 16));
        this.addAll(collection);
    }

    @Override
    public Iterator iterator()
    {
        return map.keySet().iterator();
    }

    @Override
    public int size()
    {
        return map.size();
    }

    @Override
    public boolean add(Object e)
    {
        return null == map.put((E) e, PRESENT);
    }

    @Override
    public boolean isEmpty()
    {
        return size() == 0;
    }

    @Override
    public void clear()
    {
        map.clear();
    }

    @Override
    public boolean remove(Object e)
    {
        return PRESENT == map.remove((E) e);
    }

    @Override
    public boolean contains(Object e)
    {
        return map.containsKey((E) e);
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return new AmigoSet<E>(map.keySet());
    }

    private void writeObject(java.io.ObjectOutputStream s) throws IOException
    {
        s.defaultWriteObject();

        s.writeInt(map.size());
        for(E e:map.keySet()){
            s.writeObject(e);
        }
        s.writeInt((int) HashMapReflectionHelper.callHiddenMethod(map,"capacity"));
        s.writeFloat((float) HashMapReflectionHelper.callHiddenMethod(map,"loadFactor"));
    }

    private void readObject(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException
    {
            s.defaultReadObject();
            int size = s.readInt();
            Set<E> set = new HashSet<>();
            for (int i = 0; i < size; i++)
                set.add((E) s.readObject());
            int capacity = s.readInt();
            float loadFactor = s.readFloat();

            map = new HashMap<>(capacity, loadFactor);
            this.addAll(set);

    }

}


