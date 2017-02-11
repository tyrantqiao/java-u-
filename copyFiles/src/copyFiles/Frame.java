package copyFiles;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import thread.CopyThread;

public class Frame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CopyThread thread;
	private static Frame frame;
	boolean visible=true;
	
	public Frame(){
		JPanel 		inputPanel = new JPanel();
		JPanel 		setPanel   = new JPanel();
		
		JLabel 		circulLabel= new JLabel("circulTime:");
		JLabel 		filesLabel = new JLabel("files'Forms");
		JButton     runButton  = new JButton("run");
		JButton     stopButton = new JButton("stop");
		JButton     resumeButton=new JButton("resume");
		JButton     hideButton = new JButton("hide");
		JButton     saveButton = new JButton("save");
		JTextField 	timeField  = new JTextField("1000");
		JComboBox<String>formsBox= new JComboBox<String>();

		formsBox.addItem(".ppt");
		formsBox.addItem(".pdf");
		formsBox.addItem(".docx");
		formsBox.addItem(".doc");
		formsBox.addItem(".rar");
		formsBox.addItem(".jpg.jpeg.png");
		formsBox.addItem(".docx.doc.ppt.rar.pdf.jpg.png");
		
		thread=new CopyThread(timeField.getText());
		
		runButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(formsBox.getSelectedItem().toString());
				thread.start(formsBox.getSelectedItem().toString());
			}
		});
		
		stopButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				thread.suspend();;
			}
		});
		
		resumeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				thread.resume();
			}
		});
		
		
		hideButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				visible=!visible;
				frame.setVisible(visible);
				System.out.println(visible);
			}
		});
		
		inputPanel.setLayout(new GridLayout(2, 2));
		inputPanel.add(circulLabel);
		inputPanel.add(timeField);
		
		inputPanel.add(filesLabel);
		inputPanel.add(formsBox);
		
		setPanel.add(runButton);
		setPanel.add(stopButton);
		setPanel.add(hideButton);
		setPanel.add(saveButton);
		
		add(inputPanel,BorderLayout.NORTH);
		add(setPanel,BorderLayout.SOUTH);

		pack();
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				frame=new Frame();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}

}
