/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Nikola
 */
@ManagedBean (name="uplCtrl")
@RequestScoped
public class UploadController {
    
    private static Part file;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    public static String upload(String uusername) {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\resources\\images\\";
        try {
            InputStream in = file.getInputStream();
            byte[] data = new byte[in.available()];
            in.read(data);
            FileOutputStream out = new FileOutputStream(new File(path + uusername + ".jpg"));
            out.write(data);
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Uspesno!";
    }
    
    
}
