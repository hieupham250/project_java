package ra.edu.utils.pagination;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class PaginationConfig<T> {
    public List<T> items;
    public int page;
    public int pageSize;
    public Scanner sc;
    public Runnable printHeader;
    public Consumer<T> printItem;

    public PaginationConfig(List<T> items, int page, int pageSize, Scanner sc,
                            Runnable printHeader, Consumer<T> printItem) {
        this.items = items;
        this.page = page;
        this.pageSize = pageSize;
        this.sc = sc;
        this.printHeader = printHeader;
        this.printItem = printItem;
    }
}
