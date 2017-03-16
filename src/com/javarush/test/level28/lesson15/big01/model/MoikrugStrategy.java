package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 26.12.2016.
 */
public class MoikrugStrategy implements Strategy
{
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%d&q=java+%s";
    //private static final String URL_FORMAT = "http://javarush.ru/testdata/big28data2.html";

    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> vacancies = new ArrayList<>();
        int pageCount = 1;
        try
        {
            Document document = getDocument(searchString, pageCount);
            while (!document.getElementsByClass("job").isEmpty())
            {
                for (Element elementVacancy : document.getElementsByClass("job"))
                {
                    Vacancy vacancy = new Vacancy();
                    if (!elementVacancy.getElementsByClass("location").isEmpty())
                        vacancy.setCity(elementVacancy.getElementsByClass("location").first().text());
                    else
                        vacancy.setCity("");
                    vacancy.setCompanyName(elementVacancy.getElementsByClass("company_name").first().text());
                    vacancy.setTitle(elementVacancy.getElementsByClass("title").first().text());
                    if (!elementVacancy.getElementsByClass("count").isEmpty())
                        vacancy.setSalary(elementVacancy.getElementsByClass("count").first().text());
                    else
                        vacancy.setSalary("");
                    vacancy.setUrl("https://moikrug.ru" + elementVacancy.getElementsByClass("title").select("a").attr("href").toString());
                    vacancy.setSiteName("https://moikrug.ru");
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
        String url = String.format(URL_FORMAT, page, searchString);
        Document document = Jsoup.connect(url).userAgent("Mozilla").referrer("http://google.ru").get();

        return document;
    }


}
