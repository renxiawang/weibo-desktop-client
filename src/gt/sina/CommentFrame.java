package gt.sina;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import weibo4j.Comment;
import weibo4j.WeiboException;

/*
 * 评论窗体
 * 查看评论及发表评论
 * GUI生成器1.6.2（Netbeans）制作
 * 自动生成注释已被删除
 */


public class CommentFrame extends javax.swing.JFrame {
	
	
	SinaCore sinaWeibo = null;
	String idOfTweet = null;
	String commentText = null;
	boolean isCommentToMe = false;
		
    public CommentFrame(SinaCore sinaWeibo,String idOfTweet) {
    	this.sinaWeibo = sinaWeibo;
    	this.idOfTweet = idOfTweet;
        initComponents();
    }

    private void initComponents() {

        commentsPanel = new JPanel();
        CommentsScrollPane = new JScrollPane();
        commentPanel = new JPanel();
        //tweetPanel = new JPanel();
        //avatarButton2 = new JButton();
        //tweetTextScrollPane = new JScrollPane();
        //tweetTextArea = new JTextArea();
        jScrollPane1 = new JScrollPane();
        commentsTextArea = new JTextArea();
        sendButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Comment");
        setResizable(false);
        
        // 对当前博文的评论列表
        CommentsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		commentPanel.setBackground(new Color(0, 214, 255));
		commentPanel.setPreferredSize(new Dimension(420, 2500));
		JLabel noCommentLabel = new JLabel("NO Comment!");

		Iterator<Comment> commentIterator = sinaWeibo.gtGetComments(idOfTweet)
				.iterator();
		int numOfComment = 0;
		while (commentIterator.hasNext()) {
			Comment singleComment = commentIterator.next();
			SingleCommentPanel singlecommentPanel = new SingleCommentPanel(
					sinaWeibo, singleComment, isCommentToMe);
			singlecommentPanel.setBackground(new Color(158, 238, 250));
			singlecommentPanel.setBounds(10, numOfComment * 125 + 10, 340, 120);
			numOfComment++;
			commentPanel.add(singlecommentPanel);
		}

        GroupLayout commentPanelLayout = new GroupLayout(commentPanel);
        commentPanel.setLayout(commentPanelLayout);
        commentPanelLayout.setHorizontalGroup(
            commentPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(commentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noCommentLabel)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        commentPanelLayout.setVerticalGroup(
            commentPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(commentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noCommentLabel)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        CommentsScrollPane.setViewportView(commentPanel);
        
        // 评论输入框
        commentsTextArea.setColumns(20);
        commentsTextArea.setRows(7);
        commentsTextArea.setLineWrap(true);
        commentsTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(commentsTextArea);

        sendButton.setText("Send");
        sendButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                sendButtonMouseClicked(evt);
            }
        });

        GroupLayout commentsPanelLayout = new GroupLayout(commentsPanel);
        commentsPanel.setLayout(commentsPanelLayout);
        commentsPanelLayout.setHorizontalGroup(
            commentsPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(commentsPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(commentsPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(commentsPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                        .addComponent(CommentsScrollPane, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                        .addComponent(sendButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );
        commentsPanelLayout.setVerticalGroup(
            commentsPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(commentsPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(commentsPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(CommentsScrollPane, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(sendButton)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(commentsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(commentsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }

	private void sendButtonMouseClicked(MouseEvent evt) {
		// 发送评论
		commentText = commentsTextArea.getText();
		if (commentText.length() <= 140) {
			// 若博文长度符合要求，发送博文
			if (sinaWeibo.gtSendComment(commentText, idOfTweet)) {
				JOptionPane.showMessageDialog(null, "发送成功", "：）", -1);
				this.dispose();
				commentsTextArea.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "发送失败：（", "error", -1);
			}
		} else {
			// 若博文长度不符合要求，弹窗提示
			JOptionPane.showMessageDialog(null, "字数过多，请修改后重新发送（不超过140字）",
					"error", -1);
		}
	}


    // Variables declaration
    private JScrollPane CommentsScrollPane;
    //private JButton avatarButton2;
    private JPanel commentPanel;
    private JPanel commentsPanel;
    private JTextArea commentsTextArea;
    private JScrollPane jScrollPane1;
    private JButton sendButton;
    //private JPanel tweetPanel;
    //private JTextArea tweetTextArea;
    //private JScrollPane tweetTextScrollPane;
    // End of variables declaration//GEN-END:variables

}