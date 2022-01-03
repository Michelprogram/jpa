package jub.web;

import jub.service.BreweriesService;
import jub.service.IBreweriesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UtilServlet extends HttpServlet {
    IBreweriesService service;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        service = BreweriesService.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        service.init();
        ServletOutputStream out = resp.getOutputStream();
        out.print("{\"message\":\"reset\"}");
    }
}
