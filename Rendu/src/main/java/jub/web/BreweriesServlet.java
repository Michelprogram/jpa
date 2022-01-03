package jub.web;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import jub.model.Brewery;
import jub.model.Error;
import jub.service.BreweriesService;
import jub.service.IBreweriesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


public class BreweriesServlet extends HttpServlet {
    IBreweriesService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        service = BreweriesService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = resp.getOutputStream();
        List<Brewery> breweries = service.getBreweries();

        Gson gson = new Gson();
       String json = gson.toJson(breweries);
       out.print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = resp.getOutputStream();
        Gson gson = new Gson();
        StringBuilder jb = new StringBuilder();
        String line ;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            out.print(gson.toJson(new Error("request error")));
            resp.setStatus(203);
            return;
        }
        Brewery brewery;
        try {
            brewery = gson.fromJson(jb.toString(), Brewery.class);
        } catch (JsonSyntaxException error){
            out.print(gson.toJson(new Error("request error")));
            resp.setStatus(203);
            return;
        }
        if (brewery.getId()==null || brewery.getAddress()==null || brewery.getCity()==null || brewery.getCountry()==null
                || brewery.getDescription() ==null || brewery.getName()==null){
            out.print(gson.toJson(new Error("request error")));
            resp.setStatus(203);
            return;
        }
        if (!service.addBrewery(brewery)) {
            out.print(gson.toJson(new Error("already exists")));
            resp.setStatus(203);
            return;
         }
        out.print(gson.toJson(brewery));
        resp.setStatus(201);
    }


}
