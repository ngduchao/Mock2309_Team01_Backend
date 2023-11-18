package com.vti.specification.film;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.vti.entity.Film;
import com.vti.filter.FilmFilterForm;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class FilmSpecification {

	@SuppressWarnings("deprecation")
	public static Specification<Film> buildWhere(String search, FilmFilterForm filter) {
		
		//khởi tạo where
		Specification<Film> where = null;
		
		CustomSpecification init = new CustomSpecification("init", "init");
		where = Specification.where(init);
		
		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
			CustomSpecification filmName = new CustomSpecification("filmName", search);
			where = where.and(filmName);
		}
		
		//filter
		if(filter == null || filter.getGenre() == "") {
			return where;
		}
		
		//filter genre
		if(filter != null && filter.getGenre() != null) {
			CustomSpecification genre = new CustomSpecification("genre", filter.getGenre());
			where = where.and(genre);
		}
		
		return where;
	}
}

@RequiredArgsConstructor
class CustomSpecification implements Specification<Film>{
	
	private static final long serialVersionUID = 1L;
	@NonNull
	private String field;
	@NonNull
	private Object value;
	@Override
	public Predicate toPredicate(
			Root<Film> root, 
			CriteriaQuery<?> query, 
			CriteriaBuilder criteriaBuilder) {
		
		if(field.equalsIgnoreCase("init")) {
			return criteriaBuilder.equal(criteriaBuilder.literal(1), 1);
		}
		
		if(field.equalsIgnoreCase("filmName")) {
			return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
		}
		
		if(field.equalsIgnoreCase("genre")) {
			return criteriaBuilder.like(root.get("genre"), "%" + value.toString() + "%");
		}
		
		return null;
	}
	
	
}
