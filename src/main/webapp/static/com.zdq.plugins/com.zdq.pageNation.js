var current,pageSize,pageUrl,numUrl,sum,mode=2
    $("#confirmBtn").on('click', function () {
        mode = 2
        if (pageUrl != undefined) {
            current = parseFloat($('#current').val())
            pageSize = parseInt($('#pageSize').val())
            if (current > sum / pageSize) {
                if (sum % pageSize == 0)
                    $("#current").val(sum / pageSize)
                else
                    $("#current").val(Math.floor(sum / pageSize) + 1)
            }
            current = $('#current').val()
            pageSize = $('#pageSize').val()
            pageDetailUrl = pageUrl + "&current=" + current + "&pageSize=" + pageSize;
            loadCompanySortData(pageDetailUrl)
        }
    })

    $('#pageSize').on('change', function () {
        mode = 2
        if (pageUrl != undefined) {
            current = parseFloat($('#current').val())
            pageSize = parseInt($('#pageSize').val())
            if (current > sum / pageSize) {
                if (sum % pageSize == 0)
                    $("#current").val(sum / pageSize)
                else
                    $("#current").val(Math.floor(sum / pageSize) + 1)
            }
            current = $('#current').val()
            pageSize = $('#pageSize').val()
            pageDetailUrl = pageUrl + "&current=" + current + "&pageSize=" + pageSize;
            if (sum != undefined) {
                if (sum % pageSize == 0)
                    $("#num").val(sum / pageSize)
                else
                    $("#num").val(Math.floor(sum / pageSize) + 1)
            } else
                $("#num").val('加载中')
            loadCompanySortData(pageDetailUrl)
        }
    })


    $('#preBtn').on('click',function () {
        mode=0;
        current=parseInt($('#current').val())-1;
        if(current>=1){
            if(sum!=undefined) {
                if (current >= 1) {
                    //$("#current").val(current)
                    pageSize = $('#pageSize').val()
                    pageDetailUrl = pageUrl + "&current=" + current + "&pageSize=" + pageSize;
                    loadCompanySortData(pageDetailUrl)
                }
            }else{
                pageSize = $('#pageSize').val()
                pageDetailUrl = pageUrl + "&current=" + current + "&pageSize=" + pageSize;
                loadCompanySortData(pageDetailUrl)
            }
        }
    })

    $('#nextBtn').on('click',function () {
        mode=1;
        current=parseFloat($('#current').val())+1;
        pageSize=parseInt($('#pageSize').val())
        if(sum%pageSize==0)
            sumPage=sum/pageSize
        else
            sumPage=Math.floor(sum/pageSize)+1

        if(sum!=undefined){
            if(current<=sumPage){
                //$("#current").val(current)
                pageSize=$('#pageSize').val()
                pageDetailUrl=pageUrl+"&current="+current+"&pageSize="+pageSize;
                loadCompanySortData(pageDetailUrl)
            }
        }else{
            pageSize=$('#pageSize').val()
            pageDetailUrl=pageUrl+"&current="+current+"&pageSize="+pageSize;
            loadCompanySortData(pageDetailUrl)
        }

    })



    // 加载企业排名数据
    function loadNum(url) {
        $.ajax({
            type: "GET",
            url: url,
            async:true,
            success: function (data) {
                if (data.meta.success) {
                    var companyData = data.data
                    initNum(JSON.stringify(companyData))
                } else {
                    alert(data.meta.message)
                }
            }
        });
    }



    $("#current").on('change',function () {
        current = $('#current').val();
        var ex = /^[1-9]\d*$/;
        if (!ex.test(current)) {
            $('#current').val(1);
            alert("请输入数字")
        }
    })


function loadCompanySortData(url) {
    $("#info").val("加载中")
    $.ajax({
        type: "GET",
        url: url,
        async:true,
        success: function (data) {
            if (data.meta.success) {
                var companyData = data.data
                console.log(companyData)
               // initCompanySort(JSON.stringify(companyData))
            } else {
                alert(data.meta.message)
            }
        }
    });
}

function initNum(companyData) {
    var data = JSON.parse(companyData)
    num= parseInt(data.qypm[0].num)
    sum=num;
    pageSize=parseInt($('#pageSize').val())
    if(num%pageSize==0)
        $("#num").val(num/pageSize)
    else
        $("#num").val(Math.floor(num/pageSize)+1)
    if(parseInt($("#current").val())>parseInt($("#num").val()))
        $("#current").val($("#num").val())
}