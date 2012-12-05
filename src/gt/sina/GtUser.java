package gt.sina;
import java.net.URL;


public interface GtUser {

	public String gtGetName();
	
	public String gtGetScreenName();
	
	public int gtGetFollowingsNum();
	
	public int gtGetFollowersNum();
	
	public int gtGetMyTweetsNum();
	
	public URL gtGetAvatar();
	
	public URL gtGetHomePage();
	
	public long gtGetNewTweetsNum(Object obj);  //t.sina 调用Count
	
	public long gtGetNewCommentsNum(Object obj);  //同上
	
	public long gtGetNewMentionsNum(Object obj);  //同上
	
	public long gtGetNewFollowersNum(Object obj); // 还是同上，好烦 = =！
	
	public String gtGetLocation();
	
}
