package ad.uda.tprats.backend.filter;


import ad.uda.tprats.workitdata.entities.Project;
import ad.uda.tprats.workitdata.entities.Shift;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FilterShift implements Specification<Shift> {

    /**
     *
     */
    private static final long serialVersionUID = -683701253620093979L;

    private Long userId = 0L;
    private Date startDatetime;
    //private boolean nomExacte = false;

    public FilterShift(DataTablesInput input) {

        if (StringUtils.isNotBlank(input.getColumn("startDatetime").getSearch().getValue())) {
            try {
                startDatetime = new SimpleDateFormat("dd/MM/yyyy").parse(input.getColumn("startDatetime").getSearch().getValue());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            /*if (!input.getColumn("startDatetime").getSearch().getRegex()) {
                nomExacte = true;
            }*/
        }


        return;
    }


    @Override
    public Predicate toPredicate(Root<Shift> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<Predicate>();

       /* if (StringUtils.isNotBlank(startDatetime)) {
            Expression<String> nomEx = root.get("startDatetime").as(String.class);
            if (nomExacte) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(nomEx), startDatetime.toLowerCase()));
            } else {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(nomEx), "%" + startDatetime.toLowerCase() + "%"));
            }
        }*/

        if (predicates.isEmpty()) {
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
