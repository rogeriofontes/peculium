package br.com.rft.peculium.helpers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PagePaginatorWrapper<T> {
	public static final int MAX_PAGE_ITEM_DISPLAY = 10;
	private Page<T> page;
	private List<PageItem> items;
	private int currentNumber;
	private String url;

	public PagePaginatorWrapper(Page<T> page, String url) {
		this.page = page;
		this.url = url;
		items = new ArrayList<>();

		currentNumber = page.getNumber() + 1; // start from 1 to match page.page

		int start;
		int size;
		int halfPagesToShow = MAX_PAGE_ITEM_DISPLAY / 2;

		if (page.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY) {
			start = 1;
			size = page.getTotalPages();
		} else {
			if (currentNumber - halfPagesToShow <= 0) {
				start = page.getTotalPages() - halfPagesToShow;
				size = page.getTotalPages();
			} else if (currentNumber + halfPagesToShow == page.getTotalPages()) {
				start = currentNumber - halfPagesToShow;
				size = page.getTotalPages();
			} else if (currentNumber + halfPagesToShow > page.getTotalPages()) {
				start = page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY + 1;
				size = page.getTotalPages();
			} else {
				start = page.getTotalPages() - halfPagesToShow;
				size = page.getTotalPages() + halfPagesToShow;
			}
		}

		for (int i = 0; i < size; i++) {
			items.add(new PageItem(start + i, (start + i) == currentNumber));
		}

	}

	public List<PageItem> getItems() {
		return items;
	}

	public int getNumber() {
		return currentNumber;
	}

	public List<T> getContent() {
		return page.getContent();
	}

	public int getSize() {
		return page.getSize();
	}

	public int getTotalPages() {
		return page.getTotalPages();
	}

	public boolean isFirstPage() {
		return page.isFirst();
	}

	public boolean isLastPage() {
		return page.isLast();
	}

	public boolean isHasPreviousPage() {
		return page.hasPrevious();
	}

	public boolean isHasNextPage() {
		return page.hasNext();
	}

	public class PageItem {
		private int number;
		private boolean current;

		public PageItem(int number, boolean current) {
			this.number = number;
			this.current = current;
		}

		public int getNumber() {
			return this.number;
		}

		public boolean isCurrent() {
			return this.current;
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
