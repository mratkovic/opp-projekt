package opp.parica.megafon.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import opp.parica.megafon.dao.DAO;
import opp.parica.megafon.model.Admin;
import opp.parica.megafon.model.FizickaOsoba;
import opp.parica.megafon.model.Kategorija;
import opp.parica.megafon.model.Oglas;
import opp.parica.megafon.model.Oglasivac;
import opp.parica.megafon.model.PravnaOsoba;
import opp.parica.megafon.model.TipClanstva;

/**
 * Ovo je implementacija podsustava DAO uporabom tehnologije JPA. Ova
 * konkretna implementacija očekuje da joj veza stoji na raspolaganju
 * preko {@link JPAEMProvider} razreda.
 *
 * @author Marko Ratkovic
 * @version 1.0
 */
public class JPADAOImpl implements DAO {

	@SuppressWarnings("unchecked")
	@Override
	public final List<Oglasivac> dohvatiSveOglasavace() {
		EntityManager em = JPAEMProvider.getEntityManager();
		List<Oglasivac> users = em.createQuery("from Oglasivac").getResultList();
		return users;
	}

	@Override
	public void dodajAdmina(final Admin admin) {
		EntityManager em = JPAEMProvider.getEntityManager();
		if (admin.getId() == null) {
			em.persist(admin);
		} else {
			em.merge(admin);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public FizickaOsoba dohvatiFizickaOsoba(final String username, final String passwordHash) {
		EntityManager em = JPAEMProvider.getEntityManager();
		List<FizickaOsoba> users = em.createQuery("select usr from FizickaOsoba as usr "
			+ "where usr.username=:username and usr.passwordHash=:passwordHash")
			.setParameter("username", username)
			.setParameter("passwordHash", passwordHash)
			.getResultList();
		if (users == null || users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}

	}

	@Override
	public boolean postojiAdmin() {
		EntityManager em = JPAEMProvider.getEntityManager();

		@SuppressWarnings("unchecked")
		List<Oglasivac> users = em.createQuery("from Admin").getResultList();
		return users != null && !users.isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final Admin dohvatiAdmin(final String username, final String passwordHash) {
		EntityManager em = JPAEMProvider.getEntityManager();
		List<Admin> users =
			em.createQuery("select usr from Admin as usr "
				+ "where usr.username=:username and usr.passwordHash=:passwordHash")
				.setParameter("username", username)
				.setParameter("passwordHash", passwordHash)
				.getResultList();
		if (users == null || users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public PravnaOsoba dohvatiPravnaOsoba(final String username, final String passwordHash) {
		EntityManager em = JPAEMProvider.getEntityManager();
		List<PravnaOsoba> users =
			em.createQuery("select usr from PravnaOsoba as usr "
				+ "where usr.username=:username and usr.passwordHash=:passwordHash")
				.setParameter("username", username)
				.setParameter("passwordHash", passwordHash)
				.getResultList();

		if (users == null || users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public void dodajTipClanstva(final TipClanstva tip) {
		EntityManager em = JPAEMProvider.getEntityManager();
		if (tip.getId() == null) {
			em.persist(tip);
		} else {
			em.merge(tip);
		}

	}

	@Override
	public void dodajKategoriju(final Kategorija k) {
		EntityManager em = JPAEMProvider.getEntityManager();
		if (k.getId() == null) {
			em.persist(k);
		} else {
			em.merge(k);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public TipClanstva dohvatiTipClanstva(final String naziv) {
		EntityManager em = JPAEMProvider.getEntityManager();
		List<TipClanstva> t =
			em.createQuery("select tip from TipClanstva as tip "
				+ "where tip.naziv=:naziv")
				.setParameter("naziv", naziv)
				.getResultList();
		if (t == null || t.isEmpty()) {
			return null;
		} else {
			return t.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean postojiKorisnik(final String username) {
		EntityManager em = JPAEMProvider.getEntityManager();
		List<Oglasivac> oglasivaci =
			em.createQuery("select b from Oglasivac as b where b.username=:username")
				.setParameter("username", username)
				.getResultList();

		List<Oglasivac> admini =
			em.createQuery("select b from Admin as b where b.username=:username")
				.setParameter("username", username)
				.getResultList();

		if ((admini == null || admini.isEmpty()) && (oglasivaci == null || oglasivaci.isEmpty())) {
			return false;
		} else {
			System.out.println("Korisnicko ime zauzeto");
			return true;
		}
	}

	@Override
	public void dodajOglasivaca(final Oglasivac oglasivac) {
		EntityManager em = JPAEMProvider.getEntityManager();
		if (oglasivac.getId() == null) {
			em.persist(oglasivac);
		} else {
			em.merge(oglasivac);
		}

	}

	@Override
	public Oglasivac dohvatiOglasivaca(final long id) {
		PravnaOsoba po = JPAEMProvider.getEntityManager().find(PravnaOsoba.class, id);
		if (po != null) {
			return po;
		}
		FizickaOsoba fo = JPAEMProvider.getEntityManager().find(FizickaOsoba.class, id);
		if (fo != null) {
			return fo;
		}
		return null;
	}

	@Override
	public boolean izbrisiOglasivaca(final long id) {
		EntityManager em = JPAEMProvider.getEntityManager();
		Oglasivac o = dohvatiOglasivaca(id);
		if (o != null) {
			em.remove(o);
			return true;
		}
		return false;
	}

	@Override
	public boolean izbrisiOglas(final long id) {
		EntityManager em = JPAEMProvider.getEntityManager();
		Oglas oglas = JPAEMProvider.getEntityManager().find(Oglas.class, id);
		if (oglas != null) {
			em.remove(oglas);
			return true;
		}
		return false;
	}

	@Override
	public Oglas dohvatiOglas(final long id) {
		Oglas oglas = JPAEMProvider.getEntityManager().find(Oglas.class, id);
		return oglas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object dohvatiSveTipoveClanstva() {
		EntityManager em = JPAEMProvider.getEntityManager();
		List<TipClanstva> tipoviClanstva = em.createQuery("from TipClanstva").getResultList();
		return tipoviClanstva;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> dohvatiSveAdmine() {
		EntityManager em = JPAEMProvider.getEntityManager();
		List<Admin> users = em.createQuery("from Admin").getResultList();
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kategorija> dohvatiSveKategorije() {
		EntityManager em = JPAEMProvider.getEntityManager();
		List<Kategorija> kategorije = em.createQuery("from Kategorija").getResultList();
		return kategorije;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Oglas> dohvatiOglaseOglasivaca(final Oglasivac oglasivac) {
		EntityManager em = JPAEMProvider.getEntityManager();
		List<Oglas> oglasi =
			em.createQuery("select b from Oglas as b where b.autor=:autor")
				.setParameter("autor", oglasivac)
				.getResultList();
		return oglasi;

	}
}
