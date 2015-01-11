package opp.parica.megafon.web.servleti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.Kategorija;
import opp.parica.megafon.web.servleti.forme.KategorijaForma;

@WebServlet("/servleti/kategorija")
public class StvoriKategorijuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
		IOException {
		if (req.getSession().getAttribute("admin") == null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
		} else {
			req.setAttribute("kategorije", DAOProvider.getDAO().dohvatiSveKategorije());
			req.setAttribute("zapis", new KategorijaForma());
			req.getRequestDispatcher("/WEB-INF/pages/StvoriKategoriju.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
		IOException {
		req.setCharacterEncoding("UTF-8");
		String metoda = req.getParameter("metoda");
		if (!"Pohrani".equals(metoda)) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			return;
		}
		KategorijaForma forma = new KategorijaForma();
		forma.fillFromHttpRequest(req);
		forma.validate();
		System.out.println(forma);
		if (!forma.hasError()) {
			Kategorija kategorija = new Kategorija();
			forma.fillToObject(kategorija);
			DAOProvider.getDAO().dodajKategoriju(kategorija);
			String msg = "Kategorija " + kategorija.getNaziv() + " dodana u bazu podataka";

			req.setAttribute("msg", msg);
			req.setAttribute("title", "Uspješno");

			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;

		} else {
			req.setAttribute("kategorije", DAOProvider.getDAO().dohvatiSveKategorije());
			forma.initDodatneStavke();
			req.setAttribute("zapis", forma);
			req.getRequestDispatcher("/WEB-INF/pages/StvoriKategoriju.jsp").forward(req,
				resp);
		}

	}
}
