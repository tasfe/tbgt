package tbgt.persistence;

import tbgt.domain.Express;

public interface ExpressMapper {

      public Express getExpressByOrderNo(String orderNo);
}
