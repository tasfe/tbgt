package tbgt.persistence;

import tbgt.domain.Baobei;

import java.util.List;
import java.util.Map;

public interface BaobeiMapper {

    public void insertBaobei(Baobei baobei);

    public void updateBaobei(Baobei baobei);

    public void deleteBaobei(int id);

    public List<Baobei> getAllBaobei();

    public List<Baobei> getBaobeiWithPaging(Map paramMap);

    public Baobei getBaobeiById(int id);
}
