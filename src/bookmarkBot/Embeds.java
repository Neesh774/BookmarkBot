package bookmarkBot;

import java.awt.Color;
import java.time.Instant;
import java.util.ArrayList;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class Embeds {
	public static void sendInfoEmbed(MessageChannel channel, User user) {
			EmbedBuilder info = new EmbedBuilder();
			info.setColor(Color.blue);
			info.setTitle("The Bookmark Bot");
			info.addField("`bbookmark <link> <title>`", "Set a bookmark at this link.", false);
			info.addField("`bbookmarks`", "Get a list of your bookmarks", false);
			info.addField("`bdel <BookmarkID>`", "Delete the bookmark with this id.", false);
			info.addField("Requested by ", user.getAsMention(), false);
			info.setTimestamp(Instant.now());
			channel.sendMessage(info.build()).queue();
	}
	public static void sendBookmarkListEmbed(MessageChannel channel, User user) {
		if(BookmarkUtil.hasUser(user) && BookmarkUtil.getBookmarkList(user).size() > 0) {
			EmbedBuilder list = new EmbedBuilder();
			list.setColor(Color.red);
			list.setTitle("Your Bookmarks");
			ArrayList<Bookmark> bms = BookmarkUtil.getBookmarkList(user);
			int length = bms.size();
			
			for(int i = 0;i < length;i ++){
				list.addField((i+1) + ". " + bms.get(i).getTitle(), bms.get(i).getURL(), false);
			}
			list.addField("Requested by ", user.getAsMention(), false);
			list.setTimestamp(Instant.now());
			channel.sendMessage(list.build()).queue();
		}
		else {
			EmbedBuilder noBms = new EmbedBuilder();
			noBms.setColor(Color.red);
			noBms.setTitle("You don't seem to have any bookmarks");
			noBms.setDescription("Try using `bhelp` to find out how to set one.");
			noBms.addField("Requested by ", user.getAsMention(), false);
			noBms.setTimestamp(Instant.now());
			channel.sendMessage(noBms.build()).queue();;
		}
	}
	public static void sendSuccessEmbed(MessageChannel channel, User user, Bookmark bm) {
		EmbedBuilder success = new EmbedBuilder();
		success.setColor(Color.red);
		success.setTitle("Bookmark set successfully");
		success.setDescription("You set a bookmark called " + bm.getTitle() +" at " + bm.getURL());
		success.addField("Requested by ", user.getAsMention(), false);
		success.setTimestamp(Instant.now());
		channel.sendMessage(success.build()).queue();
	}
	public static void sendErrorEmbed(MessageChannel channel, User user) {
		EmbedBuilder error = new EmbedBuilder();
		error.setColor(Color.red);
		error.setTitle("There was an error");
		error.setDescription("Try that again. Make sure you're using the proper arguments");
		error.addField("Requested by ", user.getAsMention(), false);
		error.setTimestamp(Instant.now());
		channel.sendMessage(error.build()).queue();
	}
	public static void sendDupeEmbed(MessageChannel channel, User user, String dupe) {
		EmbedBuilder error = new EmbedBuilder();
		error.setColor(Color.red);
		error.setTitle("Looks like you already have a bookmark called " + dupe);
		error.setDescription("Try that again, with a different title");
		error.addField("Requested by ", user.getAsMention(), false);
		error.setTimestamp(Instant.now());
		channel.sendMessage(error.build()).queue();
	}
	public static void sendLimitEmbed(MessageChannel channel, User user) {
		EmbedBuilder error = new EmbedBuilder();
		error.setColor(Color.red);
		error.setTitle("Too many bookmarks!");
		error.setDescription("You have reached your limit of 10 bookmarks.");
		error.addField("Requested by ", user.getAsMention(), false);
		error.setTimestamp(Instant.now());
		channel.sendMessage(error.build()).queue();
	}
	public static void sendURLEmbed(MessageChannel channel, User user) {
		EmbedBuilder url = new EmbedBuilder();
		url.setColor(Color.red);
		url.setTitle("There was an error");
		url.setDescription("Looks like that wasn't a proper URL. Try that again");
		url.addField("Requested by ", user.getAsMention(), false);
		url.setTimestamp(Instant.now());
		channel.sendMessage(url.build()).queue();
	}
	public static void sendSuccessDeleteEmbed(MessageChannel channel, User user, Bookmark bm) {
		EmbedBuilder success = new EmbedBuilder();
		success.setColor(Color.red);
		success.setTitle("Bookmark deleted successfully");
		success.setDescription("You deleted a bookmark called " + bm.getTitle() +" at " + bm.getURL());
		success.addField("Requested by ", user.getAsMention(), false);
		success.setTimestamp(Instant.now());
		channel.sendMessage(success.build()).queue();
	}
}
