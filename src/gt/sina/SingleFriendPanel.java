package gt.sina;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import weibo4j.util.BareBonesBrowserLaunch;

//VS4E -- DO NOT REMOVE THIS LINE!

public class SingleFriendPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton avatarButton;
	
	private JLabel nameLabel;

	private JLabel locationLabel;
	
	private JLabel unFollowLabel;
	
	private JLabel folloerNumLabel;
	
	
	SinaUser friendUser = null;
	
	SinaCore sinaWeibo = null;
	URL friendAvatarURL = null;
	URL friendHomePage = null;
	String friendName = null;
	String friendLocation = null;
	long friendFollowerNum = 0;
	ImageIcon Avatar=null;
	boolean isFollowerPanel = false;
	
	public SingleFriendPanel(SinaUser singleUser, boolean isFollowerPanel,SinaCore sinaWeibo) {
		initVariable(singleUser, isFollowerPanel,sinaWeibo);
		initComponents();
	}
	
	public void initVariable(SinaUser singleUser, boolean isFollowerPanel,SinaCore sinaWeibo) {
		this.sinaWeibo = sinaWeibo;
		friendUser = singleUser;
		friendAvatarURL = friendUser.gtGetAvatar();
		friendHomePage = friendUser.gtGetHomePage();
		friendName = friendUser.gtGetName();
		friendLocation = friendUser.gtGetLocation();
		friendFollowerNum = friendUser.gtGetFollowersNum();
		Avatar = new ImageIcon(friendAvatarURL);
		this.isFollowerPanel = isFollowerPanel;
	}
	
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getAvatarButton(), new Constraints(new Leading(12, 48, 10, 10), new Leading(12, 48, 10, 10)));
		add(getNameLabel(), new Constraints(new Leading(72, 99, 10, 10), new Leading(12, 23, 10, 10)));
		add(getLocationLabel(), new Constraints(new Leading(72, 121, 10, 10), new Leading(41, 24, 10, 10)));
		add(getUnFollowLabel(), new Constraints(new Trailing(12, 97, 10, 10), new Leading(12, 23, 12, 12)));
		add(getFolloerNumLabel(), new Constraints(new Trailing(12, 137, 145, 205), new Leading(43, 20, 12, 12)));
		setSize(340, 77);
	}

	
	private JLabel getFolloerNumLabel() {
		if (folloerNumLabel == null) {
			folloerNumLabel = new JLabel();
			folloerNumLabel.setText("Follower : " + friendFollowerNum);
		}
		return folloerNumLabel;
	}

	
	private JLabel getUnFollowLabel() {
		if (unFollowLabel == null) {
			unFollowLabel = new JLabel();
			unFollowLabel.setText("UnFollow");
			if(isFollowerPanel) {
				unFollowLabel.setVisible(false);
			} else {
				unFollowLabel.setVisible(true);
			}
			unFollowLabel.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					unFollowLabelMouseMouseClicked(event);
				}
			});
		}
		return unFollowLabel;
	}


	
	private JLabel getLocationLabel() {
		if (locationLabel == null) {
			locationLabel = new JLabel();
			locationLabel.setText(friendLocation);
		}
		return locationLabel;
	}

	
	private JLabel getNameLabel() {
		if (nameLabel == null) {
			nameLabel = new JLabel();
			nameLabel.setText(friendName);
		}
		return nameLabel;
	}

	
	private JButton getAvatarButton() {
		if (avatarButton == null) {
			avatarButton = new JButton();
			avatarButton.setIcon(Avatar);
			avatarButton.setToolTipText(friendName);
			avatarButton.addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent event) {
					avatarButtonMouseMouseClicked(event);
				}
			});
		}
		return avatarButton;
	}
	
	private void avatarButtonMouseMouseClicked(MouseEvent event) {
		//打开评论者主页
		System.out.println(this.friendHomePage.toString());
		BareBonesBrowserLaunch.openURL(this.friendHomePage.toString());
	}
	
	private void unFollowLabelMouseMouseClicked(MouseEvent event) {
		
		if(sinaWeibo.gtSetUnfollow(String.valueOf(friendUser.getId()))) {
			JOptionPane.showMessageDialog(null, "取消关注成功：）", "", -1);
			unFollowLabel.setVisible(false);
			} else {
			JOptionPane.showMessageDialog(null, "取消关注失败：（", "", -1);
			}
		
	}
}
