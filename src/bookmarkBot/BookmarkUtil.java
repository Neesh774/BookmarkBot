package bookmarkBot;

import java.util.ArrayList;
import java.util.HashMap;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class BookmarkUtil {
	public static HashMap<Long, ArrayList<Bookmark>> bookmarks = new HashMap<>();
	
	public static boolean hasUser(User owner) {
		return bookmarks.containsKey(owner.getIdLong());
	}
	
	public static void setBookmark(User owner, Bookmark bm, MessageChannel channel) throws Exception{
		if(hasUser(owner) && bookmarks.get(owner.getIdLong()).size() <11) {
			bookmarks.get(owner.getIdLong()).add(bm);
		}
		else if(!hasUser(owner)){
			ArrayList<Bookmark> temp = new ArrayList<>();
			temp.add(bm);
			bookmarks.put(owner.getIdLong(), temp);
		}
		else {
			throw new Exception();
		}
	}
	public static ArrayList<Bookmark> getBookmarkList(User owner) {
		return bookmarks.get(owner.getIdLong());
	}
	public static void removeBookmark(User owner, int index) {
		bookmarks.get(owner).remove(index);
	}
}
