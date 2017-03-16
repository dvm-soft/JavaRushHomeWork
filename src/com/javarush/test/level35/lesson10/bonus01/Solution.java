package com.javarush.test.level35.lesson10.bonus01;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/* ClassLoader - что это такое?
Реализуйте логику метода getAllAnimals.
Аргумент метода pathToAnimals - это абсолютный путь к директории, в которой хранятся скомпилированные классы.
Путь не обязательно содержит / в конце.
НЕ все классы наследуются от интерфейса Animal.
НЕ все классы имеют публичный конструктор без параметров.
Только для классов, которые наследуются от Animal и имеют публичный конструктор без параметров, - создать по одному объекту.
Добавить созданные объекты в результирующий сет и вернуть.
Метод main не участвует в тестировании.
*/
public class Solution extends ClassLoader{
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals("C://pathToClasses/");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {

        Set<Animal> set = new HashSet<Animal>();


        if (!pathToAnimals.endsWith("/"))
            pathToAnimals = pathToAnimals + "/";
        try
        {
            File dir = new File(pathToAnimals);
            String[] modules = dir.list();

            for (String classFileName : modules)
            {
                if (!classFileName.endsWith(".class"))
                    continue;
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream( pathToAnimals + classFileName));
                byte[] bytes = new byte [bis.available()];
                bis.read(bytes);
                Class clazz = new ModuleLoader().getClass(bytes, null);
                //System.out.println(clazz.getSimpleName());

                boolean hasInterfaceAnimal = false;
                Class[] interfaces = clazz.getInterfaces();
                for (Class inter : interfaces)
                {
                    //System.out.println(" - " + inter.getSimpleName());
                    if (Animal.class.equals(inter))
                        hasInterfaceAnimal = true;
                }

                boolean isEmptyParamContructor = false;
                Constructor[] constructors = clazz.getConstructors();
                for (Constructor constr : constructors)
                {
                    if (Modifier.isPublic(constr.getModifiers()) && constr.getParameterTypes().length == 0)
                        isEmptyParamContructor = true;
                    //System.out.println(" конструктор " + constr.getName() + "параметров - "+ constr.getParameterTypes().length);
                }

                if (isEmptyParamContructor & hasInterfaceAnimal)
                    set.add((Animal) clazz.newInstance());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return set;
    }

}

class ModuleLoader extends ClassLoader
{
    public ModuleLoader()
    {
    }

    public Class getClass(byte[] bytes, String name)
    {
        return defineClass(name, bytes, 0, bytes.length);
    }
}
