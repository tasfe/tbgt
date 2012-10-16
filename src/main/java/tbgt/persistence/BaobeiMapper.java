package tbgt.persistence;

import tbgt.domain.Baobei;

import java.util.List;
import java.util.Map;

public interface BaobeiMapper {

    public void insertBaobei(Baobei baobei);

    public void updateBaobei(Baobei baobei);

    public void deleteBaobei(long id);

    public List<Baobei> getBaobei(Map paramMap);

    public Baobei getBaobeiById(long id);
}
