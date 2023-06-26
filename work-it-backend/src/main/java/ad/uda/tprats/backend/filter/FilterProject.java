package ad.uda.tprats.backend.filter;


import ad.uda.tprats.workitdata.entities.Project;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


public class FilterProject implements Specification<Project> {

    /**
     *
     */
    private static final long serialVersionUID = -683701253620093979L;

    private Long userId = 0L;
    private String title;
    //private boolean nomExacte = false;

    public FilterProject(DataTablesInput input) {

        if (StringUtils.isNotBlank(input.getColumn("title").getSearch().getValue())) {
            title = input.getColumn("title").getSearch().getValue();
           /* if (!input.getColumn("title").getSearch().getRegex()) {
                nomExacte = true;
            }*/
        }


        return;
    }


    @Override
    public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (StringUtils.isNotBlank(title)) {
            Expression<String> nomEx = root.get("title").as(String.class);
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(nomEx), "%" + title.toLowerCase() + "%"));
            /*if (nomExacte) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(nomEx), description.toLowerCase()));
            } else {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(nomEx), "%" + description.toLowerCase() + "%"));
            }*/
        }

        if (predicates.isEmpty()) {
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
