package opp.parica.megafon.dao;

import java.util.List;

import opp.parica.megafon.model.Admin;
import opp.parica.megafon.model.FizickaOsoba;
import opp.parica.megafon.model.Kategorija;
import opp.parica.megafon.model.Oglas;
import opp.parica.megafon.model.Oglasivac;
import opp.parica.megafon.model.PravnaOsoba;
import opp.parica.megafon.model.TipClanstva;

public interface DAO{

	List<Oglasivac> dohvatiSveOglasavace();

	void dodajAdmina(Admin admin);

	void dodajOglasivaca(Oglasivac oglasivac);

	boolean postojiAdmin();

	void dodajTipRacuna(TipClanstva tip);

	void dodajKategoriju(Kategorija k);

	boolean postojiKorisnik(String username);

	PravnaOsoba dohvatiPravnaOsoba(String username, String passwordHash);

	FizickaOsoba dohvatiFizickaOsoba(String username, String passwordHash);

	Admin dohvatiAdmin(String username, String passwordHash);

	TipClanstva dohvatiTipRacuna(String naziv);

	Oglasivac dohvatiOglasivaca(long id);

	boolean izbrisiOglasivaca(long id);

	boolean izbrisiOglas(long id);

	Oglas dohvatiOglas(long id);

	Object dohvatiSveTipoveRacuna();

	List<Admin> dohvatiSveAdmine();

	List<Kategorija> dohvatiSveKategorije();

	List<Oglas> dohvatiOglaseOglasivaca(Oglasivac oglasivac);

}
