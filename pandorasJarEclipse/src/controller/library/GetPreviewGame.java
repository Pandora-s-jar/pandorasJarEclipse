package controller.library;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Objects;

@WebServlet(value = "/getPreviewGame", name = "getPreviewGame")
public class GetPreviewGame extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<String> files= new ArrayList<>();
        String game = req.getParameter("name");
        File directory = new File(this.getServletContext().getRealPath(File.separator)+File.separator+"gameFiles"+File.separator+game);
        this.log(String.valueOf(directory.exists()));
        for (File f : Objects.requireNonNull(directory.listFiles())){
            if (f.isDirectory()){
                for (File k : Objects.requireNonNull(f.listFiles())){
                    byte[] bytes = Files.readAllBytes(k.toPath());
                    String encoding = Base64.getEncoder().encodeToString(bytes);
                    files.add(encoding);
                }
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(files);
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("application/json");
        printWriter.print(json);
        printWriter.flush();
        printWriter.close();
    }
}
