/**
 * Created with JetBrains PhpStorm.
 * User: kk
 * Date: 13-8-28
 * Time: 下午4:44
 */
function U() {
	var url = arguments[0] || [];
	var param = arguments[1] || {};
	var url_arr = url.split('/');

	if (!$.isArray(url_arr) || url_arr.length < 2 || url_arr.length > 3) {
		return '';
	}

	if (url_arr.length == 2)
		url_arr.unshift(_GROUP_);

	var pre_arr = ['g', 'm', 'a'];

	var arr = [];
	for (d in pre_arr)
		arr.push(pre_arr[d] + '=' + url_arr[d]);

	for (d in param)
		arr.push(d + '=' + param[d]);

	return _APP_ + '?' + arr.join('&');
}

$(function () {
	/** ��ȡ��һ��ѡ�еĲ������� */
	var boxs = $("input[type='checkbox'][id^='box_']");

	/** ��ȫѡ��ť�󶨵���¼�  */
	$("#checkAll").click(function () {
		// this��checkAll  this.checked��true
		// ���������е�ѡ��״̬��ȫѡ��״̬һ��
		boxs.attr("checked", this.checked);
	})

	/** �������а���긲���Լ�����ƿ��¼�  */
	$("tr[id^='data_']").hover(function () {
		$(this).css("backgroundColor", "#eeccff");
	}, function () {
		$(this).css("backgroundColor", "#ffffff");
	})


	/** ɾ��Ա���󶨵���¼� */
	$("#delete").click(function () {
		/** ��ȡ���û�ѡ�еĸ�ѡ��  */
		var checkedBoxs = boxs.filter(":checked");
		if (checkedBoxs.length < 1) {
			$.ligerDialog.error("��ѡ��һ����Ҫɾ����רҵ��");
		} else {
			/** �õ��û�ѡ�е����е���Ҫɾ����ids */
			var ids = checkedBoxs.map(function () {
				return this.value;
			})

			$.ligerDialog.confirm("ȷ��Ҫɾ����?", "ɾ��רҵ", function (r) {
				if (r) {
					// alert("ɾ����"+ids.get());
					// ��������
					window.location = "${ctx}/view/removeDepartment.do?ids=" + ids.get();
				}
			});
		}
	})
})
