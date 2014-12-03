package opp.parica.megafon.dao;

import java.util.List;

import opp.parica.megafon.model.Admin;
import opp.parica.megafon.model.DodatnaStavka;
import opp.parica.megafon.model.FizickaOsoba;
import opp.parica.megafon.model.Kategorija;
import opp.parica.megafon.model.Korisnik;
import opp.parica.megafon.model.Oglas;
import opp.parica.megafon.model.Oglasivac;
import opp.parica.megafon.model.PravnaOsoba;
import opp.parica.megafon.model.Slika;
import opp.parica.megafon.model.TipClanstva;

public interface DAO{

	List<Oglasivac> dohvatiSveOglasavace();

	void dodajAdmina(Admin admin);

	void dodajOglasivaca(Oglasivac oglasivac);

	boolean postojiAdmin();

	void dodajTipClanstva(TipClanstva tip);

	void dodajKategoriju(Kategorija k);

	boolean postojiKorisnik(String username);

	PravnaOsoba dohvatiPravnaOsoba(String username, String passwordHash);

	FizickaOsoba dohvatiFizickaOsoba(String username, String passwordHash);

	Admin dohvatiAdmin(String username, String passwordHash);

	TipClanstva dohvatiTipClanstva(String naziv);

	Oglasivac dohvatiOglasivaca(long id);

	boolean izbrisiOglasivaca(long id);

	boolean izbrisiOglas(long id);

	Oglas dohvatiOglas(long id);

	Object dohvatiSveTipoveClanstva();

	List<Admin> dohvatiSveAdmine();

	List<Kategorija> dohvatiSveKategorije();

	List<Oglas> dohvatiOglaseOglasivaca(Oglasivac oglasivac);

	Korisnik dohvatiKorisnika(long id);

	TipClanstva dohvatiTipClanstva(long idTip);

	Kategorija dohvatiKategoriju(long id);

	void dodajOglas(Oglas oglas);

	void dodajStavku(DodatnaStavka stavka);

	void dodajSliku(Slika slika);

	Slika dohvatiSliku(Long slikaID);

}
