/**
 * Created by yhx on 2016/4/18.
 */

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
    $('#myGoodsModalLabel').text('添加角色');
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
