package tbgt.web.paging;

public class PagingContextHolder {

    private static ThreadLocal<PaginationTO> pageDisplayTL = new ThreadLocal<PaginationTO>();

    public static PaginationTO get() {
        return pageDisplayTL.get();
    }

    public static void set(PaginationTO paginationTO) {
        pageDisplayTL.set(paginationTO);
    }

    public static void clear() {
        pageDisplayTL.remove();
    }
}
