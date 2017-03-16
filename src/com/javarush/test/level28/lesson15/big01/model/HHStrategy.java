package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 1 on 21.12.2016.
 */
public class HHStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> vacancies = new ArrayList<>();
        int pageCount = 0;
        try
        {
            Document document = getDocument(searchString, pageCount);
            while (!document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy").isEmpty())
            {
                for (Element elementVacancy : document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy"))
                {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setCity(elementVacancy.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").first().text());
                    vacancy.setCompanyName(elementVacancy.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").first().text());
                    vacancy.setTitle(elementVacancy.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").first().text());
                    if (!elementVacancy.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").isEmpty())
                        vacancy.setSalary(elementVacancy.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").first().text());
                    else
                        vacancy.setSalary("");
                    vacancy.setUrl(elementVacancy.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").first().attr("href"));
                    vacancy.setSiteName("http://hh.ua");
                    vacancies.add(vacancy);
                }
                document = getDocument(searchString, ++pageCount);
            }
        } catch (IOException e)
        {
        }
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException
    {
        String url = String.format(URL_FORMAT, searchString, page);
        Document document = Jsoup.connect(url).userAgent("Mozilla").referrer("http://google.ru").get();

        return document;
    }
}
