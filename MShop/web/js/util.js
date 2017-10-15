function validateEmpty(id){ 
	var obj = document.getElementById(id);
	var span = document.getElementById(id+"Msg");
	if(obj.value.length >0){
		span.innerHTML="<font color=\"green\">格式正确</font>";
		//obj.className="new_txt";
		return true;
	}else{
		span.innerHTML="<font color=\"red\">格式错误</font>";
		//obj.className="wrong_txt";
		return false;
	}
}
function validateRegex(id,regex){ 
	var obj = document.getElementById(id);
	var span = document.getElementById(id+"Msg");
	if(regex.test(obj.value)){
		span.innerHTML="<font color=\"green\">格式正确</font>";
		if(id=="code"){
		obj.className="c_txt";
		}
		else{
			obj.className="new_txt";
		}
		return true;
	}else{
		span.innerHTML="<font color=\"red\">格式错误</font>";
		if(id=="code"){
		obj.className="wrong_ctxt";
		}else{
			obj.className="wrong_txt";
		}
	}
}
function focus(id,str){
	var obj = document.getElementById(id);
	var span = document.getElementById(id+"Msg");
	obj.className="new_txt";
	span.innerHTML="<font color=\"gray\">"+str+"</font>";
}
function openPage(url){
	window.open(url,"查看详细信息","width=800px; height=400px");
}
function changeCode(obj){
	obj.src = "pages/image.jsp?xhy=" + Math.random();
}
function resetPhoto(url){
	document.getElementById("preview").innerHTML="<img class='img' id='img' src='upload/" + url +"'>";
}
function check(obj,allCheckName,checkName){
    var allCheck = document.getElementById(allCheckName);
    if(allCheck.checked == true && obj.checked == false){
        allCheck.checked=false;
    }
    if(obj.checked == true){
        var item = document.all(checkName);
        if(item.length == undefined){
            document.getElementById(allCheckName).checked = true;
        }else{
            var x ;
            for(x = 0 ; x < item.length ; x++){
                if(item[x].checked != true){
                    break;
                }
            }
            if(x == item.length){
                allCheck.checked=true;
            }
        }
    }
}
function checkboxAll(obj,eleName){
    var item = document.all(eleName);
    if(item.length == undefined){
        document.getElementById(eleName).checked = obj.checked;
    }else{
        for(var x = 0 ; x < item.length ; x++){
            item[x].checked = obj.checked ;
        }
    }
}
// paramName 表示要传递的参数名称
// eleName 表示要获取数据的元素名称
// url 表示要要传递参数的页面，即处理删除数据的页面
function delete_ids(url,paramName,eleName){
    var data = "";
    var items = document.all(eleName);
    var count = 0;
    if(items.length == undefined){
        item = document.getElementById(eleName);
        if(item.checked == true){
            count++;
            data += item.value + "_";
        }
    }else{
        for(var x = 0 ; x < items.length ; x++){
            if(items[x].checked == true){
                count++;
                data += items[x].value + "_" ;
            }
        }
    }
    if(count > 0){
        if(window.confirm("确定要执行此操作?("+data+")")){
            window.location = url + "&" + paramName + "=" + data;
        }
    } else {
        alert("请选择数据！")
    }
}
function update_ids(url,paramName,eleName){
	delete_ids(url,paramName,eleName);
}

function preview(file) {
    var prevDiv = document.getElementById('preview');
    if (file.files && file.files[0]) {
        var reader = new FileReader();
        reader.onload = function(evt) {
            prevDiv.innerHTML = "<img class='img' src="+evt.target.result + " />";
        }
        reader.readAsDataURL(file.files[0]);
    } else {
        prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
    }
}