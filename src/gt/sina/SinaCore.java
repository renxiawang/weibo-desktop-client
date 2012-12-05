package gt.sina;
import java.util.List;

import weibo4j.Comment;
import weibo4j.Status;
import weibo4j.User;
import weibo4j.Weibo;
import weibo4j.WeiboException;
import weibo4j.http.AccessToken;
import weibo4j.http.RequestToken;
import weibo4j.org.json.JSONException;
import weibo4j.util.BareBonesBrowserLaunch;

public class SinaCore extends Weibo implements GtCore {

	private static final long serialVersionUID = 1L;
	//public  static RequestToken requestToken = null;
	public static final String CONSUMER_KEY = "4040337064";
	public static final String CONSUMER_SECRET = "4a4cb2f858001aa71776675ddcf49b74";
	
	/**
	 * 继承父类的构造方法
	 */
	public SinaCore(String id, String password) {
		super(id,password);
	}
	public SinaCore() {
		super();
	}
	public SinaCore(String baseURL) {
		super(baseURL);
	}
	public SinaCore(String id, String password, String baseURL) {
		super(id,password,baseURL);
	}
	
	
	
	/**这个传统登录，有疑问。 直接放在构造函数里。 这个可以不用
	 * */
	public boolean gtLoginByClassical(String loginName, String passwd){
		return true;
	}
	
/*
	*//**用loginGetPin打开浏览器，取得PIN
	* 输入PIN后，weibo得到验证
	*//*
	public void gtLoginGetPin(Object obj) {
		System.setProperty("weibo4j.oauth.consumerKey", CONSUMER_KEY);
		System.setProperty("weibo4j.oauth.consumerSecret", CONSUMER_SECRET);
		obj = (RequestToken)obj;
		try {
			obj = this.getOAuthRequestToken();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BareBonesBrowserLaunch.openURL(((RequestToken) obj).getAuthorizationURL());
	
	}
	
	*//**
	 * 输入PIN后，weibo得到验证
	 *//*
	public boolean gtLoginByOAuth(String pin,Object obj) {
		AccessToken accessToken = null;
		try{
            accessToken = ((RequestToken)obj).getAccessToken(pin);
        } catch (WeiboException te) {
            if(401 == te.getStatusCode()){
                System.out.println("Unable to get the access token.");
                return false;
            }else{
                te.printStackTrace();
                return false;
             }
          }
            
        this.setToken(accessToken.getToken(), accessToken.getTokenSecret());
        return true;
	}

*/
	/**home里显示的包括自己以及following的tweets
	*调用 weibo.java getHomeTimeline()  返回List<Status>
	*
	*/
	// 修改 Status --> SinaTweets
	public List<SinaTweets> gtGetAllTweets() {
		try {
			return this.getHomeTimeline();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			System.out.println("something wrong with getHomeTimeline");
			e.printStackTrace();
		}
		return null;
	}

	/**获取未读的信息数对象Count,包含了new mentions，new followers，等等
	 * 调用 weibo.java getUnread(), 返回Count对象，在User调用Count对象方法：gtGetComments(); gtGetRt(); gtGetMentions();
	 * */
	public Object gtGetUnread() {
		try {
			return this.getUnread();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**返回正在Follow的User list*/
	
	public List<SinaUser> gtGetFollowing() {
		try {
			return this.getFriends();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**返回粉丝 list
	 * */
	public List<SinaUser> gtGetFollower() {
		try {
			return this.getFollowers();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回@我的 tweet list
	 */
	public List<SinaTweets> gtGetMentionMe() {
		try {
			return this.getMentions();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取收藏List<Status>
	 */
	public List<SinaTweets> gtGetFav() {
		try {
			return this.getFavorites();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 我收到的comments。显示在Comments panel里
	 */
	public List<Comment> gtGetCommentsToMe() {
		try {
			return this.getCommentsToMe();		
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	/**
	 * 单一一条博文的comments，显示在Comments弹窗里
	 */
	public List<Comment> gtGetComments(String idOfStatu) {
		try {
			return this.getComments(idOfStatu);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发送博文，超字数会服务器会以Exception形式返回，需调用一个wordCounter先行计数
	 */
	public boolean gtSendTweet(String tweet) {
		if(wordCounter(tweet)) {
			try {
				this.updateStatus(tweet);
			} catch (WeiboException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
		}
		else{
			System.out.println("超字数了");
			return false;
		}
	}

	/**
	 * 通过status.getId()获取当前被评论博文的ID。。。返回Comment对象，加到Comment List里去
	 */
	public boolean gtSendComment(String comment, String idOfstatu) {
		try {
			this.updateComment(comment, idOfstatu, null);       //如果要回复别人的评论，修改null为评论id
			return true;
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * 转发微博，并提交评论
	 */
	public boolean gtReTweet(String idOfStatus, String text) {
		try {
			this.repost(idOfStatus, text);
			return true;
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	*将当前登录用户的Comments未读数设为0
	*/
	public boolean gtResetCommentsNum() {
		return this.setCommentsRead();
	}

	/**
	*将当前登录用户的Mentions未读数设为0
	*/
	public boolean gtResetMentionsNum() {
		return this.setMentionsRead();
	}

	/**
	*将当前登录用户的Messages未读数设为0
	*/
	public boolean gtResetMessagesNum() {
		return this.setMessagesRead();
	}

	/**
	*将当前登录用户的Follower未读数设为0
	*/
	public boolean gtResetFollowersNum() {
		return this.setFollowerRead();
	}
	/**
	 * 计数器，计算字符数是否超过140字
	 *
	 */
	public static boolean wordCounter(String tweet) {
		if(tweet.length() > 140) {
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * 收藏某条博文
	 */
	public boolean gtSetFav(long idOfStatu) {
		try {
			this.createFavorite(idOfStatu);
			return true;
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 取消博文的收藏
	 */
	public boolean gtSetUnfav(long idOfStatu) {
		try {
			this.destroyFavorite(idOfStatu);
			return true;
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean gtDeleteCommentsToMe(long commentId) {
		// TODO Auto-generated method stub
		try {
		this.destroyComment(commentId);
		return true;
		} catch (WeiboException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean gtSetUnfollow(String idOfUser) {
		try {
			destroyFriendship(idOfUser);
			return true;
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}