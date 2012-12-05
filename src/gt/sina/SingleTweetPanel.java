package gt.sina;

import gt.sina.SinaCore;
import gt.sina.SinaTweets;
import gt.sina.SinaUser;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

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
import org.dyno.visual.swing.layouts.Trailing;

import weibo4j.Count;
import weibo4j.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

//VS4E -- DO NOT REMOVE THIS LINE!

public class SingleTweetPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton avatarButton2;
	
	private JLabel commentLabel;
	
	private JLabel retweetLabel;
	
	private JLabel favouriteLabel;
	
	private JLabel friendNameLabel;
	private JTextArea tweetTextArea;
	
	private JScrollPane tweetTextScrollPane;

	URL friendAvatarURL = null;
	URL friendHomePage = null;
	String friendName = null;
	String friendScreenName = null;
	
	SinaUser myselfUser = null;
	
	SinaUser friendUser = null;
	String tweetText = null;
	ImageIcon Avatar = null;
	long commentId = 0;
	long tweetId = 0;
	
	SinaTweets sinaTweet = null;
	
	SinaCore sinaWeibo = null;
	boolean isFavourite = false;
	List<Count> countlist = null;
	
	Count count = null;
	long commentsNum = 0;
	long retweetNum = 0;
	boolean isFavouritePanel = false;

	public SingleTweetPanel(SinaCore sinaWeibo, SinaTweets sinaTweet,
			boolean isFavouritePanel) throws WeiboException {
		initVariables(sinaWeibo, sinaTweet, isFavouritePanel);
		initComponents();
	}

	public void initVariables(SinaCore sinaWeibo, SinaTweets sinaTweet,
			boolean isFavouritePanel) throws WeiboException {
		// myselfUser = sinaWeibo.verifyCredentials();
		this.sinaWeibo = sinaWeibo;
		this.sinaTweet = sinaTweet;
		friendUser = (SinaUser) sinaTweet.gtGetUser();
		friendAvatarURL = friendUser.gtGetAvatar();
		friendHomePage = friendUser.gtGetHomePage();
		friendName = friendUser.gtGetName();
		friendScreenName = friendUser.getName();
		// commentId = sinaTweets.getId()
		tweetText = sinaTweet.gtGetText();
		tweetId = sinaTweet.gtGetStatusId();
		Avatar = new ImageIcon(friendAvatarURL);
		this.isFavourite = sinaTweet.gtIsFavorited();
		// countlist = sinaWeibo.getCounts(String.valueOf(tweetId));
		// Iterator it = countlist.iterator();
		// count = (Count)it.next();
		// commentsNum = count.getComments();
		// retweetNum = count.getRt();
		this.isFavouritePanel = isFavouritePanel;
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getAvatarButton2(), new Constraints(new Leading(12, 48, 10, 10),
				new Leading(12, 48, 10, 10)));
		add(getTweetTextScrollPane(), new Constraints(
				new Bilateral(77, 12, 22), new Leading(12, 94, 12, 12)));
		// add(getJLabel0(), new Constraints(new Leading(12, 46, 93), new
		// Leading(72, 12, 12)));
		add(getCommentLabel(), new Constraints(new Leading(77, 80, 10, 10),
				new Leading(112, 12, 12)));
		add(getRetweetLabel(), new Constraints(new Leading(150, 85, 85, 85),
				new Leading(112, 12, 12)));
		add(getFavouriteLabel(), new Constraints(new Leading(251, 69, 12, 12),
				new Leading(112, 12, 12)));
		add(getFriendNameLabel(), new Constraints(new Leading(4, 64, 46, 93),
				new Leading(66, 12, 12)));
		setSize(340, 140);
	}

	
	private JScrollPane getTweetTextScrollPane() {
		if (tweetTextScrollPane == null) {
			tweetTextScrollPane = new JScrollPane();
			tweetTextScrollPane.setViewportView(getJTextArea0());
		}
		return tweetTextScrollPane;
	}

	private JTextArea getJTextArea0() {
		if (tweetTextArea == null) {
			tweetTextArea = new JTextArea();
			tweetTextArea.setLineWrap(true);
			tweetTextArea.setWrapStyleWord(true);
			tweetTextArea.setBackground(new Color(158, 225, 246));
			tweetTextArea.setEditable(false);
			tweetTextArea.setText(friendName + " : " + tweetText);

		}
		return tweetTextArea;
	}

	
	private JLabel getFriendNameLabel() {
		if (friendNameLabel == null) {
			friendNameLabel = new JLabel();
			friendNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			friendNameLabel.setText(friendName);
		}
		return friendNameLabel;
	}

	
	private JLabel getFavouriteLabel() {
		if (favouriteLabel == null) {
			favouriteLabel = new JLabel();
			// 判断是否处于Favourite Panel下，若是，可删除收藏；若不是，可收藏
			if (isFavouritePanel) {
				favouriteLabel.setText("UnFav");
			} else {
				favouriteLabel.setText("Favourite");
			}

			favouriteLabel.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent event) {
					favouriteLabelMouseMouseClicked(event);
				}
			});
		}
		return favouriteLabel;
	}

	
	private JLabel getRetweetLabel() {
		if (retweetLabel == null) {
			retweetLabel = new JLabel();
			retweetLabel.setText("Retweet");
			retweetLabel.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent event) {
					retweetLabelMouseMouseClicked(event);
				}
			});
		}
		return retweetLabel;
	}

	
	private JLabel getCommentLabel() {
		if (commentLabel == null) {
			commentLabel = new JLabel();
			commentLabel.setText("Reply");
			commentLabel.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent event) {
					commentLabelMouseMouseClicked(event);
				}
			});
		}
		return commentLabel;
	}

	
	private JButton getAvatarButton2() {
		if (avatarButton2 == null) {
			avatarButton2 = new JButton();
			avatarButton2.setIcon(Avatar);
			avatarButton2.setToolTipText(friendName);
			avatarButton2.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent event) {
					avatarButton2MouseMouseClicked(event);
				}
			});
		}
		return avatarButton2;
	}

	private void avatarButton2MouseMouseClicked(MouseEvent event) {
		// 打开博文博主的主页
		System.out.println(this.friendHomePage.toString());
		BareBonesBrowserLaunch.openURL(this.friendHomePage.toString());
	}

	private void commentLabelMouseMouseClicked(MouseEvent event) {
		// 对当前博文进行评论
		new CommentFrame(sinaWeibo, String.valueOf(this.tweetId))
				.setVisible(true);
	}

	private void retweetLabelMouseMouseClicked(MouseEvent event) {
		// 转发当前博文
		new RetweetFrame(sinaWeibo, String.valueOf(this.tweetId), sinaTweet)
				.setVisible(true);
	}

	private void favouriteLabelMouseMouseClicked(MouseEvent event) {
		// 收藏或删除收藏当前博文
		if (isFavouritePanel) {
			if (sinaWeibo.gtSetUnfav(tweetId)) {
				JOptionPane.showMessageDialog(null, "取消收藏成功：）", "", -1);
				favouriteLabel.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "取消收藏失败：）", "", -1);
			}
		} else {
			if (sinaWeibo.gtSetFav(tweetId)) {
				JOptionPane.showMessageDialog(null, "收藏成功：）", "", -1);
				favouriteLabel.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "收藏失败：（", "error", -1);
			}
		}

	}
}