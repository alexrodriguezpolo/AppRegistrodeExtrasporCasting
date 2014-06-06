/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Usuario;

/**
 *
 * @author Alex Rodriguez
 */
@Named(value = "usuarioBean")
@RequestScoped
public class usuarioBean {
    
    private Usuario selectedUsuario;
    private List<Usuario> usuarios;
    
    public usuarioBean() {
        this.selectedUsuario = new Usuario();
        this.usuarios = new ArrayList<Usuario>();
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public List<Usuario> getUsuarios() {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        this.usuarios = usuarioDao.findAll();
        return usuarios;
    }
    
    public void btnCreateUsuario(ActionEvent event){
       UsuarioDao usuarioDao = new UsuarioDaoImpl();
       String msng;
       if(usuarioDao.create(this.selectedUsuario)){
           msng = "Se registro correctamente el Usuario";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al registrar el Usuario";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
       }
    }
    
    public void btnUpdateUsuario(ActionEvent event){
       UsuarioDao usuarioDao = new UsuarioDaoImpl();
       String msng;
       if(usuarioDao.update(this.selectedUsuario)){
           msng = "Se modificado correctamente el Usuario";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al modificar el Usuario";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
       }
    }
    
    public void btnDeleteUsuario(ActionEvent event){
       UsuarioDao usuarioDao = new UsuarioDaoImpl();
       String msng;
       if(usuarioDao.delete(this.selectedUsuario.getIdusuario())){
           msng = "Se elimino correctamente el Usuario";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al eliminar el Usuario";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
        
    }

}
