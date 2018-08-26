package ml.kelvinco.java.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@SuppressWarnings("deprecation")
@ManagedBean  
@RequestScoped  
public class KLVNUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String name;  
	  
	public String getName() {  
	return name;  
	}  
	public void setName(String name) {  
	this.name = name;  
	}  
}
