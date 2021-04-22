package bookmarkBot;

import net.dv8tion.jda.api.entities.User;

public class Bookmark {
	private User owner;
	private String url;
	private String title;
	public Bookmark(User nowner, String nurl, String ntitle) {
		owner = nowner;
		url = nurl;
		title = ntitle;
	}
	public void setTitle(String ntitle) {
		title = ntitle;
	}
	public User getOwner() {
		return owner;
	}
	public String getURL() {
		return url;
	}
	public String getTitle() {
		return title;
	}
}
