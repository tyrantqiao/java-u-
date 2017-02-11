package work;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CopyFiles {

	File fileDrive; 
	
	
	public CopyFiles(File fileDrive, String forms) {
		this.fileDrive=fileDrive;
		Pattern formPattern=Pattern.compile("(\\.[a-z]{3,4})");
		Matcher formMatcher=formPattern.matcher(forms);
		System.out.println("matching");
		while(formMatcher.find()){
//			System.out.println("i found");
			String form=formMatcher.group(1);
			if(form==null||!fileDrive.exists()) break;
			try{
				System.out.println("try to sent the form:"+form);
				fileChoose(form,fileDrive);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public void fileChoose(String form,File parentFile) throws IOException,NullPointerException{
/*		
 * 		System.out.println("i'm in the u pan");
		一般的u盘会有 System Volume Information的隐藏文件，用来存储系统分区的信息的内容，亲测删了也没啥效果，不过会自动生成。。。而且由于不允许进入，会导致错误
		for(File file:parentFile.listFiles()){
			System.out.println(file.getName());
		}
*/		
		File[]       	sonFiles = parentFile.listFiles();
		ArrayList<File> sonLists = new ArrayList<File>();
		
//		if(sonFiles.length==0)return;
		//System Volume Information
		
		for(File file:sonFiles){
			if(file.isDirectory()&&(!file.getName().endsWith("System Volume Information"))){
				System.out.println(file.getName());
				fileChoose(form,file);
			}
			else if(file.isFile()){
				sonLists.add(file);
			}
		}
		
		for(File file:sonLists){
			if(file.isFile()&&isForms(file,form)){
				System.out.println("i found "+file+",copying");
				ioPaste(file);
			} 
		}
		
/*   介于系统文件原因，转为Arraylist操作
		for(File file:parentFile.listFiles()){
			if(file.isDirectory()){
				System.out.println(file.getName());
				fileChoose(form,file);
			}
			else if(file.isFile()&&isForms(file,form)){
				System.out.println("i found "+file+",copying");
				ioPaste(file);
			} 
 */
	
	}
	
	public boolean isForms(File file,String form){
		if(file.getName().endsWith(form)){
			return true;
		}
		return false;
	}
	
	public void ioPaste(File file) throws IOException{
		if(new File("C:\\Users\\huangxiuyang696955\\Desktop\\"+file.getName()).exists()){
			System.out.println("i had copied the file u want");
			return ;
		}
		
		BufferedInputStream inStream=new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream outStream=new BufferedOutputStream(
										new FileOutputStream(
										 new File("C:\\Users\\huangxiuyang696955\\Desktop\\"+file.getName())));
		
		int i;
		while((i=inStream.read())!=-1){
			outStream.write(i);
		}
		
		inStream.close();
		outStream.close();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
