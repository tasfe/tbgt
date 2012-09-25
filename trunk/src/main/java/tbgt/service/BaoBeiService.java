package tbgt.service;

import tbgt.domain.Baobei;

import java.util.List;

public interface BaoBeiService {

    public List<Baobei> getAllBaobei();

    public void deleteBaobei(int id);

    public Baobei getBaobeiById(int id);

    public void insertBaobei(Baobei baobei);

    public void updateBaobei(Baobei baobei);
}
