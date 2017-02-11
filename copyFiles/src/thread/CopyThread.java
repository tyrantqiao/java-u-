package thread;

import java.io.File;

import javax.swing.JFrame;

import work.CopyFiles;

public class CopyThread implements Runnable{
	File[] originalRoots=File.listRoots();
	private String time=" ";
	private String forms=" ";
	private boolean suspened=false;
	
	public CopyThread(String time){
		this.time=time;
	}
	
	public void run(){
		File[] currentRoots=null;
		while(true){
			currentRoots=File.listRoots();
			if(currentRoots.length>originalRoots.length){
//				System.out.println("i'm in");
				System.out.println(currentRoots[currentRoots.length-2]);
				new CopyFiles(currentRoots[currentRoots.length-2],forms);
			}

			try{
				Thread.sleep(Long.parseLong(time));
				synchronized (this) {
					while(suspened){
						wait();
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
    
	public void start(String forms){
		Thread t;
		this.forms=forms;
		t=new Thread(this);
		t.start();
	}
	
	public void suspend(){
        suspened = true;
    }
     
     /**
      * ¼ÌÐø
      */
     public synchronized void resume(){
         suspened = false;
         notify();
     }
	
	public static void main(String[] args) {

	}

}
