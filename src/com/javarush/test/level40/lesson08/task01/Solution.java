package com.javarush.test.level40.lesson08.task01;

import java.io.*;
import java.net.*;

/* Отправка GET-запроса через сокет
Перепиши реализацию метода getSite, он должен явно создавать и использовать сокетное соединение Socket с сервером.
Адрес сервера и параметры для GET-запроса получи из параметра url.
Порт используй дефолтный для http.
Классы HttpURLConnection, HttpClient и т.д. не использовать.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url)
    {
        try
        {
            String host = url.getHost();

            String path = url.getPath();
            //System.out.println("Path: " + path);
            if (path == null || path.length() == 0)
            {
                path = "/";
            }

            String query = url.toURI().getRawQuery();
            if (query != null && query.length() > 0)
            {
                path += "?" + query;
            }

            //System.out.println("Query: " + query);

            int port = url.getDefaultPort();

            try (Socket socket = new Socket(url.getHost(), port);
                 OutputStream out = socket.getOutputStream();
                 InputStreamReader in = new InputStreamReader(socket.getInputStream());
                 BufferedReader bufferedReader = new BufferedReader(in))
            {
                String requestText = "GET " + path + " HTTP/1.1\n" +
                        "Host: " + host + "\n" +
                        "Connection: close\n\n";

                out.write(requestText.getBytes());
                out.flush();

                String responseLine;

                while ((responseLine = bufferedReader.readLine()) != null)
                    System.out.println(responseLine);

                socket.shutdownInput();
                socket.shutdownOutput();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}