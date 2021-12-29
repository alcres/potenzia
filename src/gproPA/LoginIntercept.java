package gproPA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

import model.Vuelta;

public class LoginIntercept {
	// # Constants used in this example
	final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
	final String LOGIN_FORM_URL = "https://gpro.net/es/gpro.asp";
	final String USERNAME = "ito";
	final String PASSWORD = "alcres";
	final String POSITION_URL = "https://gpro.net/es/RaceAnalysis.asp";

	public void doLogin() throws Exception {
		// # Go to login page
		Connection.Response loginFormResponse = Jsoup.connect(LOGIN_FORM_URL).method(Connection.Method.POST)
				.userAgent(USER_AGENT).execute();

		// # Fill the login form
		// ## Find the form first...
		FormElement loginForm = (FormElement) loginFormResponse.parse().select("div#login > form").first();

		// ## ... then "type" the username ...
		Element loginField = loginForm.select("#Text1").first();
		loginField.val(USERNAME);

		// ## ... and "type" the password
		Element passwordField = loginForm.select("#Password2").first();
		passwordField.val(PASSWORD);

		// # Now send the form for login
		Connection.Response loginActionResponse = loginForm.submit().cookies(loginFormResponse.cookies())
				.userAgent(USER_AGENT).execute();

		Connection.Response positionResponse = Jsoup.connect(POSITION_URL).method(Connection.Method.POST)
				.userAgent(USER_AGENT).cookies(loginFormResponse.cookies()).execute();
		// System.out.print(positionResponse.parse().html());
		Document doc = Jsoup.parse(positionResponse.parse().html());

		Elements masthead = doc.select("table.borderbottom");

		// System.out.print(masthead.html());
		Element table = masthead.get(0);
		
		//GET RACE LAPS ANALISYS
		Element laps = masthead.get(2);
		List<Vuelta> vueltas = new ArrayList<>();
		Elements trs = laps.select("tr");
		String[][] trtd = new String[trs.size()][];

		for (int i = 2; i < trs.size(); i++) {
			Elements tds = trs.get(i).select("td");
			trtd[i] = new String[tds.size()];
			Vuelta v = new Vuelta();
			for (int j = 0; j < tds.size(); j++) {
				if (j == 0) {
					v.setVuelta(tds.get(j).text());
				}
				if (j == 1) {
					v.setTiempo(tds.get(j).text());
				}
				if (j == 2) {
					v.setPosicion(tds.get(j).text());
				}
				if (j == 3) {
					v.setNeumatico(tds.get(j).text());
				}
				if (j == 4) {
					v.setClima(tds.get(j).text());
				}
				if (j == 5) {
					v.setTemp(tds.get(j).text().replace("°", ""));
				}
				if (j == 6) {
					v.setHum(tds.get(j).text());
				}
				if (j == 7) {
					v.setEventos(tds.get(j).text());
				}
			}
			vueltas.add(v);
		}

		for (Vuelta v : vueltas) {
			System.out.print(v.getVuelta() + " | ");
			System.out.print(v.getTiempo() + " | ");
			System.out.print(v.getTemp() + " | ");
			System.out.println(v.getClima());
		}
	}

	public boolean checkElement(String name, Element elem) {
		if (elem == null) {
			throw new RuntimeException("Unable to find " + name);
		}
		return true;
	}
}
