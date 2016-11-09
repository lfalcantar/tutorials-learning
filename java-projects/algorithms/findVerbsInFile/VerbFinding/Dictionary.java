import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Dictionary {

	public static void main(String [] args){
		HashMap<String,String> map = readFile("3.txt");
		//HashMap<Integer,String> map2 = readFile("MPO SRS.txt");
		List<String> result = compare(map);
		System.out.println("Finish");
		
		for(String s:result){
			System.out.println(s);
		}
			
	}
	
	public static List<String> compare(HashMap<String,String> file1){
		List<String> list = new ArrayList<String>();
		BufferedReader br = null;
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("MPO SRS.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				
			String[] array = sCurrentLine.split(" ");
			for(int i =0; i< array.length;i++){
				
				if(!list.contains(array[i]) && file1.containsKey(HashString(array[i]))){
					list.add(array[i]+"->"+ sCurrentLine);
				}
			}
			
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return list;
		
	}
	
	public static String HashString(String str){
		return str.toLowerCase(); 
	}

	public static HashMap<String,String> readFile(String file) {
		HashMap<String,String> map = new HashMap<String,String>();
		BufferedReader br = null;
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(file));

			while ((sCurrentLine = br.readLine()) != null) {
				map.put(HashString(sCurrentLine), sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
