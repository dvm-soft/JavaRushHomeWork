package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.HHStrategy;
import com.javarush.test.level28.lesson15.big01.model.Model;
import com.javarush.test.level28.lesson15.big01.model.MoikrugStrategy;
import com.javarush.test.level28.lesson15.big01.model.Provider;
import com.javarush.test.level28.lesson15.big01.view.HtmlView;
import com.javarush.test.level28.lesson15.big01.view.View;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.util.List;

/**
 * Created by Дмитрий on 21.12.2016.
 */
public class Aggregator
{
    public static void main(String[] args)
    {
        Provider moikrugProvider = new Provider(new MoikrugStrategy());
        Provider HHProvider = new Provider(new HHStrategy());
        View view = new HtmlView();
        Model model = new Model(view, new Provider[] {moikrugProvider, HHProvider} );
        Controller controller = new Controller(model);
        view.setController(controller);
        ((HtmlView)view).userCitySelectEmulationMethod();

    }
}
