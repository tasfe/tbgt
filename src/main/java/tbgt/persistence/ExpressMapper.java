package tbgt.persistence;

import tbgt.domain.Express;

public interface ExpressMapper {

    public Express getExpressByOrderId(long orderId);

    public void insertExpress(Express express);

    public void updateExpress(Express express);

    public void deleteExpress(int id);
}
