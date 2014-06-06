package dao;

import java.util.List;
import model.Usuario;

/**
 *
 * @author Alex Rodriguez
 */
public interface UsuarioDao {
    
    public Usuario findByUsuario(Usuario usuario);
    public Usuario login(Usuario usuario);
    public List<Usuario> findAll();
    public boolean create(Usuario usuario);
    public boolean update(Usuario usuario);
    public boolean delete(Integer id);
}
