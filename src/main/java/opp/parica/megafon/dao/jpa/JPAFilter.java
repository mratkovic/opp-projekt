package opp.parica.megafon.dao.jpa;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Razred koji nasljedulje sucelje {@link Filter}.
 *
 * @author Marko Ratkovic
 * @version 1.0
 */
@WebFilter("/servleti/*")
public class JPAFilter implements Filter {

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public final void doFilter(final ServletRequest request, final ServletResponse response,
		final FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} finally {
			JPAEMProvider.close();
		}
	}

	@Override
	public void destroy() {
	}

}
