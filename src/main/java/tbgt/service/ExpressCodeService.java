package tbgt.service;

import tbgt.domain.ExpressCode;
import tbgt.web.criteria.ExpressCodeCriteria;

import java.util.List;

public interface ExpressCodeService {
    public List<ExpressCode> getExpressCode(ExpressCodeCriteria expressCodeCriteria);
}
