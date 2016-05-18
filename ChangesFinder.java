import java.util.*;
import java.io.*;

public class ChangesFinder
{
	ArrayList<String> casheFileStr;//строки, в которой текст из файла из бэкапа/кеша
	ArrayList<String> nowFileStr;//строки,в которой текст из подопытного файла
	String difference = "";//разница текстов в файлах
	String date = "";//фигня, которую хотел использовать, но не использовал
	String filePathCashe = "";//путь до файла в бэкапе/кеше
	String bakName = "";//имя .bak файла
	String name;//имя файла (.txt)
	
	//конструктор класса,в котором инициализируются полные имена файлов, а также списки из этих файлов
	public ChangesFinder(String filePath,String name) throws IOException{
		
		this.name = name;
		bakName = name.substring(0, name.lastIndexOf('.')) + ".bak";//делаем ися файла с расширением .bak
		initCashe();//инициализируем списки сразу в конструкторе
		initNow(filePath);
	}
	//вспомогательный метод, собирающая из имени обычного файла .txt имя файла с .bak (полное)
	public void createBAKname(){ 
		filePathCashe = getProperty("user.home") + "/.config/libreoffice/4/user/backup/" + bakName;
	}
	//проинициализируем списки из файла .бак (который в кеше)
	public void initCashe() throws IOException{
			createBAKname();//используем метод
			
			//прочитаем из файла все строки
			BufferedReader reader = new BufferedReader(new FileReader(filePathCashe));
			String line;//переменная для считывания строчек из файла
			casheFileStr = new ArrayList<String>();
			
			while ((line = reader.readLine()) != null)//пока счиатываемая строка не пустая
				casheFileStr.add(line);	//запихиваем в список эти строки
	}
	
	//проинициализируем строки из текущего файла (тоже самое что и метод выше)
	public void initNow(String filePath) throws IOException{
		 	BufferedReader reader = new BufferedReader(new FileReader(filePath));
	        String line;
	        nowFileStr = new ArrayList<String>();
	        
	        while ((line = reader.readLine()) != null)
	            nowFileStr.add(line);
	}
	
	//сравним эти строки и соберем в 1 строку ( стринговый возвращаемый тип хотел использовать для старого контракта)
	public String compareStrings(){
		
			ArrayList<String> list = new ArrayList<String>();
			
			list = findNotMatching(casheFileStr,nowFileStr);
			
			StringBuilder sb = new StringBuilder();
			
			for (String s : list){
			    sb.append(s);
			    sb.append("\t");
			}
			difference = sb.toString();
		
			return difference;//пока нигде не используется, но будем отправлять в контракт (поменяю тип, если поменяется контракт - пока строка)
	}
	
	//поисковик разницы между строками
	public ArrayList<String> findNotMatching(ArrayList<String> str1,ArrayList<String> str2){
	    
		ArrayList<String> diff = new ArrayList<>()//список с различиями
	    
	    //str1 - измененный файл
	    //str2 - файл в кеше 
	    ArrayList<String> result = new ArrayList(str1);
	    result.removeAll(str2);
	    //если в списке строк из кеша остались значения, то в изменяемом файле строки были удалены
	    for(String elem : result){
	    	System.out.println(name + ": Deleted>> "+ elem);
	    	diff.add(name +  ": Deleted>> "+	elem);
	    }
		
		//наоборот
	    ArrayList<String> result2 = new ArrayList(str2);
	    result2.removeAll(str1);
	    //если в списке строк изменяемого файла есть строки, которых нет в кешированном, то строки были добавлены в файл
	    for(String elem : result2){
	    	System.out.println(name + ": Added>> "+ elem);//вывод в консаоль, который впоследствии можно убрать
	    	diff.add(name + ": Added>> "+elem);
	    }
	    
	    return diff;
	}
}