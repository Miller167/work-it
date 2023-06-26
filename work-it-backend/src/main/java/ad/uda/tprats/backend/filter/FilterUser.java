package ad.uda.tprats.backend.filter;


import ad.uda.tprats.workitdata.entities.User;
import org.apache.commons.lang3.StringUtils;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


public class FilterUser implements Specification<User> {

    /**
     *
     */
    private static final long serialVersionUID = -683701253620093979L;

    private Long userId = 0L;
    private String fullName;

    public FilterUser(DataTablesInput input) {

        if (StringUtils.isNotBlank(input.getColumn("fullName").getSearch().getValue())) {
            fullName = input.getColumn("fullName").getSearch().getValue();
            /*if (!input.getColumn("fullName").getSearch().getRegex()) {

            }*/
        }


        return;
    }


    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (StringUtils.isNotBlank(fullName)) {
            Expression<String> nomEx = root.get("fullName").as(String.class);
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(nomEx), "%" + fullName.toLowerCase() + "%"));
            /*if (nomExacte) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(nomEx), fullName.toLowerCase()));
            } else {

            }*/
        }

        if (predicates.isEmpty()) {
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
