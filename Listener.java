import java.io.IOException;

import net.contentobjects.jnotify.JNotifyListener;

public class Listener implements JNotifyListener {
	ChangesFinder finder;
	
	//срабатываемт, если файл переименован
	public void fileRenamed(int wd, String rootPath, String oldName, String newName) {
	    print("renamed " + rootPath + " : " + oldName + " -> " + newName);
		//здесь по идее должна быть отправка в контракт
    }
	//если файл изменен
	public void fileModified(int wd, String rootPath, String name) {
		if (name.contains(".txt") && !name.contains("~lock.")){
			print("\nmodified " + rootPath + " : " + name);
			
			//обработчик исключений (не обр внимания)
			try{
				finder = new ChangesFinder(rootPath+"/"+name,name);
			}
			catch (IOException e){
				// TODO Auto-generated catch block
				//	e.printStackTrace();
			}
			finder.compareStrings();//вызов метода класса ChangesFinder
			//здесь по идее должна быть отправка в контракт
	    }
	}
	    //если файл удален
	    public void fileDeleted(int wd, String rootPath, String name) {
	    	if (!name.contains("~lock.")){
	    			print("deleted " + rootPath + " : " + name);
				//здесь по идее должна быть отправка в контракт
			}
		}
		
		//если создан
	    public void fileCreated(int wd, String rootPath, String name) {
	    	if (!name.contains("~lock.")){
				print("created " + rootPath + " : " + name);
				//здесь по идее должна быть отправка в контракт
	    	}  
	    }
		//если ошибка
	    void print(String msg) {
	      System.err.println(msg);
	    }
}