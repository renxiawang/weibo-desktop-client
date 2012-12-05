package gt.sina;
import java.util.Date;


public interface GtTweets {

	public Object gtGetUser();  //sina:return User对象
	
	public String gtGetText();
	
	public Date gtGetCreatTime();
	
	public long gtGetStatusId();
	
	public boolean gtIsFavorited();
	
}
