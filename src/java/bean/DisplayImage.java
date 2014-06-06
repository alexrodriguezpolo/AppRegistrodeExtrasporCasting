package bean;

import dao.Database;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import model.Imagen;

/**
 *
 * @author javaknowledge
 */
public class DisplayImage extends HttpServlet {

    private static final long serialVersionUID = 4593558495041379082L;
    private Imagen imagen = new Imagen();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strId=request.getParameter("id");
		if(strId!=null&&!"".equals(strId)){
			try{
				int id=Integer.valueOf(strId);
				BufferedImage buffImg = getImageById(id);
				response.setContentType("image/jpg");
				if(buffImg!=null){
					OutputStream os = response.getOutputStream();
					ImageIO.write(buffImg, "jpg", os);
					os.flush();
					os.close();
				}
			}
			catch(NumberFormatException nfe){
				nfe.printStackTrace();
			}
		}
	}
    
    public BufferedImage getImageById(int id) {
		String sql= "select foto from imagen where idimagen ='"+this.imagen.getIdimagen()+"'";
		BufferedImage buffimg = null;
		try {
                        Connection con = Database.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1,id);
			ResultSet result = stmt.executeQuery();
			if(result.next()){
				InputStream img = result.getBinaryStream(1);
				buffimg= ImageIO.read(img);
			}
		}
		catch(SQLException | IOException e) {
                    System.out.println("Erro al obtener la imagen"+e.getMessage());
		}
		return buffimg;
	}
}