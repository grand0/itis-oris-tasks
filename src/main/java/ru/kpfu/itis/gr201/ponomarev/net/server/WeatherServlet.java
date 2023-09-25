package ru.kpfu.itis.gr201.ponomarev.net.server;

import org.json.JSONObject;
import ru.kpfu.itis.gr201.ponomarev.net.client.HttpClient;
import ru.kpfu.itis.gr201.ponomarev.net.client.HttpClientImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

@WebServlet(name = "weatherServlet", urlPatterns = "/weather")
public class WeatherServlet extends HttpServlet {
    public static final String API_KEY = "71cff1300f38b123a2136622174ebe23";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession(false) == null || req.getParameter("city") == null) {
            resp.sendRedirect("/");
        } else {
            HttpClient http = new HttpClientImpl();
            String jsonResponse;
            try {
                jsonResponse = http.get("https://api.openweathermap.org/data/2.5/weather?q=" + req.getParameter("city") + "&units=metric&appid=" + API_KEY, null);
            } catch (FileNotFoundException e) {
                req.setAttribute("error", "City not found");
                req.getRequestDispatcher("/").forward(req, resp);
                return;
            } catch (Exception e) {
                req.setAttribute("error", "Unknown error");
                req.getRequestDispatcher("/").forward(req, resp);
                return;
            }

            JSONObject obj = new JSONObject(jsonResponse);
            String name = obj.getString("name");
            double temp = obj.getJSONObject("main").getDouble("temp");
            double humidity = obj.getJSONObject("main").getDouble("humidity");
            String weatherDescription = obj.getJSONArray("weather").getJSONObject(0).getString("description");

            req.setAttribute("city", name);
            req.setAttribute("temp", temp);
            req.setAttribute("humidity", humidity);
            req.setAttribute("weatherDescription", weatherDescription);
            req.getRequestDispatcher("weather.ftl").forward(req, resp);
        }
    }
}
