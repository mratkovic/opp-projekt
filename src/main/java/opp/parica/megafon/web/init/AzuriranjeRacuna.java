package opp.parica.megafon.web.init;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.Oglasivac;
import opp.parica.megafon.model.TipClanstva;

public class AzuriranjeRacuna implements ServletContextListener {
	private static final long serialVersionUID = 1L;
	Timer timer = null;

	class AzurirajKorisnikeTast extends TimerTask {
		@Override
		public void run() {
			Date danas = new Date();

			TipClanstva besplatniTip = DAOProvider.getDAO().dohvatiTipClanstva("Besplatni");
			System.out.println("[AzuriranjeRacuna] pokrenutno @ " + danas);
			List<Oglasivac> oglasivaci = DAOProvider.getDAO().dohvatiSveOglasavace();
			for (Oglasivac oglasivac : oglasivaci) {
				if (!oglasivac.getTipClanstva().equals(besplatniTip)) {
					if (oglasivac.getDatumIstekaClanarine().compareTo(danas) < 0) {

						System.out.println("[AzuriranjeRacuna] oglasivac"
							+ oglasivac.getUsername() + " istekla clanarina. Azurirano");
						oglasivac.setDatumIstekaClanarine(null);

						oglasivac.setTipClanstva(besplatniTip);
						DAOProvider.getDAO().dodajOglasivaca(oglasivac);
					}
				}
			}
			System.out.println("[AzuriranjeRacuna] zavrseno");
		}

	}

	@Override
	public void contextDestroyed(final ServletContextEvent arg0) {
		timer.cancel();

	}

	@Override
	public void contextInitialized(final ServletContextEvent arg0) {
		System.out.println("[AzuriranjeRacuna] init");

		// jednom dnevno
		long executionPeriod = 1000 * 60 * 60 * 24;

		timer = new Timer();
		Calendar date = Calendar.getInstance();

		// Schedule to run every day
		timer.scheduleAtFixedRate(
			new AzurirajKorisnikeTast(),
			date.getTime(),
			executionPeriod
			);
	}
}