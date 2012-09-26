package tbgt.persistence;

import tbgt.domain.Baobei;

import java.util.List;

public interface BaobeiMapper {

    public void insertBaobei(Baobei baobei);

    public void updateBaobei(Baobei baobei);

    public void deleteBaobei(int id);

    public List<Baobei> getAllBaobei();

    public Baobei getBaobeiById(int id);
}
