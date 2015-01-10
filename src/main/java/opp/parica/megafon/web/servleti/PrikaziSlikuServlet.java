package opp.parica.megafon.web.servleti;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.Slika;

@WebServlet({ "/servleti/prikaziSliku" })
public class PrikaziSlikuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Slika s;
		try {
			Long slikaID = Long.valueOf(Long.parseLong(req.getParameter("id")));
			s = DAOProvider.getDAO().dohvatiSliku(slikaID);
			if (s == null) {
				throw new IllegalArgumentException();
			}
		} catch (Exception e) {
			req.setAttribute("msg", "Neispravan id slike");
			req.setAttribute("title", "Greška");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}
		BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(s.getSlika()));

		if ((req.getParameter("x") == null) || (req.getParameter("y") == null)) {
			ImageIO.write(bufferedImage, s.getEkstenzija(), resp.getOutputStream());
		}
		int x, y;
		try {
			x = Integer.parseInt(req.getParameter("x"));
			y = Integer.parseInt(req.getParameter("y"));
		} catch (Exception e) {
			req.setAttribute("msg", "Neispravna velcina slike");
			req.setAttribute("title", "Greška");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}

		ImageIO.write(resize(bufferedImage, x, y), s.getEkstenzija(), resp.getOutputStream());
	}

	public static BufferedImage resize(final BufferedImage img, final int newW, final int newH) {
		Image tmp = img.getScaledInstance(newW, newH, 4);
		BufferedImage dimg = new BufferedImage(newW, newH, 5);

		Graphics2D g2d = dimg.createGraphics();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, newW, newH);
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}
}