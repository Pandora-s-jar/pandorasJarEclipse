package controller.auth.forgot;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(value = "/sendForget")
public class SendForgotCode extends HttpServlet {
    private String generateCode(){
        int leftLimit = 97; // 'a'
        int rightLimit = 122; // 'z'
        int strLenght = 8; //TODO: troppo lunga?
        StringBuilder builder = new StringBuilder(strLenght);
        for (int i=0; i<strLenght; i++){
            int random = ThreadLocalRandom.current().nextInt(leftLimit, rightLimit+1);
            builder.append((char)random);
        }
        return builder.toString();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = req.getParameter("email");
        String secretCode = this.generateCode();
        this.log(secretCode);
        session.setAttribute("userEmail", email);
        session.setAttribute("secretCode", secretCode);
        //TODO: send email with session secretCode
        resp.setStatus(200);
    }
}
