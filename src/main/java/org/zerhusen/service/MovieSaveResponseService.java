package org.zerhusen.service;

public class MovieSaveResponseService {

	
	private final String movie_id;

	public MovieSaveResponseService(String movie_id) {
		super();
		this.movie_id = movie_id;
	}

	public String getMovie_id() {
		return movie_id;
	}
	
}
