/**
 * Created by yhx on 2016/4/18.
 */

$(document).ready(function () {

    var goodsName = $("#searchInput").val();
    // console.log("client name is " + clientName);
    var clientInfo = $.ajax({
        url: "/goodsmanagement/selectGoods", type: "GET", data: {"goodsName": goodsName}, success: function (data) {
            var objectInfo = JSON.parse(data);
            var goods = objectInfo["data"];
            console.log("goods info is " + JSON.stringify(objectInfo));
            console.log("goods info is " + JSON.stringify(goods));
            //1.初始化Table
            var oTable = new TableInit(goods);
            oTable.Init();
        }
    });

    // //$.neon.loading.show();
    // $('#goods-table-content').DataTable({
    //     "dom": '<t>p',
    //     "iDisplayLength": 100,
    //     "processing": true,
    //     "serverSide": true,
    //     "retrieve":true,
    //     "destroy":true,
    //     "bSort": false,
    //     "drawCallback": function (settings) {
    //         $("#selectAllGoods").attr("checked", false);
    //     },
    //     "ajax": {
    //         "type": "GET",
    //         "url": "/goodsmanagement/selectGoods"
    //     }, "columns": [{
    //         "data": "id",
    //         "render": function (data, type, row) {
    //             return '<input type="checkbox" id="checkbox" value="' + data + '" class="editor-active">';
    //         },
    //         // "className": "dt-body-center",
    //         "width": "10px"
    //     }, {
    //         "data": "goodsname",
    //         "width": "80px"
    //     },  {
    //         "data": "goodsunitprice",
    //         "width": "60px"
    //     },{
    //         "data": "goodscount",
    //         "width": "60px"
    //     }]
    // }).on('xhr.dt', function (e, settings, json) {
    //     //$.neon.loading.hide();
    // });

    // $("#delGoodsInfo").click(function () {
    //     var userIds = [];
    //     var array = $("#good-table-content tbody :checkbox");
    //     $.each(array, function (index, item) {
    //         if (item.checked) {
    //             userIds.push($(item).val());
    //         }
    //     });
    //     if (userIds.length < 1) {
    //         $.alert("请先选择要删除的记录");
    //         return;
    //     }
    //     $.confirm("您确定要删除选择的" + userIds.length + "项记录?", function () {
    //         var url = "/platform/humanResources/deleteParentInfo";
    //         deleteParentItem(url, userIds);
    //     }, "提示");
    // });
});


var TableInit = function (goodsInfo) {
    var oTableInit = new Object();
    var goodsInfo = goodsInfo;
    //初始化Table
    oTableInit.Init = function () {

        $('#tb_goods').bootstrapTable({
            // url: '/client/getclient',
            columns: [{
                field: 'id',
                title: 'ID'
            }, {
                field: 'goodsname',
                title: '名称'
            }, {
                field: 'goodsunitprice',
                title: '单价'
            }, {
                field: 'goodscount',
                title: '数量'
            }
            //     , {
            //
            //     field: 'operate',
            //
            //     title: '操作',
            //
            //     align: 'center',
            //
            //     events: operateEvents,
            //
            //     formatter: operateFormatter
            //
            // }
            ],
            data: goodsInfo,
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit, //页面大小
            offset: params.offset, //页码
            departmentname: $("#txt_search_departmentname").val(),
            statu: $("#txt_search_statu").val()
        };
        return temp;
    };
    return oTableInit;
};

function toGoodManage() {
    window.location="/goodsmanagement/goodsmanagement";
}

function toAddGoodsForm(){
    $('#addGoodsModal').modal({keyboard: true});
    $('#addGoodsModal').modal({
        backdrop: true,
        keyboard: true,
        show: true
    });
    $('#myGoodsModalLabel').text('添加物品');
    $('#goodsIdInput').val('');
    $('#goodsNameInput').val('');
    $('#goodsUnitPriceInput').val('');
    $('#goodsCountInput').val('');
    $('#addGoodsForm').attr('action','/goodsmanagement/addGoods');
}


function submitForm(){
    var goodsName = $('#goodsNameInput').val();
    if(goodsName == '' || goodsName == undefined){
       alert("请输入名称");
        return;
    }
    //TODO 校验
    top.portalAjax({
        url:$('#addGoodsForm').attr('action'),
        data:$('#addGoodsForm').serialize(),
        success:function(obj){
            if(obj.code == 1){
                $('#addGoodsModal').modal('hide');
                //刷新页面
                // $("#base").DataTable().draw();
            }else{
                alert("操作失败");
            }
        },
        error:function(obj){
            alert("操作失败");
        }
    });
}


function cancelForm(){
    $('#addGoodsModal').modal('hide');
}
