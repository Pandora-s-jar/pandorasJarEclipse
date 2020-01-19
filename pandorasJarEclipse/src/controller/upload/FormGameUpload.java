package controller.upload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Game;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import persistence.DAOFactory;

@WebServlet(value = "/formGameUpload", name = "formGameUpload")
public class FormGameUpload extends HttpServlet {
    private boolean isMultipart;
    private String filePath;
    private File file;

    @Override
    public void init() throws ServletException {
        filePath = this.getServletContext().getRealPath(File.separator) + File.separator + "gameFiles";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("header.jsp");
        requestDispatcher.include(req, resp);
        if (req.getSession().getAttribute("logged") == null || !(Boolean) req.getSession().getAttribute("logged")) {
            requestDispatcher = req.getRequestDispatcher("errorNotLogged.html");
        } else {
            requestDispatcher = req.getRequestDispatcher("formGameUpload.html");
        }
        requestDispatcher.include(req, resp);
        requestDispatcher = req.getRequestDispatcher("footer.html");
        requestDispatcher.include(req, resp);
    }

    private void storeGameFile(FileItem item, String gameTitle) throws Exception {
        storeFile(item, gameTitle);
    }

    private void storeGamePreview(FileItem item, String gameTitle) throws Exception {
        storeFile(item, gameTitle);
    }

    private void storeGamePreviewImg(FileItem item, String gameTitle) throws Exception {
        storeFile(item, gameTitle + File.separator + "images");
    }

    private void storeGamePreviewVideo(FileItem item, String gameTitle) throws Exception {
        storeFile(item, gameTitle + File.separator + "video");
    }

    private void storeFile(FileItem item, String directory) throws Exception {
        String name = item.getName();
        file = new File(filePath + File.separator + directory + File.separator + name);
        item.write(file);
        this.log("Uploaded: " + file.getName());
    }

    private Game handleInsertGame(HttpServletRequest req) throws Exception {
        String name = "";
        String description="";
        String specs="";
        double price=0;
        String tag="";
        FileItem previewImage = null;
        ArrayList<FileItem> images = new ArrayList<>();
        ArrayList<FileItem> videos = new ArrayList<>();
        ArrayList<String> externalLinks = new ArrayList<>();

        FileItem jarFile = null;
        this.isMultipart = ServletFileUpload.isMultipartContent(req);
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        try {
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);
            for (FileItem i : fileItems) {
                if (i.getFieldName().equals("gameFile")) {
                    jarFile = i;
                } else if (i.getFieldName().equals("previewFiles")) {
                    String contentType = i.getContentType();
                    if (contentType.contains("image")) {
                        images.add(i);
                    } else if (contentType.contains("video")) {
                        videos.add(i);
                    }
                } else if (i.getFieldName().equals("previewImage")) {
                    previewImage = i;
                    this.log("previewImage");
                } else if (i.getFieldName().equals("name")) {
                    name = i.getString();
                } else if (i.getFieldName().equals("description")) {
                    description = i.getString();
                } else if (i.getFieldName().equals("specs")) {
                    specs = i.getString();
                } else if (i.getFieldName().equals("price")) {
                    price = Double.parseDouble(i.getString());
                } else if (i.getFieldName().contains("link")) {
                    externalLinks.add(i.getString());
                } else if (i.getFieldName().contains("tag")) {
                    tag = i.getString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Game g = DAOFactory.getInstance().makeGameDAO().getGameByName(name);
        if (g.getId() == 0) {
            DAOFactory.getInstance().makeGameDAO().insertGame(0, name, (Integer) req.getSession().getAttribute("userId"),
                    tag, (String)req.getSession().getAttribute("helpEmail"), price,
                    (String)req.getSession().getAttribute("paymentCoords"),description+specs);
            g = DAOFactory.getInstance().makeGameDAO().getGameByName(name);
            this.storeGamePreview(previewImage, name);
            DAOFactory.getInstance().makeGameDAO().insertPreview(g.getId(), previewImage.getName(), true);
            this.storeGameFile(jarFile, name);
            for (FileItem img : images) {
                this.storeGamePreviewImg(img, name);
                DAOFactory.getInstance().makeGameDAO().insertPreview(g.getId(), img.getName(), false);
            }
            for (FileItem video : videos) {
                this.storeGamePreviewVideo(video, name);
                DAOFactory.getInstance().makeGameDAO().insertPreview(g.getId(), video.getName(), false);
            }
            for (String s : externalLinks){
                DAOFactory.getInstance().makeGameDAO().insertVideoLink(g.getId(), s);
            }
        }
        return g;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Game g=new Game();
        try {
            g = handleInsertGame(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/gamePage?id=" + g.getId());
    }
}
