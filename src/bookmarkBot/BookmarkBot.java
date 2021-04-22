package bookmarkBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class BookmarkBot {
	public static JDA jda;
	public static String prefix = "bb";
	public static void main(String[] args) throws LoginException, FileNotFoundException {
		Scanner token = new Scanner(new File("token.txt"));
		String tk = token.nextLine();
		jda = JDABuilder.createDefault(tk).build();
		jda.getPresence().setStatus(OnlineStatus.IDLE);
		jda.getPresence().setActivity(Activity.watching("bbhelp"));
		jda.addEventListener(new Commands());
	}

}
