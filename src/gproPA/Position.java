package gproPA;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class Position {
	final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
	final String POSITION_URL = "https://gpro.net/gb/RaceAnalysis.asp";
	final String USERNAME = "ito";
	final String PASSWORD = "alcres";

	public void getPosition() throws Exception {
		// # Go to login page
		Connection.Response loginPositionResponse = Jsoup.connect(POSITION_URL).method(Connection.Method.POST)
				.userAgent(USER_AGENT).execute();
		
		System.out.println(loginPositionResponse.parse().html());
	}
}
