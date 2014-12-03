package opp.parica.megafon.pomocno;
import java.io.Serializable;


public class Pair<T1, T2> implements Serializable{

	private static final long serialVersionUID = 4687091501848856677L;

	public Pair(final T1 first, final T2 second) {
		super();
		this.first = first;
		this.second = second;
	}
	public T1 first;
	public T2 second;

	@Override
	public String toString() {
		return first + "," + second;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pair<?, ?>other = (Pair<?, ?>) obj;
		if (first == null) {
			if (other.first != null) {
				return false;
			}
		} else if (!first.equals(other.first)) {
			return false;
		}
		if (second == null) {
			if (other.second != null) {
				return false;
			}
		} else if (!second.equals(other.second)) {
			return false;
		}
		return true;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public T1 getFirst() {
		return first;
	}

	public T2 getSecond() {
		return second;
	}

	public void setFirst(final T1 first) {
		this.first = first;
	}

	public void setSecond(final T2 second) {
		this.second = second;
	}






}
