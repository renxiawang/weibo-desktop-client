package gt.sina;

import java.net.MalformedURLException;
import java.net.URL;

import org.w3c.dom.Element;

import weibo4j.Count;
import weibo4j.User;
import weibo4j.Weibo;
import weibo4j.WeiboException;
import weibo4j.http.Response;
import weibo4j.org.json.JSONObject;

public class SinaUser extends User implements GtUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 继承父类的构造方法
	 */
	public SinaUser(Response res, Weibo weibo) throws WeiboException {
		super(res, weibo);
		// TODO Auto-generated constructor stub
	}

	public SinaUser(JSONObject json) throws WeiboException {
		super(json);
		// TODO Auto-generated constructor stub
	}

	public SinaUser(Response res, Element elem, Weibo weibo)
			throws WeiboException {
		super(res, elem, weibo);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 返回User对象的Name
	 **/
	public String gtGetName() {
		return this.getName();

	}

	/**
	 * 返回User对象的ScreenName
	 */
	public String gtGetScreenName() {
		return this.getScreenName();
	}

	/**
	 * 返回User对象的following数目
	 * */
	public int gtGetFollowingsNum() {
		return this.getFriendsCount();
	}

	/**
	 * 返回User对象的Follower数目
	 * */
	public int gtGetFollowersNum() {
		return this.getFollowersCount();
	}

	/**
	 * 返回User对象发布的博文数目
	 * */
	public int gtGetMyTweetsNum() {
		return this.getStatusesCount();
	}

	/**
	 * 返回User对象的头像地址，返回URL
	 * */
	public URL gtGetAvatar() {
		return this.getProfileImageURL();
	}

	/**
	 * 返回User对象的homepage ，返回URL
	 * */
	public URL gtGetHomePage() {
		URL homepage = null;
		try {
			homepage = new URL("http://t.sina.com.cn/" + this.getId());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return homepage;
	}

	/**
	 * 利用SinaCore new 一个weibo对象（其实就是博主自己） ， 调用方法 getUnread() 返回Count 对象
	 * 再调用Count里面的方法，取得new数目
	 * 
	 * 取得Count对象后，count.getRt():long //这个Rt我不敢确定，但除了get私信，就只有这个了。
	 **/
	public long gtGetNewTweetsNum(Object obj) {
		return ((Count) obj).getRt();
	}

	/**
	 * 取得Count对象后，count.getComments():long 返回未读comments数目
	 * */
	public long gtGetNewCommentsNum(Object obj) {
		return ((Count) obj).getComments();
	}

	/**
	 * 取得Count对象后，count.getMentions():long 返回未读Mentions数目
	 * */
	public long gtGetNewMentionsNum(Object obj) {
		return ((Count) obj).getMentions();
	}

	/**
	 * 取得Count对象后，count.getFollowers():long 返回新Follower数目
	 * */
	public long gtGetNewFollowersNum(Object obj) {
		return ((Count) obj).getFollowers();
	}

	/**
	 * 返回User Location
	 */
	public String gtGetLocation() {
		return this.getLocation();
	}

}
