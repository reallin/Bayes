package lxj.Datamining;

import java.util.ArrayList;
import java.util.Scanner;

import lxj.tool.DecimalCalculate;

public class Main {
	public static void main(String[] args){
		ArrayList<ArrayList<String>> trainList ;
		ArrayList<ArrayList<String>> testList ;
		
		String originalTrain = "file/adult.data";
		String processedTrain = "process/adultResult.txt";
		String originalTest = "file/adult1000.test";
		String originalTest100 = "file/adult100.test";
		String processedTest = "process/adult1000Result.txt";
		String processedTest100 = "process/adult100Result.txt";
		
		 String finalStr = "";
		 int wrong_number = 0; //记录正确的数量
		 double finalData = 0.0; //最后比率
		 
		 boolean type = false; //是否除干扰
		 boolean flag = false; //标志测试的数量
		 for(int i = 0;i < 2;i++){
			 if(i == 0){
				 System.out.println("请选择测试数量，按y为100个，按n为1000个");
				 Scanner scanner = new Scanner(System.in);
				 String str = scanner.next();
				 if(str.equals("y")){
					 flag = true; 
				 }else if(str.equals("n")){
					 flag = false; 
				 }else{
					 i = -1;
					 System.out.println("输入有误，请重新输入");
				 }
			 }else{
				 System.out.println("请选择是否去噪，y为是，n为否");
				 
				 Scanner scanner = new Scanner(System.in); 
				 String str = scanner.next();
				 if(str.equals("y")){
					 type = true; 
				 }else if(str.equals("n")){
					 type = false; 
				 }else{
					 i = 0;
					 System.out.println("输入有误，请重新输入");
				 }
			 }
		 }
		 System.out.println("请耐心等待…………");
		 if(type){
				System.out.println("除噪声的情况：");
			}else{
				System.out.println("没除噪声的情况：");
			}
		PreRead convert = new PreRead();
		convert.readFile(originalTrain, processedTrain,type); //训练样本
		if(!flag){
			convert.readFile(originalTest, processedTest,type); //1000测试样本
			testList = convert.readTest(processedTest);
		}else{
			convert.readFile(originalTest100, processedTest100,type); //100个测试样本
			testList = convert.readTest(processedTest100);
		}
		
		trainList =convert.readTest(processedTrain);	
		Bayes bayes = new Bayes();
		for(int i = 0;i < testList.size();i++){
			ArrayList<String> tmp = new ArrayList<String>();
			tmp = testList.get(i);
			String label = tmp.get(tmp.size()-1);
			tmp.remove(tmp.size() - 1);
			finalStr = bayes.predictClass(trainList, tmp);
			
			if(!label.equals(finalStr)){
				wrong_number ++;
			}
		}
		//finalData = DecimalCalculate.div(count, testList.size(),3); 
		System.out.println("预试错误个数："+ wrong_number+", 测试总个数"+testList.size());
		System.out.println("正确率为：" + (DecimalCalculate.sub(1.00000000, DecimalCalculate.div(wrong_number, testList.size())))*100 + "%");
	}
}
