package lxj.Datamining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.sound.sampled.Line;

public class PreRead {

	/**
	 * 读取测试文档
	 */
	 public ArrayList<ArrayList<String>> readTest(String fileIn){
		 ArrayList<ArrayList<String>> outList = new ArrayList<ArrayList<String>>();
		 try {
			File file = new File(fileIn);
			 FileReader reader = new FileReader(file);
			 BufferedReader in = new BufferedReader(reader);
			 String line = null;
			 while((line = in.readLine()) != null){
				 ArrayList<String> list = new ArrayList<String> ();
				 String[] mArray = line.split(",");
				 for(int i = 0;i < mArray.length;i++){
					 list.add(mArray[i]);
				 }
				 outList.add(list);
			 }
			 in.close();
			 reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("读取出错");
			e.printStackTrace();
		} 
		return outList; 
	 }
	/**
	 * 读取并预处理数据
	 * @param fileIn
	 * @param fileOut
	 */
	public void readFile(String fileIn,String fileOut,boolean type){
		
		ArrayList<ArrayList<String>> outList = new ArrayList<ArrayList<String>>();
		File file = new File(fileIn);
	

		if(file.isFile()&&file.exists()){
		try {
			InputStreamReader in = new InputStreamReader(new FileInputStream(file),"UTF-8");
			BufferedReader bf = new BufferedReader(in);
			File file2 = new File(fileOut);
			FileWriter writer = new FileWriter(file2);
			BufferedWriter bWriter = new BufferedWriter(writer);
			String line = null;
			while((line = bf.readLine()) != null){
				if(type && line.contains("?")){ //除干扰
					continue;
				}
				String tempString = dataConvert(line);
				bWriter.write(tempString);
				bWriter.newLine();
			}
			bf.close();
			in.close();
			bWriter.flush();
			bWriter.close();
			writer.close();
			//System.out.print("读取成功") ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print("读取出错") ;
			e.printStackTrace();
		}
		}else{
			System.out.print("找不到文件") ;
		}
	}
	
	public String dataConvert(String string ){
		String temp[] = string.split(", ");
		StringBuilder sb = new StringBuilder();
		sb.append(ageConversion(temp[0])+",");
		sb.append(workclassConversion(temp[1])+",");//workclass
    	sb.append("");//fnlwgt忽略
    	sb.append(educationConversion(temp[3])+",");//education
    	sb.append("");//education_num忽略
    	sb.append(maritalStatusConversion(temp[5])+",");//marital_status
    	sb.append(occupationConversion(temp[6])+",");//occupation
    	sb.append(relationshipConversion(temp[7])+",");//relationship
    	sb.append(raceConversion(temp[8])+",");//race
    	sb.append(sexConversion(temp[9])+",");//sex
    	sb.append("");//capital-gain
    	sb.append("");//capital-loss
    	sb.append(hoursPerWeekConversion(temp[12])+",");//hours-per-wee：每十个小时一个区间
    	sb.append(nativeCountryConversion(temp[13])+",");//nativeCountry：只分为两种，美国和外国
    	sb.append(resultConversion(temp[14]));
		return sb.toString();
	}
	
	private int ageConversion(String string) {
		if (!string.contains("?")) {
			int ageTemp = Integer.parseInt(string);
			if (ageTemp<10) {
				return 1;
			} else if (ageTemp<20) {
				return 2;
			} else if (ageTemp<30) {
				return 3;
			} else if (ageTemp<40) {
				return 4;
			} else if (ageTemp<50) {
				return 5;
			} else if (ageTemp<60) {
				return 6;
			} else if (ageTemp<70) {
				return 7;
			} else if (ageTemp<80) {
				return 8;
			} else if (ageTemp<90) {
				return 9;
			} else {
				return 10;
			}
		}
		return 0;
	}
	private int workclassConversion(String string) {
		if (!string.contains("?")) {
			if (string.contains("Private")) {
				return 1;
			} else if (string.contains("Self-emp-not-inc")) {
				return 2;
			} else if (string.contains("Self-emp-inc")) {
				return 3;
			} else if (string.contains("Federal-gov")) {
				return 4;
			} else if (string.contains("Local-gov")) {
				return 5;
			} else if (string.contains("State-gov")) {
				return 6;
			} else if (string.contains("Without-pay")) {
				return 7;
			} else if (string.contains("Never-worked")) {
				return 8;
			}
		}
		return 0;
	}
	
