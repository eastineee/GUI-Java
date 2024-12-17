package java_gui_group7;

import java.util.HashMap;

public class login_info {

	HashMap<String,String> idandpass = new HashMap<String,String>();
	
	login_info(){
		idandpass.put("pascua", "1111");
		idandpass.put("macaubos", "2222");
		idandpass.put("rulona", "3333");
		idandpass.put("pacana","4444");
		idandpass.put("sarino", "5555");
	}
	
	protected HashMap getIdandPass() {
		return idandpass;
	}
}