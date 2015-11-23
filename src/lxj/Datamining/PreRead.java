package lxj.Datamining;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PreRead {
	public void readFile(String fileIn,String fileOut){
		ArrayList<ArrayList<String>> outList = new ArrayList<ArrayList<String>>();
		File file = new File(fileIn);
		try {
			InputStreamReader in = new InputStreamReader(new FileInputStream(file),"UTF-8");
			BufferedReader bf = new BufferedReader(in);
			FileWriter writer = new FileWriter(fileOut);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
