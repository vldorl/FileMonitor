import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyListener;
import net.contentobjects.jnotify.linux.JNotify_linux;

public class Main {
		
		//главный метод программы, точка входа
		public static void main(String[] args) throws Exception{
		
		System.out.println("Input directory name (from " + System.getProperty("user.home")+ "/ )");//для ввода просматриваемой директории
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str = in.readLine();
			
		// путь для поиска
	    String path = System.getProperty("user.home") +"/"+ str + "/";
	    
		System.out.println("Watching Directory: " + path);//вывод в консоль пути, который мониторим
		
		// маска,какие события мониторим
	    int mask = JNotify.FILE_CREATED  | 
	               JNotify.FILE_DELETED  | 
	               JNotify.FILE_MODIFIED | 
	               JNotify.FILE_RENAMED;

	    //разрешаем поиск в подкаталогах
	    boolean watchSubtree = true;
		
		//запускаем мониторинг
	    int watchID = JNotify.addWatch(path, mask, watchSubtree, new Listener());
	    
		//хз зачем, я сопипастил
	    Thread.sleep(1000000);
		
		//неведомая фигня
	    boolean res = JNotify.removeWatch(watchID);
	    if (!res) {
	      
	    }
	}
}
