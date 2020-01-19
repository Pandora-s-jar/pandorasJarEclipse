package controller.upload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/upload", name = "upload")
public class Upload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("header.jsp");
        requestDispatcher.include(req, resp);
        if(req.getSession().getAttribute("logged") == null || !(Boolean) req.getSession().getAttribute("logged")){
            requestDispatcher = req.getRequestDispatcher("errorNotLogged.html");
        }
        else{
            requestDispatcher = req.getRequestDispatcher("upload.html");
        }
        requestDispatcher.include(req, resp);
        requestDispatcher = req.getRequestDispatcher("footer.html");
        requestDispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String paymentCoords = req.getParameter("paymentEmail");
        req.getSession().setAttribute("helpEmail", email);
        req.getSession().setAttribute("paymentCoords", paymentCoords);
        req.getSession().setAttribute("nextPage", "/formGameUpload");
        req.getSession().setAttribute("previousPage", "/upload");
        req.getRequestDispatcher("/sendCode").forward(req, resp);
    }
}
