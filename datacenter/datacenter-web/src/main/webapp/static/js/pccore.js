/*简单封装jquery*/
function portalAjax(obj)
{
    var _type="POST";
    var async_type = true;
    if(obj.type!=undefined)
        _type=obj.type;
    if(obj.async!=undefined) {
    	async_type = obj.async;
    }
    $.ajax({
            url: obj.url,
            context: document.body,
            type:_type,
            async:async_type,
            data:obj.data,
            dataType:"json",
            success: function(res){
                if(res.code=="-1") //如果session过期
                    top.window.location="/portal/index";
                else if(obj.success) obj.success(res);
            },
            error:function(data){
                if(obj.error) obj.error(data);
            }
        }
    );
}