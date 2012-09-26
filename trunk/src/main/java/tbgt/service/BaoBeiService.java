package tbgt.service;

import tbgt.domain.Baobei;
import tbgt.web.paging.PaginationTO;

import java.util.List;

public interface BaoBeiService {

    public List<Baobei> getAllBaobei();

    public PaginationTO getBaobeiWithPaging();

    public void deleteBaobei(int id);

    public Baobei getBaobeiById(int id);

    public void insertBaobei(Baobei baobei);

    public void updateBaobei(Baobei baobei);
}
