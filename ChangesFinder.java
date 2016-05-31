import java.util.*;
import java.io.*;

public class ChangesFinder
{
	ArrayList<String> casheFileStr;//строки, в которой текст из файла из бэкапа/кеша
	ArrayList<String> nowFileStr;//строки,в которой текст из подопытного файла
	String difference = "";//разница текстов в файлах
	String date = "";
	String filePathCashe = "";//путь до файла в бэкапе/кеше
	String bakName = "";//имя .bak файла
	String name;
	String textForMD5BAK="";
	String textForMD5Source="";
	
	public ChangesFinder(String filePath,String name) throws IOException{
		
		this.name = name;
		bakName = name.substring(0, name.lastIndexOf('.')) + ".bak";
		initCashe();
		initNow(filePath);
	}
	
	public void createBAKname(){
		filePathCashe = System.getProperty("user.home") + "/.config/libreoffice/4/user/backup/" + bakName;
	}
	//проинициализируем строки из файла .бак
	public void initCashe() throws IOException
	{
			createBAKname();
			StringBuilder text    = new StringBuilder();
			BufferedReader reader = new BufferedReader(new FileReader(filePathCashe));
			String line;
			casheFileStr = new ArrayList<String>();
			
			while ((line = reader.readLine()) != null)
			{
				casheFileStr.add(line);
				text.append(line);
			}
			textForMD5BAK = text.toString();
	}
	
	//проинициализируем строки из текущего файлв
	public void initNow(String filePath) throws IOException{
			StringBuilder text = new StringBuilder();
		 	BufferedReader reader = new BufferedReader(new FileReader(filePath));
	        String line;
	        nowFileStr = new ArrayList<String>();
	        
	        while ((line = reader.readLine()) != null)
	        {
	        	nowFileStr.add(line);
	        	text.append(line);
	        }
	        textForMD5Source = text.toString();
	}
	
	//сравним эти строки и соберем в 1
	public String compareStrings(){
		
			ArrayList<String> list = new ArrayList<String>();
			
			list = findNotMatching(casheFileStr,nowFileStr);
			
			StringBuilder sb = new StringBuilder();
			
			for (String s : list){
			    sb.append(s);
			    sb.append("\t");
			}
			difference = sb.toString();
		
			return difference;			
	}
	
	//поисковик разницы между строками
	public ArrayList<String> findNotMatching(ArrayList<String> str1,ArrayList<String> str2){
	    
		ArrayList<String> diff = new ArrayList<>();
	    
	    int lengthMax = Math.max(str1.size(), str2.size());
	    int lengthMin = Math.min(str1.size(), str2.size());
	    
	    //str1 - измененый файл
	    //str2 - файл в кеше 
	    ArrayList<String> result = new ArrayList(str1);
	    result.removeAll(str2);
	    
	    for(String elem : result){
	    	System.out.println(name + ": Deleted>> "+ elem);
	    	diff.add(name +  ": Deleted>> "+	elem);
	    }
	 
	    ArrayList<String> result2 = new ArrayList(str2);
	    result2.removeAll(str1);
	    
	    for(String elem : result2){
	    	System.out.println(name + ": Added>> "+ elem);
	    	diff.add(name + ": Added>> "+elem);
	    }
	   // System.out.println("Was: " + MD5Util.md5Custom(textForMD5BAK));
    	//System.out.println("Now: " + MD5Util.md5Custom(textForMD5Source));
	    
	    return diff;
	}

}