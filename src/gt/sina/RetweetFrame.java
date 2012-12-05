package gt.sina;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import weibo4j.util.BareBonesBrowserLaunch;

/*
 * 转发窗体
 * 查看博文并转发
 * 使用GUI生成器1.6.2（Netbeans）制作
 * 自动生成注释已被删除
 */

/**
 * @author  cofthew7
 */
public class RetweetFrame extends javax.swing.JFrame {

	/**
	 * @uml.property  name="sinaWeibo"
	 * @uml.associationEnd  
	 */
	SinaCore sinaWeibo = null;
	String idOfTweet = null;
	String RetweetCommentText = null;
	/**
	 * @uml.property  name="friendUser"
	 * @uml.associationEnd  
	 */
	SinaUser friendUser = null;
	URL friendAvatarURL = null;
	URL friendHomePage = null;
	String friendName = null;
	String tweetText = null;
	ImageIcon Avatar = null;

	public RetweetFrame(SinaCore sinaWeibo, String idOfTweet,
			SinaTweets sinaTweet) {
		this.sinaWeibo = sinaWeibo;
		this.idOfTweet = idOfTweet;
		friendUser = (SinaUser) sinaTweet.gtGetUser();
		friendAvatarURL = friendUser.gtGetAvatar();
		friendHomePage = friendUser.gtGetHomePage();
		friendName = friendUser.gtGetName();
		tweetText = sinaTweet.gtGetText();
		Avatar = new ImageIcon(friendAvatarURL);
		initComponents();
	}

	private void initComponents() {

		retweetPanel = new JPanel();
		tweetPanel = new JPanel();
		avatarButton = new JButton();
		jScrollPane2 = new JScrollPane();
		retweetTextArea = new JTextArea();
		NameLabel = new JLabel();
		jScrollPane1 = new JScrollPane();
		retweetCommentTextArea = new JTextArea();
		sendButton = new JButton();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Retweet");
		setResizable(false);

		// 显示当前博文
		tweetPanel.setBackground(new Color(158, 238, 250));

		avatarButton.setToolTipText(friendName);
		avatarButton.setIcon(Avatar);
		avatarButton.setSize(new Dimension(48, 48));
		avatarButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				avatarButtonMouseClicked(evt);
			}
		});

		jScrollPane2
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		retweetTextArea.setBackground(new Color(158, 225, 246));
		retweetTextArea.setColumns(20);
		retweetTextArea.setEditable(false);
		retweetTextArea.setLineWrap(true);
		retweetTextArea.setRows(5);
		retweetTextArea.setWrapStyleWord(true);
		retweetTextArea.setText(tweetText);
		jScrollPane2.setViewportView(retweetTextArea);

		NameLabel.setText(friendName);

        GroupLayout tweetPanelLayout = new GroupLayout(tweetPanel);
        tweetPanel.setLayout(tweetPanelLayout);
        tweetPanelLayout.setHorizontalGroup(
            tweetPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(tweetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tweetPanelLayout.createParallelGroup(Alignment.TRAILING, false)
                    .addComponent(NameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(avatarButton, GroupLayout.PREFERRED_SIZE, 60, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );
        tweetPanelLayout.setVerticalGroup(
            tweetPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(tweetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tweetPanelLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addGroup(tweetPanelLayout.createSequentialGroup()
                        .addComponent(avatarButton, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(NameLabel)))
                .addContainerGap())
        );

        // 转发输入框
        retweetCommentTextArea.setColumns(20);
        retweetCommentTextArea.setRows(7);
        retweetCommentTextArea.setLineWrap(true);
        retweetCommentTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(retweetCommentTextArea);

        sendButton.setText("Send");
        sendButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                sendButtonMouseClicked(evt);
            }
        });

        GroupLayout retweetPanelLayout = new GroupLayout(retweetPanel);
        retweetPanel.setLayout(retweetPanelLayout);
        retweetPanelLayout.setHorizontalGroup(
            retweetPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(retweetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(retweetPanelLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(tweetPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(sendButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        retweetPanelLayout.setVerticalGroup(
            retweetPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(retweetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tweetPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sendButton)
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(retweetPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(retweetPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

	private void sendButtonMouseClicked(MouseEvent evt) {
		// 转发
		RetweetCommentText = retweetCommentTextArea.getText();
		if (sinaWeibo.gtReTweet(idOfTweet, RetweetCommentText)) {
			JOptionPane.showMessageDialog(null, "转发成功：）", "", -1);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "转发失败：（", "", -1);
		}
	}
    
    private void avatarButtonMouseClicked(MouseEvent evt) {
    	BareBonesBrowserLaunch.openURL(this.friendHomePage.toString());
    }

    // Variables declaration - do not modify
    private JLabel NameLabel;
    private JButton avatarButton;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextArea retweetCommentTextArea;
    private JPanel retweetPanel;
    private JTextArea retweetTextArea;
    private JButton sendButton;
    private JPanel tweetPanel;
    // End of variables declaration

}