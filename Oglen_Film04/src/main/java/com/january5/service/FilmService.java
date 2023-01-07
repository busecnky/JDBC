package com.january5.service;

import java.util.List;

import com.january5.entity.Film;
import com.january5.repository.FilmDao;

public class FilmService implements ICrud<Film> {

	private FilmDao filmDao;

	public FilmService() {
		this.filmDao = new FilmDao();
	}

	@Override
	public Film create(Film entity) {
		filmDao.create(entity);
		return entity;
	}

	@Override
	public void delete(long id) {
		filmDao.delete(id);
	}

	@Override
	public void update(long id, Film entity) {
		filmDao.update(id, entity);
	}

	@Override
	public List<Film> listAll() {
		return filmDao.listAll();
	}

	@Override
	public Film find(long id) {
		Film film = filmDao.find(id);
		return film;
	}

}
