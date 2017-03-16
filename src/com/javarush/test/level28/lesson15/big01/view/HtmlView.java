package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Дмитрий on 23.12.2016.
 */
public class HtmlView implements View
{
    Controller controller;
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";


    @Override
    public void update(List<Vacancy> vacancies)
    {
        try
        {
            updateFile(getUpdatedFileContent(vacancies));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod()
    {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) throws IOException
    {
        Document document = getDocument();

        Element template = document.getElementsByClass("template").first().clone();
        template.removeAttr("style");
        template.removeClass("template");
        for (Element element : document.getElementsByClass("vacancy"))
            if (!element.hasClass("template"))
                element.remove();

        for (Vacancy vacancy : vacancies)
        {
            Element element = template.clone();
            element.getElementsByClass("city").append(vacancy.getCity());
            element.getElementsByClass("companyName").append(vacancy.getCompanyName());
            element.getElementsByClass("salary").append(vacancy.getSalary());
            element.getElementsByClass("title").select("a").append(vacancy.getTitle());
            element.getElementsByClass("title").select("a").attr("href", vacancy.getUrl());

            document.select("table tbody").append(element.outerHtml());
        }
        document.select("table tbody").append(document.select("table tbody tr").get(1).outerHtml());
        document.select("table tbody tr").get(1).remove();

        return document.outerHtml();
    }

    private void updateFile(String string) throws IOException
    {
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(filePath));
        output.write(string.getBytes());
        output.close();
    }

    protected Document getDocument() throws IOException
    {
        File input = new File(filePath);
        Document document = Jsoup.parse(input, "UTF-8");

        return  document;
    }

}
