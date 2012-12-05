package gt.sina;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import weibo4j.Comment;
import weibo4j.util.BareBonesBrowserLaunch;

//VS4E -- DO NOT REMOVE THIS LINE!

public class SingleCommentPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton avatarButton;
	private JTextArea commentTextArea;
	
	private JScrollPane commentScrollPane;
	
	private JLabel commenterNameLabel;

	
	SinaCore sinaWeibo = null;
	
	SinaUser sinaUser = null;
	URL commenterAvatarURL = null;
	URL commentarHomePageURL = null;
	String commenterName = null;
	String commentText = null;
	long commentId = 0L;
	ImageIcon Avatar = null;
	
	private JLabel deleteLabel;
	boolean isCommentToMe = false;

	public SingleCommentPanel(SinaCore sinaWeibo, Comment singleComment,
			boolean isCommentToMe) {
		initVariable(sinaWeibo, singleComment, isCommentToMe);
		initComponents();
	}

	private void initVariable(SinaCore sinaWeibo, Comment singleComment,
			boolean isCommentToMe) {
		this.sinaWeibo = sinaWeibo;
		sinaUser = singleComment.getUser();
		commenterAvatarURL = sinaUser.gtGetAvatar();
		commentarHomePageURL = sinaUser.gtGetHomePage();
		commenterName = singleComment.getUser().getName();
		commentText = singleComment.getText();
		commentId = singleComment.getId();
		Avatar = new ImageIcon(commenterAvatarURL);
		this.isCommentToMe = isCommentToMe;
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getAvatarButton(), new Constraints(new Leading(12, 48, 10, 10),
				new Leading(12, 48, 10, 10)));
		add(getCommentScrollPane(), new Constraints(new Bilateral(77, 12, 22),
				new Bilateral(13, 12, 22)));
		add(getCommenterNameLabel(), new Constraints(
				new Leading(6, 60, 46, 93), new Leading(69, 10, 10)));
		add(getDeleteLabel(), new Constraints(new Leading(6, 60, 46, 93),
				new Leading(90, 10, 10)));
		setSize(340, 120);
	}

	
	private JLabel getDeleteLabel() {
		if (deleteLabel == null) {
			deleteLabel = new JLabel();
			deleteLabel.setHorizontalAlignment(SwingConstants.CENTER);
			deleteLabel.setText("Delete");
			if (isCommentToMe) {
				deleteLabel.setVisible(true);
			} else {
				deleteLabel.setVisible(false);
			}
			deleteLabel.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent event) {
					deleteLabelMouseMouseClicked(event);
				}
			});
		}
		return deleteLabel;
	}

	
	private JButton getAvatarButton() {
		if (avatarButton == null) {
			avatarButton = new JButton();
			avatarButton.setIcon(Avatar);
			avatarButton.setToolTipText(commenterName);
			avatarButton.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent event) {
					avatarButtonMouseMouseClicked(event);
				}
			});
		}
		return avatarButton;
	}

	
	private JLabel getCommenterNameLabel() {
		if (commenterNameLabel == null) {
			commenterNameLabel = new JLabel();
			commenterNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			commenterNameLabel.setText(commenterName);
		}
		return commenterNameLabel;
	}

	
	private JScrollPane getCommentScrollPane() {
		if (commentScrollPane == null) {
			commentScrollPane = new JScrollPane();
			commentScrollPane.setViewportView(getJTextArea0());
		}
		return commentScrollPane;
	}

	private JTextArea getJTextArea0() {
		if (commentTextArea == null) {
			commentTextArea = new JTextArea();
			commentTextArea.setLineWrap(true);
			commentTextArea.setWrapStyleWord(true);
			commentTextArea.setBackground(new Color(158, 225, 246));
			commentTextArea.setEditable(false);
			commentTextArea.setText(commentText);
		}
		return commentTextArea;
	}

	private void avatarButtonMouseMouseClicked(MouseEvent event) {
		// 打开评论者主页
		System.out.println(this.commentarHomePageURL.toString());
		BareBonesBrowserLaunch.openURL(this.commentarHomePageURL.toString());
	}

	private void deleteLabelMouseMouseClicked(MouseEvent event) {
		// 删除在你发的博文中对你的评论
		if (sinaWeibo.gtDeleteCommentsToMe(commentId)) {
			JOptionPane.showMessageDialog(null, "删除评论成功：）", "", -1);
			deleteLabel.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, "删除评论失败：（", "", -1);
		}
	}
}
