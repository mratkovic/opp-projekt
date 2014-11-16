package opp.parica.megafon.web.servleti;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.Oglasivac;
import opp.parica.megafon.model.TipClanstva;

@WebServlet("/servleti/bankaUplata")
public class BankaServlet extends HttpServlet {
	public static final double POSTOTAK_GRESKE = 0.2f;
	/** *Defaultni serial version UID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doPost(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		String metoda = req.getParameter("metoda");

		Random r = new Random();
		float rnd = r.nextFloat();
		System.out.println("rnd" + rnd);
		if ("Odustani".equals(metoda) || rnd < POSTOTAK_GRESKE) {

			req.setAttribute("msg",
				"Uplata nije uspjesno realizirana. Molimo pokušajte ponovno te ukoliko se "
				+ "problem ponovi kontaktirate administratora");
			UrediPodatkeServlet.clanstvoStatus(req);
			req.getRequestDispatcher("/WEB-INF/pages/ClanstvoStatus.jsp").forward(req, resp);
			return;

		}

		long id = Long.parseLong(req.getParameter("id_oglasivac"));
		long idTip = Long.parseLong(req.getParameter("id_novi_tip"));
		Oglasivac oglasivac = DAOProvider.getDAO().dohvatiOglasivaca(id);
		TipClanstva tip = DAOProvider.getDAO().dohvatiTipClanstva(idTip);

		Calendar cal = Calendar.getInstance();
		Date istekClanstva;
		if (oglasivac.getTipClanstva().equals(tip)) {
			istekClanstva = oglasivac.getDatumIstekaClanarine();

		} else {
			istekClanstva = new Date();
		}

		cal.setTime(istekClanstva);
		cal.add(Calendar.MONTH, 1);
		istekClanstva = cal.getTime();

		oglasivac.setDatumIstekaClanarine(istekClanstva);
		oglasivac.setTipClanstva(tip);

		DAOProvider.getDAO().dodajOglasivaca(oglasivac);
		req.getSession().invalidate();
		PrijavaServlet.loginMethod(req, oglasivac);

		UrediPodatkeServlet.clanstvoStatus(req);
		req.setAttribute("msg",
			"Promjena tipa članstva uspjesno realizirana.");
		req.getRequestDispatcher("/WEB-INF/pages/ClanstvoStatus.jsp").forward(req, resp);


	}
}
