package opp.parica.megafon.web.servleti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.Kategorija;
import opp.parica.megafon.model.Oglas;
import opp.parica.megafon.model.TipClanstva;
import opp.parica.megafon.pomocno.Potpora;
import opp.parica.megafon.web.servleti.forme.PretragaForma;

@WebServlet("/servleti/pretraga")
public class PretraziOglaseServlet extends HttpServlet {
	/** Defaultni serial version UID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {

		List<Kategorija> kategorije = DAOProvider.getDAO().dohvatiSveKategorije();
		req.setAttribute("kategorije", kategorije);
		req.setAttribute("zapis", new PretragaForma());
		req.getRequestDispatcher("/WEB-INF/pages/Pretraga.jsp").forward(req, resp);
	}

	@Override
	protected final void doPost(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		PretragaForma forma = new PretragaForma();
		forma.fillFromHttpRequest(req);
		forma.validate();

		if (forma.hasError()) {

			req.setAttribute("kategorije", DAOProvider.getDAO().dohvatiSveKategorije());
			req.setAttribute("zapis", forma);
			req.getRequestDispatcher("/WEB-INF/pages/Pretraga.jsp").forward(req, resp);
			return;
		}
		List<Oglas> oglasi = forma.obaviUpit();
		if (oglasi == null || oglasi.isEmpty()) {
			req.setAttribute("title", "Nema rezultata");
			req.setAttribute(
				"msg",
				"Nepostoji niti jedan oglas koji zadovoljava kriterije pretrage");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}
		List<Oglas> premium = new ArrayList<Oglas>();
		List<Oglas> regular = new ArrayList<Oglas>();

		TipClanstva besplatni = DAOProvider.getDAO().dohvatiTipClanstva("Besplatni");
		for (Oglas o : oglasi) {
			if (o.getJeSkriven()) {
				continue;
			}
			if (!o.getAutor().getTipClanstva().equals(besplatni)) {
				premium.add(o);
			} else {
				regular.add(o);
			}
		}

		if (!premium.isEmpty()) {
			Collections.sort(premium, Potpora.OGLASI_KOMPARATOR);
			req.setAttribute("premium", oglasi);
		}
		if (!regular.isEmpty()) {
			Collections.sort(regular, Potpora.OGLASI_KOMPARATOR);
			req.setAttribute("retultati", oglasi);
		}
		req.setAttribute("kriteriji", forma.getKriterijiString());
		req.getRequestDispatcher("/WEB-INF/pages/PrikazRezultata.jsp").forward(req, resp);
	}

}
