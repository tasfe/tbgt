package tbgt.persistence;

import tbgt.domain.Price;

import java.util.List;

public interface PriceMapper {

    public void insertPrice(Price price);

    public void updatePrice(Price price);

    public void deletePrice(int id);

    public Price getPrice(int id);

}
