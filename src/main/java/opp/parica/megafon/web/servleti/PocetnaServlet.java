package opp.parica.megafon.web.servleti;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.Oglas;
import opp.parica.megafon.model.TipClanstva;
import opp.parica.megafon.pomocno.Potpora;
import opp.parica.megafon.web.servleti.forme.OglasKratkaForma;

/**
 * Klasa koja nasljeduje {@link HttpServlet} i predstavlja pocetnu stranicu
 * portala.
 *
 * @author Marko Ratkovic
 * @version 1.0
 */
@WebServlet("/servleti/pocetna")
public class PocetnaServlet extends HttpServlet {
	/** Defaultni serial version UID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {

		TipClanstva tip = DAOProvider.getDAO().dohvatiTipClanstva("Premium");
		List<Oglas> premium = DAOProvider.getDAO().dohvatiSveOglase(tip);
		if (premium != null && !premium.isEmpty()) {
			Collections.sort(premium, Potpora.OGLASI_KOMPARATOR);
			req.setAttribute("premium", OglasKratkaForma.prilagodiZaPrikaz(premium, 25, 45));
		}
		req.getRequestDispatcher("/WEB-INF/pages/Index.jsp").forward(req,
			resp);
	}

}
