/*     */
package com.dragon.util.tag;
/*     */
/*     */

import java.io.IOException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/*     */
/*     */
/*     */
/*     */ public class PagerTag
		/*     */ extends SimpleTagSupport
		/*     */ {
	/*     */   private static final String TAG = "{0}";
	/*     */   private int pageIndex;
	/*     */   private int pageSize;
	/*     */   private int recordCount;
	/*     */   private String submitUrl;
	/*  24 */   private String style = "sabrosus";
	/*  27 */   private int totalPage = 0;

	/*     */
	/*     */
	/*     */
	/*     */
	@Override
	/*     */ public void doTag() {
		/*  33 */
		StringBuilder res = new StringBuilder();
		/*     */
		/*  35 */
		StringBuilder str = new StringBuilder();
		/*     */
		/*  37 */
		if (this.recordCount > 0) {
			/*     */
			/*  39 */
			this.totalPage = (this.recordCount - 1) / this.pageSize + 1;
			/*     */
			/*     */
			/*  42 */
			if (this.pageIndex == 1) {
				/*  43 */
				str.append("<span class='disabled'>上一页</span>");
				/*  46 */
				calcPage(str);
				/*  49 */
				if (this.pageIndex == this.totalPage) {
					/*  51 */
					str.append("<span class='disabled'>下一页</span>");
					/*     */
				} else {
					/*  53 */
					String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex + 1));//("{0}", String.valueOf(this.pageIndex + 1));
					/*  54 */
					str.append("<a href='" + tempUrl + "'>下一页</a>");
					/*     */
				}
				/*  56 */
			} else if (this.pageIndex == this.totalPage) {
				/*  57 */
				String tempUrl = this.submitUrl.replace("{0}", String.valueOf(this.pageIndex - 1));
				/*  58 */
				str.append("<a href='" + tempUrl + "'>上一页</a>");
				/*     */
				/*     */
				/*  61 */
				calcPage(str);
				/*     */
				/*  63 */
				str.append("<span class='disabled'>下一页</span>");
				/*     */
			} else {
				/*  65 */
				String tempUrl = this.submitUrl.replace("{0}", String.valueOf(this.pageIndex - 1));
				/*  66 */
				str.append("<a href='" + tempUrl + "'>上一页</a>");
				/*     */
				/*     */
				/*  69 */
				calcPage(str);
				/*     */
				/*  71 */
				tempUrl = this.submitUrl.replace("{0}", String.valueOf(this.pageIndex + 1));
				/*  72 */
				str.append("<a href='" + tempUrl + "'>下一页</a>");
				/*     */
			}
			/*     */
			/*     */
			/*  76 */
			res.append("<table width='100%' align='center' style='font-size:13px;' class='" + this.style + "'>");
			/*  77 */
			res.append("<tr><td style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none'>" + str.toString());
			/*  78 */
			res.append("&nbsp;跳转到&nbsp;&nbsp;<input style='text-align: center;BORDER-RIGHT: #aaaadd 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #aaaadd 1px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 2px; MARGIN: 2px; BORDER-LEFT: #aaaadd 1px solid; COLOR: #000099; PADDING-TOP: 2px; BORDER-BOTTOM: #aaaadd 1px solid; TEXT-DECORATION: none' type='text' size='2' id='pager_jump_page_size'/>");
			/*  79 */
			res.append("&nbsp;<input type='button' style='text-align: center;BORDER-RIGHT: #dedfde 1px solid; PADDING-RIGHT: 6px; BACKGROUND-POSITION: 50% bottom; BORDER-TOP: #dedfde 1px solid; PADDING-LEFT: 6px; PADDING-BOTTOM: 2px; BORDER-LEFT: #dedfde 1px solid; COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; BORDER-BOTTOM: #dedfde 1px solid; TEXT-DECORATION: none' value='确定' id='pager_jump_btn'/>");
			/*  80 */
			res.append("</td></tr>");
			/*  81 */
			res.append("<tr align='center'><td style='font-size:13px;'><tr><td style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none'>");
			/*     */
			/*  83 */
			int startNum = (this.pageIndex - 1) * this.pageSize + 1;
			/*     */
			/*  85 */
			int endNum = (this.pageIndex == this.totalPage) ? this.recordCount : (this.pageIndex * this.pageSize);
			/*     */
			/*  87 */
			res.append("总共<font color='red'>" + this.recordCount + "</font>条记录，当前显示" + startNum + "-" + endNum + "条记录。");
			/*  88 */
			res.append("</td></tr>");
			/*  89 */
			res.append("</table>");
			/*  90 */
			res.append("<script type='text/javascript'>");
			/*  91 */
			res.append("   document.getElementById('pager_jump_btn').onclick = function(){");
			/*  92 */
			res.append("      var page_size = document.getElementById('pager_jump_page_size').value;");
			/*  93 */
			res.append("      if (!/^[1-9]\\d*$/.test(page_size) || page_size < 1 || page_size > " + this.totalPage + "){");
			/*  94 */
			res.append("          alert('请输入[1-" + this.totalPage + "]之间的页码！');");
			/*  95 */
			res.append("      }else{");
			/*  96 */
			res.append("         var submit_url = '" + this.submitUrl + "';");
			/*  97 */
			res.append("         window.location = submit_url.replace('{0}', page_size);");
			/*  98 */
			res.append("      }");
			/*  99 */
			res.append("}");
			/* 100 */
			res.append("</script>");
			/*     */
		}
		/*     */
		else {
			/*     */
			/* 104 */
			res.append("<table align='center' style='font-size:13px;'><tr><td style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none'>总共<font color='red'>0</font>条记录，当前显示0-0条记录。</td></tr></table>");
			/*     */
		}
		/* 106 */
		try {
			getJspContext().getOut().print(res.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*     */
	}

	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	private void calcPage(StringBuilder str) {
		/* 113 */
		if (this.totalPage <= 11) {
			/*     */
			/* 115 */
			for (int i = 1; i <= this.totalPage; i++) {
				/* 116 */
				if (this.pageIndex == i) {
					/*     */
					/* 118 */
					str.append("<span class='current'>" + i + "</span>");
					/*     */
				} else {
					/* 120 */
					String tempUrl = this.submitUrl.replace("{0}", String.valueOf(i));
					/* 121 */
					str.append("<a href='" + tempUrl + "'>" + i + "</a>");
					/*     */
				}
				/*     */
				/*     */
			}
			/*     */
			/* 126 */
		} else if (this.pageIndex <= 8) {
			/* 127 */
			for (int i = 1; i <= 10; i++) {
				/* 128 */
				if (this.pageIndex == i) {
					/*     */
					/* 130 */
					str.append("<span class='current'>" + i + "</span>");
					/*     */
				} else {
					/* 132 */
					String tempUrl = this.submitUrl.replace("{0}", String.valueOf(i));
					/* 133 */
					str.append("<a href='" + tempUrl + "'>" + i + "</a>");
					/*     */
				}
				/*     */
			}
			/* 136 */
			str.append("...");
			/* 137 */
			String tempUrl = this.submitUrl.replace("{0}", String.valueOf(this.totalPage));
			/* 138 */
			str.append("<a href='" + tempUrl + "'>" + this.totalPage + "</a>");
			/*     */
			/*     */
		}
		/* 141 */
		else if (this.pageIndex + 8 >= this.totalPage) {
			/* 142 */
			String tempUrl = this.submitUrl.replace("{0}", String.valueOf(1));
			/* 143 */
			str.append("<a href='" + tempUrl + "'>1</a>");
			/* 144 */
			str.append("...");
			/*     */
			/* 146 */
			for (int i = this.totalPage - 10; i <= this.totalPage; i++) {
				/* 147 */
				if (this.pageIndex == i) {
					/*     */
					/* 149 */
					str.append("<span class='current'>" + i + "</span>");
					/*     */
				} else {
					/* 151 */
					tempUrl = this.submitUrl.replace("{0}", String.valueOf(i));
					/* 152 */
					str.append("<a href='" + tempUrl + "'>" + i + "</a>");
					/*     */
				}
				/*     */
				/*     */
			}
			/*     */
		} else {
			/*     */
			/* 158 */
			String tempUrl = this.submitUrl.replace("{0}", String.valueOf(1));
			/* 159 */
			str.append("<a href='" + tempUrl + "'>1</a>");
			/* 160 */
			str.append("...");
			/*     */
			/* 162 */
			for (int i = this.pageIndex - 4; i <= this.pageIndex + 4; i++) {
				/* 163 */
				if (this.pageIndex == i) {
					/*     */
					/* 165 */
					str.append("<span class='current'>" + i + "</span>");
					/*     */
				} else {
					/* 167 */
					tempUrl = this.submitUrl.replace("{0}", String.valueOf(i));
					/* 168 */
					str.append("<a href='" + tempUrl + "'>" + i + "</a>");
					/*     */
				}
				/*     */
			}
			/*     */
			/* 172 */
			str.append("...");
			/* 173 */
			tempUrl = this.submitUrl.replace("{0}", String.valueOf(this.totalPage));
			/* 174 */
			str.append("<a href='" + tempUrl + "'>" + this.totalPage + "</a>");
			/*     */
		}
		/*     */
	}

	/*     */
	/*     */
	/* 181 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/*     */
	/*     */
	/* 184 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/*     */
	/*     */
	/* 187 */
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	/*     */
	/*     */
	/* 190 */
	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}

	/*     */
	/*     */
	/* 193 */
	public void setStyle(String style) {
		this.style = style;
	}
	/*     */
}
