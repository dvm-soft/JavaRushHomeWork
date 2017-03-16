package com.javarush.test.level36.lesson04.big01.view;

import com.javarush.test.level36.lesson04.big01.controller.Controller;
import com.javarush.test.level36.lesson04.big01.model.ModelData;

/**
 * Created by 1 on 29.01.2017.
 */
public interface View
{
    void refresh(ModelData modelData);
    void setController(Controller controller);
}
