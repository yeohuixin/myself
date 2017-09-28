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
});


var TableInit = function (goodsInfo) {
    var oTableInit = new Object();
    var goodsInfo = goodsInfo;
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_goods').bootstrapTable({
            url: '/goodsmanagement/selectGoods',
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
    $('#addGoodsForm').attr('requsturl','/goodsmanagement/addGoods');
}


function submitForm(){
    var goodsName = $('#goodsNameInput').val();
    if(goodsName == '' || goodsName == undefined){
       alert("请输入名称");
        return;
    }
    //TODO 校验
    top.portalAjax({
        url:$('#addGoodsForm').attr('requsturl'),
        data:$('#addGoodsForm').serialize(),
        success:function(obj){
            if(obj.code == 1){
                $('#addGoodsModal').modal('hide');
                //刷新页面
                $('#tb_goods').bootstrapTable('refresh').reload();
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
