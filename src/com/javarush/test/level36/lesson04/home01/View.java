package com.javarush.test.level36.lesson04.home01;

/**
 * Created by Дмитрий on 30.01.2017.
 */
public class View
{
    private Controller controller = new Controller();


    public void fireEventShowData() {
        System.out.println(controller.onDataListShow());
    }
}
