package bookmarkBot;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter{
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String message = event.getMessage().getContentRaw();
		User author = event.getAuthor();
		MessageChannel channel = event.getChannel();
		if(!message.startsWith("bb") || event.getAuthor().isBot()) {
			return;
		}
		message = message.substring(2);
		String[] args = message.split(" ");
		if(args[0].equalsIgnoreCase("help")) {
			Embeds.sendInfoEmbed(event.getChannel(), event.getAuthor());
		}
		else if(args[0].equalsIgnoreCase("bookmark")) {
			Bookmark nbm = new Bookmark(author, args[1], args[2]);
			try {
				if(BookmarkUtil.hasUser(author) && BookmarkUtil.getBookmarkList(author).size() == 10) {
					Embeds.sendLimitEmbed(channel, author);
					return;
				}
				BookmarkUtil.setBookmark(author, nbm, channel);
				Embeds.sendSuccessEmbed(channel, author, nbm);
			} catch (Exception e) {
				e.printStackTrace();
				Embeds.sendErrorEmbed(channel, author);
			}
		}
		else if(args[0].equalsIgnoreCase("bookmarks")) {
			Embeds.sendBookmarkListEmbed(channel, author);
		}
		else if(args[0].equalsIgnoreCase("del")) {
			try {
				BookmarkUtil.removeBookmark(author, Integer.parseInt(args[1]));
			}
			catch(Exception e){
				Embeds.sendErrorEmbed(channel, author);
			}
		}
	}
}
