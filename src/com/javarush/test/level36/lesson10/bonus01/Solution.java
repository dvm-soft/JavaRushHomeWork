package com.javarush.test.level36.lesson10.bonus01;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/* Осваиваем ClassLoader и Reflection
Аргументом для класса Solution является абсолютный путь к пакету,
например, "C:\JavaRushHomeWork\src\com\javarush\test\level36\lesson10\bonus01\data\second".
Имя пакета может содержать File.separator.
В этом пакете находятся только скомпилированные классы.
Известно, что каждый класс имеет конструктор без параметров и реализует интерфейс HiddenClass.
Считайте все классы с файловой системы, создайте фабрику - реализуйте метод getHiddenClassObjectByKey.
Известно, что есть только один класс, простое имя которого начинается с String key без учета регистра.
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;
    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution("C:\\JavaRushHomeWork\\src\\com\\javarush\\test\\level36\\lesson10\\bonus01\\data\\second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        try
        {
            File dir = new File(packageName);
            String[] modules = dir.list();

            for (String classFileName : modules)
            {
                if (!classFileName.endsWith(".class"))
                    continue;
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(packageName + File.separator + classFileName));
                byte[] bytes = new byte[bis.available()];
                bis.read(bytes);
                Class clazz = new ModuleLoader().getClass(bytes, null);
                //System.out.println(clazz.getSimpleName());

                boolean hasInterfaceAnimal = false;
                Class[] interfaces = clazz.getInterfaces();
                for (Class inter : interfaces)
                {
                    //System.out.println(" - " + inter.getSimpleName());
                    if (HiddenClass.class.equals(inter))
                        hasInterfaceAnimal = true;
                }

                boolean isEmptyParamContructor = false;
                Constructor[] constructors = clazz.getConstructors();
                for (Constructor constr : constructors)
                {
                    if (constr.getParameterTypes().length == 0)
                        isEmptyParamContructor = true;
                    //System.out.println(" конструктор " + constr.getName() + "параметров - "+ constr.getParameterTypes().length);
                }

                //if (isEmptyParamContructor & hasInterfaceAnimal)
                    hiddenClasses.add(clazz);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public HiddenClass getHiddenClassObjectByKey(String key) {

        for(Class clazz: hiddenClasses){
            if(clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())){
                try {
                    Constructor[] constructors = clazz.getDeclaredConstructors();
                    for(Constructor constructor: constructors){
                        if(constructor.getParameterTypes().length==0){
                            constructor.setAccessible(true);
                            return (HiddenClass) constructor.newInstance(null);
                        }
                    }
                }
                catch (InstantiationException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
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

