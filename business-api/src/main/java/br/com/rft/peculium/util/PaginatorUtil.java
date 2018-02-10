package br.com.rft.peculium.util;

import java.util.Optional;

import br.com.rft.peculium.web.exceptions.NotImplementationConstructionException;

public class PaginatorUtil {
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 10;

	private PaginatorUtil() {
		throw new NotImplementationConstructionException("Classe - [PaginatorUtil] - não pode ser instanciado");
	}

	public static int getEvalPageSize(Optional<Integer> pageSize) {
		// Evaluate page size. If requested parameter is null, return initial
		// return page size
		return pageSize.orElse(INITIAL_PAGE_SIZE);
	}

	public static int getEvalPage(Optional<Integer> page) {
		// Evaluate page. If requested parameter is null or less than 0 (to
		// prevent exception), return initial size. Otherwise, return value of
		// param. decreased by 1

		int evalPage = 0;
		if (page.isPresent()) {
			evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		}

		return evalPage;
	}
}
