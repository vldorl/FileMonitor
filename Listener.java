import java.io.IOException;

import net.contentobjects.jnotify.JNotifyListener;

public class Listener implements JNotifyListener {
	ChangesFinder finder;
	
	public void fileRenamed(int wd, String rootPath, String oldName, String newName) {
	      print("renamed " + rootPath + " : " + oldName + " -> " + newName);
	    }
	    public void fileModified(int wd, String rootPath, String name) {
	      
	      if (name.contains(".txt") && !name.contains("~lock.")){
			//print("\nmodified " + rootPath + " : " + name);
			try{
				finder = new ChangesFinder(rootPath+"/"+name,name);
			}
			catch (IOException e){
				// TODO Auto-generated catch block
				//	e.printStackTrace();
			}
			finder.compareStrings();
		
	      }
	    }
	    
	    public void fileDeleted(int wd, String rootPath, String name) {
	    	if (!name.contains("~lock."))
	    			print("deleted " + rootPath + " : " + name);
	    }
	    public void fileCreated(int wd, String rootPath, String name) {
	    	if (!name.contains("~lock.")){
				print("created " + rootPath + " : " + name);
	    	}  
	    }
	    void print(String msg) {
	      System.err.println(msg);
	    }
}