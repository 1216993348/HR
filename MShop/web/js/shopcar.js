var allPrice = 0.0;
window.onload = function () {    // 在页面加载完毕之后进行统计计算
    document.getElementById("allPrice").innerHTML = "<font color='red'>总金额：" + allPrice + "</font>";
}

function addCar(gid) {
    var count = parseInt(document.getElementById(gid).value);
    var gprice = parseFloat(document.getElementById("gprice-" + gid).innerHTML);
    allPrice -= document.getElementById("cal-" + gid).innerHTML;
    count++;
    document.getElementById(gid).value = count;
    cal(gid);
}

function subCar(gid) {
    var count = parseInt(document.getElementById(gid).value);
    var gprice = parseFloat(document.getElementById("gprice-" + gid).innerHTML);
    allPrice -= document.getElementById("cal-" + gid).innerHTML;
    count--;
    if (count > 0) {
        document.getElementById(gid).value = count;
        cal(gid);
    }
}

function cal(gid) {
    var count = parseInt(document.getElementById(gid).value);
    var gprice = parseFloat(document.getElementById("gprice-" + gid).innerHTML);
    document.getElementById("cal-" + gid).innerHTML = count * gprice;
    allPrice += count * gprice;
    if (document.getElementById("allPrice") != undefined) {
        document.getElementById("allPrice").innerHTML = "<font color='red'>总金额：" + allPrice + "</font>";
    }
}

function submitOrders(url) {
    var items = document.all('gid');
    var count = 0;
    if(items.length == undefined){
        var item = document.getElementById('gid');
        if(item.checked == true){
            count++;
        }
    }else{
        for(var x = 0 ; x < items.length ; x++){
            if(items[x].checked == true){
                count++;
            }
        }
    }
    if(count > 0){
        if(window.confirm("确定要提交订单？")){
            document.forms['form'].action = url;
            document.forms['form'].submit();
        }
    } else {
        alert("请选择商品！")
    }
}

function clean() {
    var item = document.all('gid');
    if (item.length == undefined) {
        var checkBox = document.getElementById('gid') ;
        var text = document.getElementById(checkBox.value);
            text.name = checkBox.value;
    } else {
        for (var x = 0; x < item.length; x++) {
            var checkBox = item[x];
            var text = document.getElementById(checkBox.value);
            text.name = checkBox.value;
        }
    }
}

function change(obj, eleName) {
    if (obj.checked == true) {
        document.getElementById(eleName).name = "orders-" + eleName;
    } else {
        document.getElementById(eleName).name = eleName;
    }

}

function checkboxAll2(obj, eleName) {
    var item = document.all(eleName);
    if (item.length == undefined) {
        var checkBox = document.getElementById(eleName) ;
        checkBox.checked = obj.checked;
        var text = document.getElementById(checkBox.value);
        if (checkBox.checked == true) {
            text.name = "orders-" + checkBox.value;
        } else {
            text.name = checkBox.value;
        }
    } else {
        for (var x = 0; x < item.length; x++) {
            var checkBox = item[x] ;
            checkBox.checked = obj.checked;
            var text = document.getElementById(''+ checkBox.value + '')
            if (checkBox.checked == true) {
                text.name = "orders-" + checkBox.value;
            } else {
                text.name = checkBox.value;
            }

        }
    }
}