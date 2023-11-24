package com.vti.specification.ticket;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vti.entity.Ticket;
import com.vti.form.ticket.TicketFilterForm;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class TicketSpecification {
	
	public static Specification<Ticket> buildWhere (TicketFilterForm filter){
		
		Specification<Ticket> where = null;
		
		CustomSpecification init = new CustomSpecification("init", "init");
		where = Specification.where(init);
		
		// Filter
		if(filter == null) {
			return where;
		}
		
		if(filter.getMinBookingDate() != null) {
			CustomSpecification minBookingDate = new CustomSpecification("minBookingDate", filter.getMinBookingDate());
			where = where.and(minBookingDate);
		}
		
		if(filter.getMaxBookingDate() != null) {
			CustomSpecification maxBookingDate = new CustomSpecification("maxBookingDate", filter.getMaxBookingDate());
			where = where.and(maxBookingDate);
		}
		
		return where;
		
		
	}

}

@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecification implements Specification<Ticket>{
	
	@NonNull
	private String field;
	@NonNull
	private Object value;
	@Override
	public Predicate toPredicate(Root<Ticket> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
		if (field.equalsIgnoreCase("init")) {
			return criteriaBuilder.equal(criteriaBuilder.literal(1), 1);
		}
		
		if (field.equalsIgnoreCase("minBookingDate")) {
			return criteriaBuilder.greaterThanOrEqualTo(
					root.get("bookingDate"), (Date) value);
		}
		
		if (field.equalsIgnoreCase("maxBookingDate")) {
			return criteriaBuilder.lessThanOrEqualTo(
					root.get("bookingDate"), (Date) value);
		}
		
		return null;
	}
	
	
}
