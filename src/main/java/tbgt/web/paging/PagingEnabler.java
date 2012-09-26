package tbgt.web.paging;

import java.util.List;

public class PagingEnabler {

    public static PaginationTO enablePaging(List<?> result) {
        PaginationTO paginationTO = PagingContextHolder.get();
        if (paginationTO == null) {
            paginationTO = new PaginationTO();
        }
        int total = result.size();
        paginationTO.setiTotalRecords(total);
        paginationTO.setiTotalDisplayRecords(total);
        int start = paginationTO.getiDisplayStart();
        int pageSize = paginationTO.getiDisplayLength();
        if (pageSize != -1) {
            if ((start + 1 + pageSize) > total) {
                paginationTO.setAaData(result.subList(start, total));
            } else {
                paginationTO.setAaData(result.subList(start, start + pageSize));
            }
        } else {
            paginationTO.setAaData(result);
        }


        return paginationTO;
    }
}
