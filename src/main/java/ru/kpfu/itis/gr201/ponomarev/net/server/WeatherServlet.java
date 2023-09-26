package ru.kpfu.itis.gr201.ponomarev.net.server;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.gr201.ponomarev.net.client.HttpClient;
import ru.kpfu.itis.gr201.ponomarev.net.client.HttpClientImpl;
import ru.kpfu.itis.gr201.ponomarev.net.dto.WeatherDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "weatherServlet", urlPatterns = "/weather")
public class WeatherServlet extends HttpServlet {
    public static final String API_KEY = "71cff1300f38b123a2136622174ebe23";

    public static final Logger LOGGER = LoggerFactory.getLogger(WeatherServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("city") == null) {
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

            WeatherDto weather = new WeatherDto(name, temp, humidity, weatherDescription);

            LOGGER.info("User {} requested weather for {} at {}", req.getSession().getAttribute("username"), name, LocalDateTime.now());

            req.setAttribute("weather", weather);
            req.getRequestDispatcher("weather.ftl").forward(req, resp);
        }
    }
}
