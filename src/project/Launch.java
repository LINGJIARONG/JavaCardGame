package project;


import java.awt.EventQueue;
import java.io.IOException;

import project.view.MainView;



public class Launch {
		private MainView v;
		public Launch() throws IOException {
			v=new MainView();
			v.setResizable(false);
			v.setVisible(true);
		}
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						new Launch();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}
	
}
