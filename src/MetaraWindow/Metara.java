//main applicatrion method
package MetaraWindow;
/* Watch your name*/ 
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import Downloader.ReadFile;
import javax.swing.*;
import MetaraDecoder.Decoder;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Metara {
	
	 static final int MIN = 0;
         static final int MAX = 100;

	private JFrame frame;
	private JTextField textField;
	
	
	public static String ICAO;
	public static String print;

	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Metara app = new Metara();
					app.frame.setVisible(true);
                                        //FIND THE LINK TO THE METAR SERVER
                                        //THAT ACTUALLY WORKS
					String data = "https://aviationweather.gov/adds/dataserver_current/current/metars.cache.csv";
					File output = new File("src/metar.txt");
					
                                        
                                        
                                        
					Decoder decoder = new Decoder();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
                        
                );
	}


	
	public Metara() {
		initialize();
	}

	
	
	private void initialize() {
		
		try {
		    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		 } catch (Exception e) {
		            e.printStackTrace();
		 }
		 
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);

		try {
	          frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/background.jpg")))));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        frame.pack();
	        frame.setVisible(true);
		
		
		File in = new File("src/data.txt");
		ReadFile read = new ReadFile(in);
		Decoder decoder = new Decoder();
		
		
		JTextArea textArea = new JTextArea();
		textField = new JTextField();
		JButton showButton = new JButton("METAR");
		JRadioButton rdbtnDecode = new JRadioButton("Format");
		
		
		
		frame.getContentPane().add(textArea);
		frame.getContentPane().add(showButton);
		frame.getContentPane().add(textField);
		


		
		textArea.setForeground(Color.WHITE);
		textArea.setBounds(105, 150, 612, 292);
		textArea.setFont(new Font("Fixedsys", Font.BOLD, 13));
		textArea.setBackground(new Color(128, 128, 128));
		textArea.setOpaque(false);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		
		
		
		textField.setForeground(new Color(255, 255, 255));
		textField.setFont(new Font("Fixedsys", Font.BOLD, 50));
                //text box position
		textField.setBounds(270, 40, 170, 90);
		textField.setColumns(90);
		textField.setOpaque(false);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				
				ICAO = textField.getText().toUpperCase();

				rdbtnDecode.setSelected(true);
				String allLine = decoder.saveAllLine(read, ICAO);
				String raw = decoder.showRawMetar(allLine);
				print = raw;
				textArea.setText(print);
			}
		});
		
		
		showButton.setForeground(new Color(255, 254, 255));
		showButton.setFont(new Font("Fixedsys", Font.BOLD, 30));
                //button size for metar button
		showButton.setBounds(50, 40, 200, 90);
		showButton.setBackground(new Color(255,255,255));
		showButton.setOpaque(false);
		frame.getContentPane().setLayout(null);
		showButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				ICAO = textField.getText().toUpperCase();
				rdbtnDecode.setSelected(false);
				String allLine = decoder.saveAllLine(read, ICAO);
				String raw = decoder.showRawMetar(allLine);
				print = raw;
				textArea.setText(print);
			}
		});
		
		
		
		
		rdbtnDecode.setFont(new Font("Fixedsys", Font.BOLD, 30));
		rdbtnDecode.setForeground(Color.WHITE);
		rdbtnDecode.setBounds(330, 500, 150, 30);
		rdbtnDecode.setBackground(new Color(255,255,255));
		rdbtnDecode.setOpaque(false);
		frame.getContentPane().add(rdbtnDecode);
		rdbtnDecode.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				
				ICAO = textField.getText().toUpperCase();
				String allLine = decoder.saveAllLine(read, ICAO);
				String raw = decoder.showRawMetar(allLine);
				String decoded = decoder.showDecoded(allLine);
				print = raw;
				
				if(rdbtnDecode.isSelected()) {
					textArea.setText(decoded);
				}
				if(rdbtnDecode.isSelected() == false) {
					textArea.setText(raw);
				}
				
			}
		});
		
		
		
	}
}
