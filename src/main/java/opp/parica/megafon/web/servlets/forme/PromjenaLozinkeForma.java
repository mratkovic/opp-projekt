package opp.parica.megafon.web.servlets.forme;

import javax.servlet.http.HttpServletRequest;

public class PromjenaLozinkeForma extends ApstraktnaWebForma {

	private String oldPass;
	private String password1;
	private String password2;

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(final String oldPass) {
		this.oldPass = oldPass;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(final String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(final String password2) {
		this.password2 = password2;
	}

	@Override
	public void fillFromHttpRequest(final HttpServletRequest req) {
		oldPass = trimParameter(req.getParameter("oldPass"));
		password1 = trimParameter(req.getParameter("password1"));
		password2 = trimParameter(req.getParameter("password2"));

	}

	@Override
	public void fillToObject(final Object obj) {
		// ne koristi se

	}

	@Override
	public void fillFromObject(final Object obj) {
		// ne kroristi se
	}

	@Override
	public void validate() {
		getErrors().clear();
		if (oldPass.isEmpty()) {
			getErrors().put("oldPass", "Polje 'Stara lozinka' je obavezno");
		}
		if (password1.isEmpty()) {
			getErrors().put("password1", "Polje 'Nova lozinka' je obavezno");
		}
		if (password2.isEmpty()) {
			getErrors().put("password2", "Polje 'Potvrda nove lozinke' je obavezno");
		}

		if (!password1.equals(password2)) {
			getErrors().put("password2", "Nove lozinke se ne poklapaju");
		}
	}

	public void obrisiPolja() {
		oldPass = "";
		password1 = "";
		password2 = "";
	}

}
