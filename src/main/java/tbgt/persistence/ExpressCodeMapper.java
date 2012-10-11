package tbgt.persistence;

import tbgt.domain.ExpressCode;

import java.util.List;
import java.util.Map;

public interface ExpressCodeMapper {
    public List<ExpressCode> getExpressCodeList(String province);

    public ExpressCode getExpressCode(Map param);
}
