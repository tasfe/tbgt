package tbgt.persistence;

import tbgt.domain.ExpressCode;

import java.util.List;

public interface ExpressCodeMapper {
    public List<ExpressCode> getExpressCode(String province);
}
