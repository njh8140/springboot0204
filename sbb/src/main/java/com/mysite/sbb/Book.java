package com.mysite.sbb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Book {
	private final String title;
	private final String author;
	
	public static void main(String[] args) {
		Book book = new Book("점프", "스프링부트");
		
		System.out.println(book.getTitle());
		System.out.println(book.getAuthor());
		
	}
}
