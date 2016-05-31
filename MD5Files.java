import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MD5Files {
	String textForhashBAK = "";
	String textForhashSource = "";
	String filePathCashe;
	String filePath;
	
	public MD5Files(String filePath, String name)
	{
		String bakName = name.substring(0, name.lastIndexOf('.')) + ".bak";
		this.filePath = filePath;
		filePathCashe = System.getProperty("user.home") + "/.config/libreoffice/4/user/backup/" + bakName;
	}
	
	public void initStr() throws IOException
	{
		StringBuilder text    = new StringBuilder();
		StringBuilder text2    = new StringBuilder();
		BufferedReader reader = new BufferedReader(new FileReader(filePathCashe));
		BufferedReader reader2 = new BufferedReader(new FileReader(filePath));
		String line;
		String line2;
		
		while ((line2 = reader2.readLine()) != null)
			text2.append(line2);
		
		while ((line = reader.readLine()) != null)
			text.append(line);
		
		System.out.println("Now: " + MD5Util.md5Custom(text2.toString()));
		System.out.println("Was: " + MD5Util.md5Custom(text.toString()));
		
		
	}
}