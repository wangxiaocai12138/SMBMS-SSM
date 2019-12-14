package util;

public class PageUtil {
    private Integer totalCount;//总记录数
    private Integer totalPageCount;//总页数
    private Integer currentPageNo;//当前页码
    private Integer page;//页容量

    public Integer getPage() {
        this.page=5;
        return page;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPageCount() {
        totalPageCount=this.getTotalCount()%this.getPage()==0?
                            this.getTotalCount()/this.getPage():
                            this.getTotalCount()/this.getPage()+1;
        return totalPageCount;
    }

    public Integer getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(Integer currentPageNo) {
        this.currentPageNo = currentPageNo;
    }
}
