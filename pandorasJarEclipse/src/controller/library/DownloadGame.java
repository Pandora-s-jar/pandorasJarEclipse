package controller.library;

import persistence.DAOFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(value = "/downloadGame", name = "downloadGame")
public class DownloadGame extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gameName = DAOFactory.getInstance().makeGameDAO().getGameById(Integer.parseInt(req.getParameter("id"))).getName();
        String directory = this.getServletContext().getRealPath(File.separator);
        directory += File.separator+"gameFiles"+File.separator+gameName;
        System.out.println(directory);
        String filePath = "";
        File f = new File(directory);
        System.out.println(f.exists());
        for (File e: f.listFiles()) {
            if (e.getName().contains(".jar")){
                filePath = e.getAbsolutePath();
            }
        }
        File download = new File(filePath);
        FileInputStream inStream = new FileInputStream(download);
        ServletContext context = getServletContext();

        // gets MIME type of the file
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        resp.setContentType(mimeType);
        resp.setContentLength((int) download.length());
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", download.getName());
        resp.setHeader(headerKey, headerValue);
        OutputStream outStream = resp.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        inStream.close();
        outStream.close();
    }
}
