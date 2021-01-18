/*    */
package com.dragon.util.tag;
/*    */
/*    */

import com.dragon.util.common.*;

/*    */
/*    */ public class PageModel
        /*    */ {
    /*    */   private int recordCount;
    /*    */   private int pageIndex;
    /* 14 */   private int pageSize = StuConstants.PAGE_DEFAULT_SIZE = 4;
    /*    */
    /*    */   private int totalSize;

    /*    */
    /*    */
    /*    */
    public int getRecordCount() {
        /* 20 */
        this.recordCount = (this.recordCount <= 0) ? 0 : this.recordCount;
        /* 21 */
        return this.recordCount;
        /*    */
    }

    /*    */
    /* 24 */
    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    /*    */
    /*    */
    public int getPageIndex() {
        /* 27 */
        this.pageIndex = (this.pageIndex <= 0) ? 1 : this.pageIndex;
        /*    */
        /* 29 */
        this.pageIndex = (this.pageIndex >= getTotalSize()) ? getTotalSize() : this.pageIndex;
        /*    */
        /* 31 */
        return this.pageIndex;
        /*    */
    }

    /*    */
    /* 34 */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    /*    */
    /*    */
    public int getPageSize() {
        /* 37 */
        this.pageSize = (this.pageSize <= StuConstants.PAGE_DEFAULT_SIZE) ? StuConstants.PAGE_DEFAULT_SIZE : this.pageSize;
        /* 38 */
        return this.pageSize;
        /*    */
    }

    /*    */
    /* 41 */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /*    */
    /*    */
    /*    */
    public int getTotalSize() {
        /* 45 */
        if (getRecordCount() <= 0) {
            /* 46 */
            this.totalSize = 0;
            /*    */
        } else {
            /* 48 */
            this.totalSize = (getRecordCount() - 1) / getPageSize() + 1;
            /*    */
        }
        /* 50 */
        return this.totalSize;
        /*    */
    }

    /*    */
    /*    */
    /*    */
    /* 55 */
    public int getFirstLimitParam() {
        return (getPageIndex() - 1) * getPageSize();
    }
    /*    */
}

