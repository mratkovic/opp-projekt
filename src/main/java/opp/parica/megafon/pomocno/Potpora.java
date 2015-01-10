package opp.parica.megafon.pomocno;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import opp.parica.megafon.model.Oglas;

public class Potpora {
	private Potpora() {

	}

	public static String formatirajDatum(final Date datum) {
	
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm'h', dd.MM.yyyy");;
		return sdf.format(datum);
	}

	public static Comparator<Oglas> OGLASI_KOMPARATOR = new Comparator<Oglas>() {

		@Override
		public int compare(final Oglas o1, final Oglas o2) {
			int diff = o1.getDatumObjave().compareTo(o2.getDatumObjave());
			if (diff != 0) {
				return diff;
			}
			diff = o1.getAutor().getTipClanstva().getClanarina()
				.compareTo(o2.getAutor().getTipClanstva().getClanarina());
			return diff;
		}
	};
}
