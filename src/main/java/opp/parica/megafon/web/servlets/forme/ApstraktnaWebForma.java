/**
 *
 */
package opp.parica.megafon.web.servlets.forme;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marko Ratkovic
 * @version 1.0
 */
public abstract class ApstraktnaWebForma {

	/** Mapa gresaka prilikom unosa parametara. */
	private Map<String, String> errors;

	/**
	 * Konstruktor.
	 */
	public ApstraktnaWebForma() {
		errors = new HashMap<>();
	}

	/**
	 * Metoda koja dohvaca gresku za atribut zadan argumentom ime.
	 *
	 * @param attribute
	 *            ime argumenta cija gireska se dohvaca
	 * @return tekst greske
	 */
	public final String getError(final String attribute) {
		return errors.get(attribute);
	}

	/**
	 * Metoda koja provjerava dali postoji greska kojeg od atributa.
	 *
	 * @return true ukoliko ima gresaka.
	 */
	public final boolean hasError() {
		return !errors.isEmpty();
	}

	/**
	 * Metoda koja provjerava dali atribut zadan argumentom 'attribute' ima
	 * gresku.
	 *
	 * @param attribute
	 *            naziv atributa koji se provjerava
	 * @return true ukoliko postoji greska
	 */
	public final boolean hasError(final String attribute) {
		return errors.containsKey(attribute);
	}

	/**
	 * Metoda koja popunjava instancu {@link PrijavaKorisnikaForma} podacima iz
	 * {@link HttpServletRequest}-a.
	 *
	 * @param req
	 *            {@link HttpServletRequest}
	 */
	public abstract void fillFromHttpRequest(final HttpServletRequest req);

	/**
	 * Metoda koja puni objekt podacima iz pripadne web forme.
	 *
	 * @param obj
	 *            objekt koji se puni odgovarajucim podacima
	 */
	public abstract void fillToObject(final Object obj);

	/**
	 * Metoda koja puni web formu podacima iz pripadnog objekta.
	 *
	 * @param obj
	 *            objekt koji sadrzi informacije koji se upisuju u formu
	 */
	public abstract void fillFromObject(Object obj);

	/**
	 * Metoda koja provjerava ispavnost podataka objekta, ta puni mapu gresaka s
	 * pripadnim porukama.
	 */
	public abstract void validate();

	/**
	 * Metoda koja dohvaca mapu gresaka nastalih provjerom ispravnosti
	 * parametara.
	 *
	 * @return the errors mapa gresaka
	 */
	public final Map<String, String> getErrors() {
		return errors;
	}

	/**
	 * Metoda koja uklanja praznine s pocetka ili kraja. Ukoliko je string null,
	 * vraca prazan niz.
	 *
	 * @param param
	 *            string
	 * @return string bez praznina s pocetka ili kraja
	 */
	public static  final String trimParameter(final String param) {
		if (param != null) {
			return param.trim();
		} else {
			return "";
		}
	}
}
