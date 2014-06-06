package dao;

import java.util.List;
import model.Extra;

/**
 *
 * @author Alex Rodriguez
 */
public interface ExtraDao {
    
    public Extra findByExtra(Extra extra);
    public List<Extra> selectItems();
    public List<Extra> findAll();
    public boolean create(Extra extra);
    public boolean update(Extra extra);
    public boolean delete(Integer id);
    
}
