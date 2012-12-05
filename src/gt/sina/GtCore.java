package gt.sina;
import java.util.List;


public interface GtCore {
	
	public boolean gtLoginByClassical(String loginName, String passwd);
	
	
	public List gtGetAllTweets();   //sina 的是List<status>
	
	public Object gtGetUnread();    //sina 返回Count对象
	
	public List gtGetFollowing();    //sina: List<User>

	public List gtGetFollower();     //sina: List<User>
	
	public List gtGetMentionMe();		//sina:List<Status>
	
	public List gtGetFav(); 			//sina: List<Status>
	
	public List gtGetCommentsToMe(); 		//sina: List<Status>
	
	public List gtGetComments(String idOfStatu);		//sina:List<Comment>
	
	public boolean gtSendTweet(String tweet);			
	
	public boolean gtSendComment(String comment, String idOfstatu);
	
	public boolean gtReTweet(String idOfStatus, String text);
	
	public boolean gtResetCommentsNum();
	
	public boolean gtResetMentionsNum();
	
	public boolean gtResetMessagesNum();
	
	public boolean gtResetFollowersNum();
	
	public boolean gtSetFav(long idOfStatu);
	
	public boolean gtSetUnfav(long idOfStatu);
	
	public boolean gtDeleteCommentsToMe(long commentId);
	
	public boolean gtSetUnfollow(String idOfUser);

	
}
