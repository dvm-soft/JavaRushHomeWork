package com.javarush.test.level40.lesson04.task01;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* POST, а не GET
Исправь ошибки в методе sendPost, чтобы он отправлял POST-запрос с переданными параметрами.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
//        solution.sendPost(new URL("http://requestb.in/1h4qhvv1"), "name=zapp&mood=good&locale=&id=777");
        solution.sendPost(new URL("http://javarush.ru/course.html1"), "v=8");
    }

    public void sendPost(URL url, String urlParameters) throws Exception {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        os.write(urlParameters.getBytes());
        os.close();

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String responseLine;
        StringBuilder response = new StringBuilder();

        while ((responseLine = bufferedReader.readLine()) != null)
        {
            response.append(responseLine);
        }
        bufferedReader.close();

        System.out.println("Response: " + response.toString());

        bufferedReader.close();
        connection.disconnect();

    }
}
