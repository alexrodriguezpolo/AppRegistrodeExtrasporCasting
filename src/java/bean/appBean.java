package bean;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import util.MyUtil;

/**
 *
 * @author Alex Rodriguez
 */
@Named(value = "appBean")
@ApplicationScoped
public class appBean {

    /**
     * Creates a new instance of appBean
     */
    public appBean() {
    }
    
    public String getBaseUrl(){
        return MyUtil.baseurl();
    }
}
