<script type="text/javascript">
    var curRow = {};
    $(function () {
        $("#tb_user").bootstrapTable({
            toolbar: "#toolbar",
            idField: "Id",
            pagination: true,
            showRefresh: true,
            search: true,
            clickToSelect: true,
            queryParams: function (param) {
                return {};
            },
            url: "/Editable/GetUsers",
            columns: [{
                checkbox: true
            }, {
                field: "UserName",
                title: "用户名",
                formatter: function (value, row, index) {
                    return "<a href=\"#\" name=\"UserName\" data-type=\"text\" data-pk=\""+row.Id+"\" data-title=\"用户名\">" + value + "</a>";
                }
            }, {
                field: "Age",
                title: "年龄",
            }, {
                field: "Birthday",
                title: "生日",
                formatter: function (value, row, index) {
                    var date = eval('new ' + eval(value).source)
                    return date.format("yyyy年MM月dd日");
                }
            },
            {
                field: "DeptName",
                title: "部门"
            }, {
                field: "Hodd",
                title: "爱好"
            }],
            onClickRow: function (row, $element) {
                curRow = row;
            },
            onLoadSuccess: function (aa, bb, cc) {
                $("#tb_user a").editable({
                    url: function (params) {
                        var sName = $(this).attr("name");
                        curRow[sName] = params.value;
                        $.ajax({
                            type: 'POST',
                            url: "/Editable/Edit",
                            data: curRow,
                            dataType: 'JSON',
                            success: function (data, textStatus, jqXHR) {
                                alert('保存成功！');
                            },
                            error: function () { alert("error");}
                        });
                    },
                    type: 'text'
                });
            },
        });
    });</script>