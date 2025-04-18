package ra.edu.utils.pagination;

import java.util.List;

public interface ListProvider<T> {
    List<T> getDataByPage(int page, int pageSize);
}
