package ra.edu.utils.pagination;

import java.util.Scanner;
import java.util.function.Consumer;

public class PaginationConfig<T> {
    private int totalItems;
    private int pageSize;
    public Scanner sc;
    public Runnable printHeader;
    public Consumer<T> printItem;

    public PaginationConfig(int totalItems, int pageSize, Scanner sc, Runnable printHeader, Consumer<T> printItem) {
        this.totalItems = totalItems;
        this.pageSize = pageSize;
        this.sc = sc;
        this.printHeader = printHeader;
        this.printItem = printItem;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    public Runnable getPrintHeader() {
        return printHeader;
    }

    public void setPrintHeader(Runnable printHeader) {
        this.printHeader = printHeader;
    }

    public Consumer<T> getPrintItem() {
        return printItem;
    }

    public void setPrintItem(Consumer<T> printItem) {
        this.printItem = printItem;
    }
}