package com.vti.specification.user;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.vti.entity.User;
import com.vti.filter.UserFilterForm;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class UserSpecification {
	
	public static Specification<User> buildWhere(String search, UserFilterForm filter){
		
		Specification<User> where = null;
		
		CustomSpecification init = new CustomSpecification("init", "init");
		where = Specification.where(init);
		
		// Search theo theo username & firstname & lastname
		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
			CustomSpecification username = new CustomSpecification("username", search);
			CustomSpecification firstName = new CustomSpecification("firstName", search);
			CustomSpecification lastName = new CustomSpecification("lastName", search);
			where = where.and((username).or(firstName).or(lastName));
		}
		
		//filter theo role
		if(filter == null) {
			return where;
		}
		
		if(filter.getRole() != null) {
			CustomSpecification role = new CustomSpecification("role", filter.getRole());
			where = where.and(role);
		}
		
		return where;
	}
}

@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecification implements Specification<User>{

	@NonNull
	private String field;
	@NonNull
	private Object value;
	
	@Override
	public Predicate toPredicate(
			Root<User> root, 
			CriteriaQuery<?> query, 
			CriteriaBuilder criteriaBuilder) {

		if (field.equalsIgnoreCase("init")) {
			return criteriaBuilder.equal(criteriaBuilder.literal(1), 1);
		}
		
		if (field.equalsIgnoreCase("username")) {
			return criteriaBuilder.like(root.get("username"), "%" + value.toString() + "%");
		}

		if (field.equalsIgnoreCase("firstName")) {
			return criteriaBuilder.like(root.get("firstName"), "%" + value.toString() + "%");
		}

		if (field.equalsIgnoreCase("lastName")) {
			return criteriaBuilder.like(root.get("lastName"), "%" + value.toString() + "%");
		}
		
		if (field.equalsIgnoreCase("role")) {
			return criteriaBuilder.equal(root.get("role"), value);
		}
		
		return null;
	}
	
}