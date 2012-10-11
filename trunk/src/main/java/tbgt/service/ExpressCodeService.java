package tbgt.service;

import tbgt.domain.ExpressCode;
import tbgt.web.criteria.ExpressCodeCriteria;

import java.math.BigDecimal;
import java.util.List;

public interface ExpressCodeService {
    public List<ExpressCode> getExpressCodeList(ExpressCodeCriteria expressCodeCriteria);
    public ExpressCode getExpressCode(String province,String type);
    public int getExpressFee(ExpressCode expressCode, BigDecimal weight);
}
