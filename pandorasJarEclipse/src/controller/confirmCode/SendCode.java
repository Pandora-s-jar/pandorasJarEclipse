package controller.confirmCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(value = "/sendCode", name = "sendCode")
public class SendCode extends HttpServlet {

    private String generateCode(){
        int leftLimit = 65; // 'A'
        int rightLimit = 90; // 'Z'
        int strLenght = 8;
        StringBuilder builder = new StringBuilder(strLenght);
        for (int i=0; i<strLenght; i++){
            int random = ThreadLocalRandom.current().nextInt(leftLimit, rightLimit+1);
            builder.append((char)random);
        }
        return builder.toString();
    }

    private void sendEmail(HttpSession session, HttpServletResponse resp, String emailTo) throws IOException {
        String secretCode = this.generateCode();
        this.log(secretCode); //TODO: delete
        session.setAttribute("secretCode", secretCode);
        //new Thread(new CodeSender(secretCode, emailTo)).start();
        resp.sendRedirect("/controlCode");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getSession().getAttribute("email");
        sendEmail(req.getSession(), resp, email);
        resp.setStatus(201);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = req.getParameter("email");
        session.setAttribute("email", email);
        sendEmail(session, resp, email);
    }
}
