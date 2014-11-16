package opp.parica.megafon.web.servleti;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.web.servlets.forme.KategorijaForma;

@WebServlet("/servleti/stvoriKategoriju")
public class StvoriKategorijuServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		if (req.getSession().getAttribute("admin") == null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			return;
		}
		KategorijaForma form = new KategorijaForma();

		req.setAttribute("zapis", form);
		req.setAttribute("kategorije", DAOProvider.getDAO().dohvatiSveKategorije());
		req.setAttribute("tipovi", Arrays.asList(new String[]{"Tekst", "Numerička vrijednost"}));
		req.getRequestDispatcher("/WEB-INF/pages/StvoriKategoriju.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
		IOException {
		req.setCharacterEncoding("UTF-8");
		String metoda = req.getParameter("metoda");
		if (!"Promjeni tip".equals(metoda)) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			return;
		}
	}
}
