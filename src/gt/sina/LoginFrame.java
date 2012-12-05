package gt.sina;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;

import weibo4j.Weibo;
import weibo4j.WeiboException;
import weibo4j.http.AccessToken;
import weibo4j.http.RequestToken;
import weibo4j.util.BareBonesBrowserLaunch;


public class LoginFrame extends javax.swing.JFrame {

	/**
	 * 创建Sina微博帐号
	 *
	 */

	public static SinaCore sinaWeibo = null;
	/**
	 * 创建requestToken 
	 */
	public static RequestToken requestToken = null;

	/** Creates new form LoginFrame */
	public LoginFrame() {
		initComponents();
	}

	private void initComponents() {

		classicalLoginPanel = new JPanel();
		loginNameTextField = new JTextField();
		loginNameLabel = new JLabel();
		passwdLabel = new JLabel();
		PasswordField = new JPasswordField();
		LoginButton = new JButton();
		changeToOauthButton = new JButton();
		oAuthLoginPanel = new JPanel();
		pinLabel = new JLabel();
		pinTextField = new JTextField();
		verifyButton = new JButton();
		changeToClassicalButton = new JButton();
		getPinButton = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("GodTweet Login");
		setResizable(false);
		
		// 传统登录面板
		classicalLoginPanel.setBackground(new Color(158, 238, 250));
		classicalLoginPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		loginNameLabel.setText("Login Name:");

		passwdLabel.setText("PassWord:");

		LoginButton.setText("Login");
		LoginButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				try {
					LoginButtonMouseClicked(evt);
				} catch (WeiboException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		changeToOauthButton.setText("Change to oAuth Login Mode ---->");
		changeToOauthButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				changeToOauthButtonMouseClicked(evt);
			}
		});

        GroupLayout classicalLoginPanelLayout = new GroupLayout(classicalLoginPanel);
        classicalLoginPanel.setLayout(classicalLoginPanelLayout);
        classicalLoginPanelLayout.setHorizontalGroup(
            classicalLoginPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(classicalLoginPanelLayout.createSequentialGroup()
                .addGroup(classicalLoginPanelLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(classicalLoginPanelLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(classicalLoginPanelLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(loginNameLabel)
                            .addComponent(passwdLabel))
                        .addGap(12, 12, 12)
                        .addGroup(classicalLoginPanelLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(PasswordField, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
                            .addComponent(loginNameTextField, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(classicalLoginPanelLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(LoginButton, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
                    .addGroup(Alignment.TRAILING, classicalLoginPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(changeToOauthButton, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        classicalLoginPanelLayout.setVerticalGroup(
            classicalLoginPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(classicalLoginPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(classicalLoginPanelLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(classicalLoginPanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(loginNameLabel))
                    .addComponent(loginNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(classicalLoginPanelLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(passwdLabel)
                    .addComponent(PasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(LoginButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(changeToOauthButton)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        
        // oAuth登录面板
        oAuthLoginPanel.setVisible(false);
        oAuthLoginPanel.setBackground(new Color(158, 238, 250));
        oAuthLoginPanel.setPreferredSize(new Dimension(433, 264));

        pinLabel.setText("PIN number:");

        verifyButton.setText("Verify");
        verifyButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                try {
					verifyButtonMouseClicked(evt);
				} catch (WeiboException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        changeToClassicalButton.setText("<---- Change to Classical Login Mode");
        changeToClassicalButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                changeToClassicalButtonMouseClicked(evt);
            }
        });

        getPinButton.setText("Click and get a PIN number");
        getPinButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                getPinButtonMouseClicked(evt);
            }
        });

        GroupLayout oAuthLoginPanelLayout = new GroupLayout(oAuthLoginPanel);
        oAuthLoginPanel.setLayout(oAuthLoginPanelLayout);
        oAuthLoginPanelLayout.setHorizontalGroup(
            oAuthLoginPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(oAuthLoginPanelLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(oAuthLoginPanelLayout.createParallelGroup(Alignment.LEADING, false)
                    .addGroup(oAuthLoginPanelLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(pinLabel)
                        .addGap(12, 12, 12)
                        .addComponent(pinTextField, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
                    .addGroup(oAuthLoginPanelLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(verifyButton, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
                    .addComponent(changeToClassicalButton, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addComponent(getPinButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        oAuthLoginPanelLayout.setVerticalGroup(
            oAuthLoginPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(oAuthLoginPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(getPinButton)
                .addGap(18, 18, 18)
                .addGroup(oAuthLoginPanelLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(oAuthLoginPanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(pinLabel))
                    .addComponent(pinTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(verifyButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(changeToClassicalButton)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(classicalLoginPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addComponent(oAuthLoginPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(classicalLoginPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addComponent(oAuthLoginPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

	private void changeToOauthButtonMouseClicked(MouseEvent evt) {
		// TODO add your handling code here: 变换至oAuth登录方式
		classicalLoginPanel.setVisible(false);
		oAuthLoginPanel.setVisible(true);
	}

	private void changeToClassicalButtonMouseClicked(MouseEvent evt) {
		// TODO add your handling code here: 变换至传统登录方式
		oAuthLoginPanel.setVisible(false);
		classicalLoginPanel.setVisible(true);
	}

	private void LoginButtonMouseClicked(MouseEvent evt) throws WeiboException {
		// TODO add your handling code here: 传统登录
		boolean sign = true;
		//sinaWeibo = new SinaCore("godtweet@sina.com", "godtweet");

		sinaWeibo = new SinaCore(loginNameTextField.getText(),
				PasswordField.getText());
		try {
			sinaWeibo.verifyCredentials();
		} catch (WeiboException te) {
			if (401 == te.getStatusCode()) {
				JOptionPane.showMessageDialog(null, "登录失败", "error", -1);
				sign = false;
			}
		}
		
		// 登录成功，启动主窗体，隐藏登录窗体
		if (sign) {
			System.out.println("successful!");
			this.setVisible(false);
			this.dispose();
			new MainFrame(sinaWeibo).setVisible(true);
		}
	}

	private void getPinButtonMouseClicked(MouseEvent evt) {
		// TODO add your handling code here: 获取PIN码
		System.setProperty("weibo4j.oauth.consumerKey", SinaCore.CONSUMER_KEY);
		System.setProperty("weibo4j.oauth.consumerSecret",
				SinaCore.CONSUMER_SECRET);

		try {
			sinaWeibo = new SinaCore();
			requestToken = sinaWeibo.getOAuthRequestToken();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BareBonesBrowserLaunch.openURL(requestToken.getAuthorizationURL());
	}

	private void verifyButtonMouseClicked(MouseEvent evt) throws WeiboException {
		// TODO add your handling code here: 验证PIN码，登录微博
		AccessToken accessToken = null;
		boolean sign = true;

		if (requestToken == null) {
			JOptionPane.showMessageDialog(null, "请输入正确的PIN码", "error", -1);
			sign = false;
		}
		try {
			accessToken = requestToken.getAccessToken(pinTextField.getText()
					.trim());
		} catch (WeiboException te) {
			JOptionPane.showMessageDialog(null, "请输入正确的PIN码", "error", -1);
			sign = false;
		}

		sinaWeibo
				.setToken(accessToken.getToken(), accessToken.getTokenSecret());
		try {
			sinaWeibo.verifyCredentials();
		} catch (WeiboException te) {
			JOptionPane.showMessageDialog(null, "请输入正确的PIN码", "error", -1);
			sign = false;
		}

		// 登录成功，启动主窗体，隐藏登录窗体
		if (sign) {
			this.setVisible(false);
			System.out.println("successful!");
			new MainFrame(sinaWeibo).setVisible(true);

		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new LoginFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private JButton LoginButton;
	private JPasswordField PasswordField;
	private JButton changeToClassicalButton;
	private JButton changeToOauthButton;
	private JPanel classicalLoginPanel;
	private JButton getPinButton;
	private JLabel loginNameLabel;
	private JTextField loginNameTextField;
	private JPanel oAuthLoginPanel;
	private JLabel passwdLabel;
	private JLabel pinLabel;
	private JTextField pinTextField;
	private JButton verifyButton;
	// End of variables declaration//GEN-END:variables

}