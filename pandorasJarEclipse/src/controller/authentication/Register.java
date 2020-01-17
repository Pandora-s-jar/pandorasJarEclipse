package controller.authentication;

import com.google.gson.Gson;
import model.User;
import persistence.DAOFactory;
import utility.CaptchaResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


@WebServlet(value = "/register", name = "register")
public class Register extends HttpServlet {

    private boolean checkCaptcha(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String url = "https://www.google.com/recaptcha/api/siteverify",
                params = "secret=" + "6Le4Nc4UAAAAAK4eyf3u6AghrE6Ql0ZChYu_wIzZ" + "&response=" + req.getParameter("g-recaptcha-response");
        HttpURLConnection http = (HttpURLConnection) new URL(url).openConnection();
        http.setDoOutput(true);
        http.setRequestMethod("POST");
        http.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded; charset=UTF-8");
        OutputStream out = http.getOutputStream();
        out.write(params.getBytes("UTF-8"));
        out.flush();
        out.close();

        InputStream res = http.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(res, "UTF-8"));

        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        Gson gson = new Gson();
        CaptchaResponse captchaResponse = gson.fromJson(sb.toString(), CaptchaResponse.class);
        res.close();
        return captchaResponse.isSuccess();
    }

    private boolean checkUniqueEmail(HttpServletRequest req){
        User user = DAOFactory.getInstance().makeUserDAO().getUserByEmail(req.getParameter("email"));
        return user.getEmail() == null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("header.jsp");
        requestDispatcher.include(req, resp);
        requestDispatcher = req.getRequestDispatcher("register.html");
        requestDispatcher.include(req, resp);
        requestDispatcher = req.getRequestDispatcher("footer.html");
        requestDispatcher.include(req, resp);
        if(req.getSession().getAttribute("attempts") == null){
            req.getSession().setAttribute("attempts", 0);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkCaptcha(req, resp)){
            if(checkUniqueEmail(req)){
                HttpSession session = req.getSession();
                session.setAttribute("nextPage", "/register/insertDatabase");
                session.setAttribute("previousPage", "/register");
                session.setAttribute("email", req.getParameter("email"));
                session.setAttribute("username", req.getParameter("username"));
                session.setAttribute("password", req.getParameter("password"));
                resp.sendRedirect("/sendCode");
            }
            else{
                resp.sendRedirect("/register?emailAlreadyExists=true"); //SI SCRIVE COSI?
            }
        }
        else{
            int cont = (int) req.getSession().getAttribute("attempts");
            req.getSession().setAttribute("attempts", ++cont);
            if(cont == 3){
                req.getSession().setAttribute("attempts", 0);
                resp.sendRedirect("http://www.google.it");
            }
            else{
                resp.sendRedirect("/register?captcha=false");
            }
        }
    }

}
