package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import model.Imagen;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Alex Rodriguez
 */
public class ImagenDaoImpl implements ImagenDao {
    private File file;
    @Override
    public Imagen findByImagen(Imagen imagen) {
        Imagen model = null;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Imagen WHERE idimagen = '" + imagen.getIdimagen() + "'";
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                model = (Imagen) sesion.createQuery(sql).uniqueResult();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        } finally {

        }
        return model;
    }

    @Override
    public List<Imagen> findAll() {
        List<Imagen> listado = null;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Imagen";
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                listado = (List<Imagen>) sesion.createQuery(sql).list();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        } finally {

        }
        return listado;
    }

    @Override
    public boolean create(Imagen imagen) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.beginTransaction();
        try {
            sesion.save(imagen);
            transaction.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            transaction.rollback();
        }
        return flag;
    }
    
    @Override
    public boolean update(Imagen imagen) {
        boolean flag = false;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                sesion.update(imagen);
                transaction.commit();
                flag = true;
            } catch (Exception e) {
                flag = false;
                transaction.rollback();
                throw e;
            }
        } finally {
            
        }
        return flag;
    }

    @Override
    public boolean delete(Integer id) {
        boolean flag = false;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                Imagen imagen = (Imagen) sesion.load(Imagen.class, id);
                sesion.delete(imagen);
                transaction.commit();
                flag = true;
            } catch (Exception e) {
                flag = false;
                transaction.rollback();
                throw e;
            }
        } finally {
            
        }
        return flag;
    }

    @Override
    public Imagen getImagen(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction(); 
        Imagen imagen = (Imagen)session.get(Imagen.class, id);
        byte[] bimagen = imagen.getFoto();
        try {
            //FileOutputStream fos = new FileOutputStream("images\\output.jpg");  //windows
            FileOutputStream fos = new FileOutputStream("resources/images/"+file.getName());
            fos.write(bimagen);
            fos.close();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
