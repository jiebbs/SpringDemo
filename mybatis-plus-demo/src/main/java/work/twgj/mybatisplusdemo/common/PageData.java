package work.twgj.mybatisplusdemo.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * @author weijie.zhu
 * @date 2023/11/3 16:07
 */
@Data
public class PageData<T> {

    private long pageNo;

    private long pageSize;

    private long total;

    private long totalPage;

    private List<T> list;

    public PageData(Page<T> page){
        this.pageNo = page.getCurrent();
        this.pageSize = page.getSize();
        this.total = page.getTotal();
        this.totalPage = page.getPages();
        this.list = page.getRecords();
    }
}
