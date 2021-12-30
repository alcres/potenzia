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

import model.Coche;
import model.Niveles;
import model.NivelesFin;
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



		Elements coche = doc.select("table.styled.bordered.center");
		Element c = coche.get(0);
		Elements trs = c.select("tr");
		String[][] trtd = new String[trs.size()][];
		Coche car = new Coche();
		Niveles nivelInicio = new Niveles();
		NivelesFin nivelesFin = new NivelesFin();
		for (int i = 2; i < trs.size(); i++) {
			Elements tds = trs.get(i).select("td");
			trtd[i] = new String[tds.size()];



			for (int j = 0; j < tds.size(); j++) {
			//	System.out.println(i+": |"+j+": | TEXTO: "+tds.get(j).text());
				if (j == 0) {
					switch (i) {
						case 2:
							car.setChassis(Integer.parseInt(tds.get(j).text()));
							break;
						case 4:
							nivelInicio.setChassis(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
						case 6:
							nivelesFin.setChassis(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
					}
				}
				if (j == 1) {
					switch (i) {
						case 2:
							car.setMotor(Integer.parseInt(tds.get(j).text()));
							break;
						case 4:
							nivelInicio.setMotor(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
						case 6:
							nivelesFin.setMotor(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
					}
				}
				if (j == 2) {
					switch (i) {
						case 2:
							car.setAleD(Integer.parseInt(tds.get(j).text()));
							break;
						case 4:
							nivelInicio.setAleD(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
						case 6:
							nivelesFin.setAleD(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
					}
				}
				if (j == 3) {
					switch (i) {
						case 2:
							car.setAleT(Integer.parseInt(tds.get(j).text()));
							break;
						case 4:
							nivelInicio.setAleT(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
						case 6:
							nivelesFin.setAleT(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
					}
				}
				if (j == 4) {
					switch (i) {
						case 2:
							car.setFondo(Integer.parseInt(tds.get(j).text()));
							break;
						case 4:
							nivelInicio.setFondo(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
						case 6:
							nivelesFin.setFondo(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
					}
				}
				if (j == 5) {
					switch (i) {
						case 2:
							car.setRefrigeracion(Integer.parseInt(tds.get(j).text()));
							break;
						case 4:
							nivelInicio.setRefrigeracion(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
						case 6:
							nivelesFin.setRefrigeracion(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
					}
				}
				if (j == 6) {
					switch (i) {
						case 2:
							car.setPontones(Integer.parseInt(tds.get(j).text()));
							break;
						case 4:
							nivelInicio.setPontones(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
						case 6:
							nivelesFin.setPontones(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
					}
				}
				if (j == 7) {
					switch (i) {
						case 2:
							car.setCaja(Integer.parseInt(tds.get(j).text()));
							break;
						case 4:
							nivelInicio.setCaja(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
						case 6:
							nivelesFin.setCaja(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
					}
				}
				if (j == 8) {
					switch (i) {
						case 2:
							car.setFrenos(Integer.parseInt(tds.get(j).text()));
							break;
						case 4:
							nivelInicio.setFrenos(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
						case 6:
							nivelesFin.setFrenos(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
					}
				}
				if (j == 9) {
					switch (i) {
						case 2:
							car.setSuspension(Integer.parseInt(tds.get(j).text()));
							break;
						case 4:
							nivelInicio.setSuspension(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
						case 6:
							nivelesFin.setSuspension(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
					}
				}
				if (j == 10) {
					switch (i) {
						case 2:
							car.setElectrico(Integer.parseInt(tds.get(j).text()));
							break;
						case 4:
							nivelInicio.setElectrico(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
						case 6:
							nivelesFin.setElectrico(Integer.parseInt(tds.get(j).text().substring(0,tds.get(j).text().length()-1)));
							break;
					}
				}
			}
		}
		// System.out.print(masthead.html());
			Elements masthead = doc.select("table.borderbottom");
		Element table = masthead.get(0);


		List<Vuelta> vueltas = getVueltas(masthead);

		for (Vuelta v : vueltas) {
			System.out.print(v.getVuelta() + " | ");
			System.out.print(v.getTiempo() + " | ");
			System.out.print(v.getTemp() + " | ");
			System.out.println(v.getClima());
		}
	}

	private List<Vuelta> getVueltas(Elements masthead) {
		//GET RACE LAPS ANALISYS
		Element laps = masthead.get(2);
		List<Vuelta> vueltas = new ArrayList<>();
		Elements trs = laps.select("tr");
		String[][] trtd = new String[trs.size()][];
		int boost = 0;
		for (int i = 2; i < trs.size(); i++) {
			Elements tds = trs.get(i).select("td");
			trtd[i] = new String[tds.size()];
			Vuelta v = new Vuelta();

			for (int j = 0; j < tds.size(); j++) {
				if (j == 0) {
					v.setVuelta(tds.get(j).text());
					if(tds.get(j).text().contains("B")){
						boost++;
					}
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
		System.out.println("Vueltas Boost: "+ boost);
		return vueltas;
	}

	public boolean checkElement(String name, Element elem) {
		if (elem == null) {
			throw new RuntimeException("Unable to find " + name);
		}
		return true;
	}
}
