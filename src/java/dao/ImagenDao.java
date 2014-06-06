/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Imagen;

/**
 *
 * @author Alex Rodriguez
 */
public interface ImagenDao {
    public Imagen findByImagen(Imagen imagen);
    public List<Imagen> findAll();
    public boolean create(Imagen imagen);
    public boolean update(Imagen imagen);
    public boolean delete(Integer id);
    public Imagen getImagen(Integer id);
}
