package ru.kpfu.itis.gr201.ponomarev.net.server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.stream.Collectors;

@WebServlet(name = "HelloServlet", urlPatterns = "/")
public class HelloServlet extends HttpServlet {
    private static final String CONTENT = "<!DOCTYPE html><u>Hello <i>world</i>!</u><br><img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Gregory_and_watermelon.jpg/274px-Gregory_and_watermelon.jpg\">";

    private void printRequest(HttpServletRequest req) throws IOException {
        System.out.println("START OF REQUEST");

        System.out.println("PARAMETERS:");
        req.getParameterMap().forEach((key, value) -> System.out.println(key + "=" + paramValuesToString(value)));

        System.out.println("HEADERS:");
        Enumeration<String> e = req.getHeaderNames();
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            System.out.println(name + ": " + req.getHeader(name));
        }

        System.out.println("BODY:");
        String body = req.getReader().lines().collect(Collectors.joining("\n"));
        System.out.println(body);

        System.out.println("END OF REQUEST");
    }

    private String paramValuesToString(String[] values) {
        if (values.length == 0) return "";
        else if (values.length == 1) return values[0];
        else return Arrays.toString(values);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printRequest(req);

        PrintWriter pw = resp.getWriter();
        pw.println(CONTENT + "<br>You called GET");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printRequest(req);

        PrintWriter pw = resp.getWriter();
        pw.println(CONTENT + "<br>You called POST");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printRequest(req);

        PrintWriter pw = resp.getWriter();
        pw.println(CONTENT + "<br>You called PUT");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printRequest(req);

        PrintWriter pw = resp.getWriter();
        pw.println(CONTENT + "<br>You called DELETE");
    }
}
