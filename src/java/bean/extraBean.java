/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import dao.ExtraDao;
import dao.ExtraDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import model.Extra;

/**
 *
 * @author Alex Rodriguez
 */
@Named(value = "extraBean")
@RequestScoped
public class extraBean {
    private Extra selectedExtra;
    private List<Extra> extras;
    private List<SelectItem> selectOneItemsExtra;
    
    public extraBean() {
        this.extras = new ArrayList<Extra>();
        this.selectedExtra = new Extra();
    }

    public List<SelectItem> getSelectOneItemsExtra() {
        this.selectOneItemsExtra = new ArrayList<SelectItem>();
        ExtraDao extraDao = new ExtraDaoImpl();
        extras = extraDao.selectItems();
        for (Extra extra : extras) {
            SelectItem selectItem = new SelectItem(extra.getIdextra(), extra.getNombres());
            this.selectOneItemsExtra.add(selectItem);
        }
        return selectOneItemsExtra;
    }
    
    

    public Extra getSelectedExtra() {
        return selectedExtra;
    }
    
    public void setSelectedExtra(Extra selectedExtra) {
        this.selectedExtra = selectedExtra;
    }

    public List<Extra> getExtras() {
        ExtraDao extraDao = new ExtraDaoImpl();
        this.extras = extraDao.findAll();
        return extras;
    }
    
    public void btnCreateExtra(ActionEvent event){
       ExtraDao extraDao = new ExtraDaoImpl();
       String msng;
       if(extraDao.create(this.selectedExtra)){
           msng = "Se registro correctamente el Extra";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al registrar el Extra";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
       }
    }
    
    public void btnUpdateExtra(ActionEvent event){
       ExtraDao extraDao = new ExtraDaoImpl();
       String msng;
       if(extraDao.update(this.selectedExtra)){
           msng = "Se modificado correctamente el Extra";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al modificar el Extra";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
       }
    }
    
    public void btnDeleteExtra(ActionEvent event){
       ExtraDao extraDao = new ExtraDaoImpl();
       String msng;
       if(extraDao.delete(this.selectedExtra.getIdextra())){
           msng = "Se elimino correctamente el Extra";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al eliminar el Extra";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
        
    }

}
