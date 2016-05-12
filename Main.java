

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyListener;
import net.contentobjects.jnotify.linux.JNotify_linux;

public class Main {
		
		public static void main(String[] args) throws Exception{
		
		System.out.println("Input directory name (from " + System.getProperty("user.home")+ "/ )");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str = in.readLine();
			
		// путь для поиска
	    String path = System.getProperty("user.home") +"/"+ str + "/";
	    
		System.out.println("Watching Directory: " + path);
		
	    int mask = JNotify.FILE_CREATED  | 
	               JNotify.FILE_DELETED  | 
	               JNotify.FILE_MODIFIED | 
	               JNotify.FILE_RENAMED;

	    //разрешаем поиск в подкаталогах
	    boolean watchSubtree = true;

	    int watchID = JNotify.addWatch(path, mask, watchSubtree, new Listener());
	    
	    Thread.sleep(1000000);

	    boolean res = JNotify.removeWatch(watchID);
	    if (!res) {
	      
	    }
	}
}
