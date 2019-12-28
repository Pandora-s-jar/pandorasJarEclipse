package controller.upload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
@WebServlet("/uploadGame")
public class GameUpload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // guide to: https://www.tutorialspoint.com/servlets/servlets-file-uploading.htm
        //TODO: Upload and retrieve the gameID

        int gameId = 2;
        resp.sendRedirect("/gamePage?id="+gameId);
    }
}