	private int educationConversion(String string) {
		if (!string.contains("?")) {
			if (string.contains("Bachelors")) {
				return 1;
			} else if (string.contains("Some-college")) {
				return 2;
			} else if (string.contains("11th")) {
				return 3;
			} else if (string.contains("HS-grad")) {
				return 4;
			} else if (string.contains("Prof-school")) {
				return 5;
			} else if (string.contains("Assoc-acdm")) {
				return 6;
			} else if (string.contains("Assoc-voc")) {
				return 7;
			} else if (string.contains("9th")) {
				return 8;
			} else if (string.contains("7th-8th")) {
				return 9;
			} else if (string.contains("12th")) {
				return 10;
			} else if (string.contains("Masters")) {
				return 11;
			} else if (string.contains("1st-4th")) {
				return 12;
			} else if (string.contains("10th")) {
				return 13;
			} else if (string.contains("Doctorate")) {
				return 14;
			} else if (string.contains("5th-6th")) {
				return 15;
			} else if (string.contains("Preschool")) {
				return 16;
			}
		}
		return 0;
	}
	
	private int maritalStatusConversion(String string) {
		if (!string.contains("?")) {
			if (string.contains("Married-civ-spouse")) {
				return 1;
			} else if (string.contains("Divorced")) {
				return 2;
			} else if (string.contains("Never-married")) {
				return 3;
			} else if (string.contains("Separated")) {
				return 4;
			} else if (string.contains("Widowed")) {
				return 5;
			} else if (string.contains("Married-spouse-absent")) {
				return 6;
			} else if (string.contains("Married-AF-spouse")) {
				return 7;
			}
		}
		return 0;	
	}
	//occupation: Tech-support, Craft-repair, Other-service, Sales, Exec-managerial,
	//Prof-specialty, Handlers-cleaners, Machine-op-inspct, Adm-clerical, Farming-fishing,
	//Transport-moving, Priv-house-serv, Protective-serv, Armed-Forces.

	private int occupationConversion(String string) {
		if (!string.contains("?")) {
			if (string.contains("Tech-support")) {
				return 1;
			} else if (string.contains("Craft-repair")) {
				return 2;
			} else if (string.contains("Other-service")) {
				return 3;
			} else if (string.contains("Sales")) {
				return 4;
			} else if (string.contains("Exec-managerial")) {
				return 5;
			} else if (string.contains("Prof-specialty")) {
				return 6;
			} else if (string.contains("Handlers-cleaners")) {
				return 7;
			} else if (string.contains("Machine-op-inspct")) {
				return 8;
			} else if (string.contains("Adm-clerical")) {
				return 9;
			} else if (string.contains("Farming-fishing")) {
				return 10;
			} else if (string.contains("Transport-moving")) {
				return 11;
			} else if (string.contains("Priv-house-serv")) {
				return 12;
			} else if (string.contains("Protective-serv")) {
				return 13;
			} else if (string.contains("Armed-Forces")) {
				return 14;
			}
		}
		return 0;
	}
	//relationship: Wife, Own-child, Husband, Not-in-family, Other-relative, Unmarried.
	private int relationshipConversion(String string) {
		if (!string.contains("?")) {
			if (string.contains("Wife")) {
				return 1;
			} else if (string.contains("Own-child")) {
				return 2;
			} else if (string.contains("Husband")) {
				return 3;
			} else if (string.contains("Not-in-family")) {
				return 4;
			} else if (string.contains("Other-relative")) {
				return 5;
			} else if (string.contains("Unmarried")) {
				return 6;
			}
		}
		return 0;	
	}
//	race: White, Asian-Pac-Islander, Amer-Indian-Eskimo, Other, Black.
	private int raceConversion(String string) {
		if (!string.contains("?")) {
			if (string.contains("White")) {
				return 1;
			} else if (string.contains("Asian-Pac-Islander")) {
				return 2;
			} else if (string.contains("Amer-Indian-Eskimo")) {
				return 3;
			} else if (string.contains("Other")) {
				return 4;
			} else if (string.contains("Black")) {
				return 5;
			}
		}
		return 0;	
	}
	
	private int sexConversion(String string) {
		if (!string.contains("?")) {
			if (string.contains("Female")) {
				return 1;
			} else if (string.contains("Male")) {
				return 2;
			}
		}
		return 0;	
	}
	private int hoursPerWeekConversion(String string) {
		if (!string.contains("?")) {
			int workHourTemp = Integer.parseInt(string);
			if (workHourTemp<10) {
				return 1;
			} else if (workHourTemp<20) {
				return 2;
			} else if (workHourTemp<30) {
				return 3;
			} else if (workHourTemp<40) {
				return 4;
			} else if (workHourTemp<50) {
				return 5;
			} else if (workHourTemp<60) {
				return 6;
			} else if (workHourTemp<70) {
				return 7;
			} else if (workHourTemp<80) {
				return 8;
			} else {
				return 9;
			}
		}
		return 0;
	}
	private int nativeCountryConversion(String string) {
		if (!string.contains("?")) {
			if (string.contains("United-States")) {
				return 1;
			} else {
				return 0;
			}
		}
		return 0;	
	}
	private String resultConversion(String string) {
		if (string.contains(">50K")) {
			return "yes";
		} else {
			return "no";
		}
	}
}
