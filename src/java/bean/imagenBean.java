package bean;

import dao.Database;
import dao.ImagenDao;
import dao.ImagenDaoImpl;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Imagen;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Alex Rodriguez
 */
@Named(value = "imagenBean")
@RequestScoped
public class imagenBean{

    private Imagen selectedImagen;
    private List<Imagen> imagenes;
        
    private static final long serialVersionUID = 1L;
    private UploadedFile file; 
 
     public imagenBean() throws IOException {
        this.imagenes = new ArrayList<Imagen>();
        this.selectedImagen = new Imagen();
    }
    
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Imagen getSelectedImagen() {
        return selectedImagen;
    }

    public void setSelectedImagen(Imagen selectedImagen) {
        this.selectedImagen = selectedImagen;
    }

    public List<Imagen> getImagenes() {
        ImagenDao imagenDao = new ImagenDaoImpl();
        this.imagenes = imagenDao.findAll();
        return imagenes;
    }

    public void btnCreateImagen(ActionEvent event) {
        ImagenDao imagenDao = new ImagenDaoImpl();
        String msng;
        if (imagenDao.create(this.selectedImagen)) {
            msng = "Se registro correctamente la Imagen";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msng = "Error al registrar la Imagen";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnUpdateImagen(ActionEvent event){
        ImagenDao imagenDao = new ImagenDaoImpl();
       String msng;
       if(imagenDao.update(this.selectedImagen)){
           msng = "Se modificado correctamente la Imagen";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al modificar la Imagen";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
       }
    }
    
    public void btnDeleteImagen(ActionEvent event){
        ImagenDao imagenDao = new ImagenDaoImpl();
       String msng;
       if(imagenDao.delete(this.selectedImagen.getIdimagen())){
           msng = "Se elimino correctamente la Imagen";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al eliminar la Imagen";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
    }
    
    public void upload() {
        if (file != null) {
            try {
                InputStream fin2 = this.file.getInputstream();
                Connection con = Database.getConnection();
                PreparedStatement pre = con.prepareStatement("insert into imagen (foto,descripcion,orden,idextra) values(?,?,?,?)");
                pre.setBinaryStream(1, fin2,this.file.getSize());
                pre.setString(2, this.file.getFileName());
                pre.setInt(3, this.selectedImagen.getOrden());
                pre.setInt(4, this.selectedImagen.getExtra().getIdextra());
                pre.executeUpdate();
                pre.close();
                FacesMessage msg = new FacesMessage("Succesfuly", this.file.getFileName()+ " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
 
            } catch (IOException | SQLException e) {
                System.out.println("Exception-File Upload." + e.getMessage());
                FacesMessage msg = new FacesMessage("Error", this.file.getFileName() + " Exception-File Upload.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
        else{
        FacesMessage msg = new FacesMessage("Por favor seleccione una Imagen!!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
        }   
    }
    
    public void uploadFile(FileUploadEvent event){
        try {
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images");
            String archivo = path + File.separator + event.getFile().getFileName();
            
            FileOutputStream fileOutputStream = new FileOutputStream(archivo);
            byte[] bufer = new byte[this.selectedImagen.getFoto().length];
            int bulk;
            InputStream inputStream = event.getFile().getInputstream();
            while(true){
                bulk = inputStream.read(bufer);
                if(bulk < 0){
                    break;
                }
                fileOutputStream.write(bufer, 0, bulk);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();
            this.selectedImagen.setFoto(event.getFile().getContents());            
        } catch (Exception e) {
            System.out.println("Error al subir la Imagen"+e.getMessage());
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Error al subir la Imagen"));
        }
    }
     
}

