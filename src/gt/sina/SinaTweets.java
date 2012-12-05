package gt.sina;
import java.util.Date;

import org.w3c.dom.Element;

import weibo4j.Status;
import weibo4j.Weibo;
import weibo4j.WeiboException;
import weibo4j.http.Response;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;


public class SinaTweets extends Status implements GtTweets {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SinaTweets(JSONObject json) throws WeiboException, JSONException {
		super(json);
		// TODO Auto-generated constructor stub
	}

	SinaTweets(Response res) throws WeiboException {
		super(res);
		// TODO Auto-generated constructor stub
	}

	SinaTweets(Response res, Element elem, Weibo weibo) throws WeiboException {
		super(res, elem, weibo);
		// TODO Auto-generated constructor stub
	}

	SinaTweets(Response res, Weibo weibo) throws WeiboException {
		super(res, weibo);
		// TODO Auto-generated constructor stub
	}

	
	public SinaTweets(String str) throws WeiboException, JSONException {
		super(str);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取该博文的发表者 返回User对象
	 */
	public Object gtGetUser() {
		return this.getUser();
	}

	@Override
	public String gtGetText() {
		return this.getText();
	}

	@Override
	public Date gtGetCreatTime() {
		return this.getCreatedAt();
	}

		
	public long  gtGetStatusId() {
		return this.getId();
	}

	@Override
	public boolean gtIsFavorited() {
		// TODO Auto-generated method stub
		return this.isFavorited();
	}
	

}
