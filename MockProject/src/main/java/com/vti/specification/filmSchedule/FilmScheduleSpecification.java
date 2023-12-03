package com.vti.specification.filmSchedule;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vti.entity.FilmSchedule;
import com.vti.form.filmSchedule.FilmScheduleFilterForm;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class FilmScheduleSpecification {
	
	public static Specification<FilmSchedule> buildWhere (FilmScheduleFilterForm filter){
		
		Specification<FilmSchedule> where = null;
		
		CustomSpecification init = new CustomSpecification("init", "init");
		where = Specification.where(init);
		
		// Filter
		if(filter == null) {
			return where;
		}
		
		if (filter.getMinTimeSlot() != null) {
			CustomSpecification minTimeSlot = new CustomSpecification("minTimeSlot", filter.getMinTimeSlot());
			where = where.and(minTimeSlot);
		}		
				
		// max created date
		if (filter.getMaxTimeSlot() != null) {
			CustomSpecification maxTimeSlot = new CustomSpecification("maxTimeSlot", filter.getMaxTimeSlot());
			where = where.and(maxTimeSlot);
		}
		return where;
	}
}

@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecification implements Specification<FilmSchedule>{

	@NonNull
	private String field;
	@NonNull
	private Object value;
	
	@Override
	public Predicate toPredicate(
			Root<FilmSchedule> root, 
			CriteriaQuery<?> query, 
			CriteriaBuilder criteriaBuilder) {
		if (field.equalsIgnoreCase("init")) {
			return criteriaBuilder.equal(criteriaBuilder.literal(1), 1);
		}
		
		if (field.equalsIgnoreCase("minTimeSlot")) {
			return criteriaBuilder.greaterThanOrEqualTo(
					root.get("timeSlot"), (Date) value);
		}
		
		if (field.equalsIgnoreCase("maxTimeSlot")) {
			return criteriaBuilder.lessThanOrEqualTo(
					root.get("timeSlot"), (Date) value);
		}
		
		return null;
	}
	
}
