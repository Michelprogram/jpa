package jub.web;

import com.google.gson.Gson;
import jub.model.Hero;
import jub.model.Weapon;
import jub.service.HeroService;
import jub.service.IHeroServicce;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HeroServlet extends HttpServlet {
    IHeroServicce service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        service = HeroService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");

        ServletOutputStream out = resp.getOutputStream();
        String[] params = new String[0];

        String response = "";



        try{
            params = req.getPathInfo().split("/");

        } catch (NullPointerException err){
            List<Hero> heros = service.getHeroes();

            for (Hero h: heros) {
                response += "{ " +
                        "\"id\" : \"" + h.getId() + "\"" +
                        ",\"name\" : \"" + h.getName() + "\"" +
                        ",\"slug\" : \"" + h.getSlug() + "\"" +
                        "},";
            }

            resp.setStatus(200);
            out.print("[" + response + "]");
        }


        if (params.length == 2){

            Hero hero = service.getHero(Integer.parseInt(params[1]));

            if (hero == null){
                resp.setStatus(404);
                response = "{ \"error\" : \"not found\"}";
                out.print(response);
                return;
            }

            response = "{" +
                    "id : '" + hero.getId() + "'" +
                    ",name : '" + hero.getName() + "'" +
                    ",slug : '" + hero.getSlug() + "'" +
                    ",kind : '" + "{" +
                    "id : '" +hero.getKind().getId()+ "'" +
                    ",name : '" + hero.getKind().getName() +"'}" +
                    ",weapons : '" + hero.getWeapons().toString() + "'" +
                "}";


            out.print(response);
        }


        else if(params.length == 3){
            List<Weapon> weapons = service.getWeapons(Integer.parseInt(params[1]));

            for (Weapon weapon: weapons) {
                response += "{" +
                        "\"id\" : \""+ weapon.getId() + "\"" +
                        ",\"name\" : \"" + weapon.getName() + "\"" +
                        "},";
            }

            out.print("["+ response +"]");
        }




    }
}
