package com.mypro.basecomponet;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import com.mypro.mainsurface.MainSurface;
import com.mypro.manager.CannonManager;
import com.mypro.manager.GameInitManager;
import com.mypro.manager.LayoutManager;
import com.mypro.model.GamingInfo;

public class AwtMainComponet{
	public static void main(String[] args) throws Exception  {
		// Toolkit�� ����getDefaultToolkit() ��ȡĬ�Ϲ��߰���Toolkit�� ���Է��ʲ���ϵͳ�����Խ���������󶨵��ض��������߰�ʵ�֡�
		Toolkit tool = Toolkit.getDefaultToolkit();
		// Dimension�� ��װ���������� ����Ŀ�Ⱥ͸߶ȣ���ȷ���������������������ĳ��������ء�
		Dimension d = tool.getScreenSize();
		// JFrame��  ��һ���������ṩһ�����ݴ��� 
		JFrame frame = new JFrame();
		// �ȵ���GamingInfo�࣬ Ȼ��������е� getGamingInfo() �ж���Ҫ����ģʽ������Ȼ��������е� setGaming() �жϴ�����Ϸ״̬
        GamingInfo.getGamingInfo().setGaming(true);
        // GamingInfo�� ����getGamingInfo() �ж���Ҫ����ģʽ������Ȼ�����setScreenWidth() ������Ϸ������
        GamingInfo.getGamingInfo().setScreenWidth(900);
        //...... ����setScreenHeight()  ������Ϸ����߶�
    	GamingInfo.getGamingInfo().setScreenHeight(600);  
    	//���� ���� �ĳߴ��С����ȣ�����Ϸ��������ȣ��߶ȣ�����Ϸ����߶����
    	frame.setSize(GamingInfo.getGamingInfo().getScreenWidth(), GamingInfo.getGamingInfo().getScreenHeight());
    	//���ô��ڲ�Ҫװ��
    	frame.setUndecorated(true);
    	//frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE); //����ָ���Ĵ���װ�η��
    	//���ô��ڲ��ܱ��û�������С
    	frame.setResizable(false);
    	
    	//���ô���Ĭ�Ϲرշ�ʽ
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ô��������ָ�������λ�ã�ָ�����Ϊ����  null, ���ھ�����Ļ����
		frame.setLocationRelativeTo(null);
		//����һ���޸�ͼ��Ԫ�ص��� �Ķ���
		MainSurface pane = new MainSurface();
		//�ж�����ģʽ������Ȼ��������Ϸ��Ҫͼ��
		GamingInfo.getGamingInfo().setSurface(pane);
		//�������ݴ������ʾ����
		frame.setContentPane(pane);
		//frame.setAlwaysOnTop(true);		//���ô˴����Ƿ�ʼ��λ�����������Ϸ�
		frame.setVisible(true);
		frame.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {				
			}
			public void mousePressed(MouseEvent e) {
				//��Ϸ��ʼ��������
				if(GameInitManager.getGameInitManager().isIniting()){
					return ;
				}
				//�����
				//�ȿ����ֹ������Ƿ�����Ӧ
				if(!LayoutManager.getLayoutManager().onClick(e.getX(), e.getY())){
					//�����ӵ�
					CannonManager.getCannonManager().shot(e.getX(),  e.getY());
				}
			}
			
			public void mouseExited(MouseEvent e) {				
			}
			
			public void mouseEntered(MouseEvent e) {				
			}
			
			public void mouseClicked(MouseEvent e) {				
			}
		});
//		frame.pack();
		pane.action();
		/**
    	 * ����һ���߳����첽��ʼ����Ϸ����
    	 */
    	new Thread(new Runnable(){
			public void run() {
				//ʹ����Ϸ��ʼ����������ʼ����Ϸ
				GameInitManager.getGameInitManager().init();
			}
    	}).start();  	
	}

}
