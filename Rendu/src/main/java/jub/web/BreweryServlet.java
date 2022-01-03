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


public class BreweryServlet extends HttpServlet {
    IBreweriesService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        service = BreweriesService.getInstance();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = resp.getOutputStream();
        String[] params = req.getPathInfo().split("/");
        Gson gson = new Gson();
        if (params.length!=2) {
            out.print(gson.toJson(new Error("not found")));
            resp.setStatus(404);
            return;
        }
        Integer id;
        try {
            id = Integer.parseInt(params[1]);
        } catch (NumberFormatException e) {
            out.print(gson.toJson(new Error("request error")));
            resp.setStatus(203);
            return;
        }
        Brewery brewery = BreweriesService.getInstance().getBrewery(id);
        if (brewery==null){
            out.print(gson.toJson(new Error("not exists")));
            resp.setStatus(203);
            return;
        }
        if (!service.removeBrewery(brewery)){
            out.print(gson.toJson(new Error("request error")));
            resp.setStatus(203);
            return;
        }
        out.print(gson.toJson(brewery));
        resp.setStatus(200);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = resp.getOutputStream();
        String[] params = req.getPathInfo().split("/");
        Gson gson = new Gson();
        if (params.length!=2) {
            out.print(gson.toJson(new Error("not found")));
            resp.setStatus(404);
            return;
        }
        Integer id;
        try {
            id = Integer.parseInt(params[1]);
        } catch (NumberFormatException e) {
            out.print(gson.toJson(new Error("request error")));
            resp.setStatus(203);
            return;
        }
        Brewery brewery = service.getBrewery(id);
        if (brewery==null) {
            out.print(gson.toJson(new Error("not found")));
            resp.setStatus(404);
            return;
        }
        String json = gson.toJson(brewery);
        out.print(json);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = resp.getOutputStream();
        String[] params = req.getPathInfo().split("/");
        Gson gson = new Gson();
        if (params.length!=2) {
            out.print(gson.toJson(new Error("not found")));
            resp.setStatus(404);
            return;
        }
        Integer id;
        try {
            id = Integer.parseInt(params[1]);
        } catch (NumberFormatException e) {
            out.print(gson.toJson(new Error("request error")));
            resp.setStatus(203);
            return;
        }
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
        if (id !=brewery.getId()) {
            out.print(gson.toJson(new Error("request error")));
            resp.setStatus(203);
            return;
        }
        if (service.getBrewery(id)==null){
            out.print(gson.toJson(new Error("not exists")));
            resp.setStatus(203);
            return;
        }
        if (!service.updateBrewery(brewery)){
            out.print(gson.toJson(new Error("request error")));
            resp.setStatus(203);
        }
        out.print(gson.toJson(brewery));
        resp.setStatus(200);
    }
}
