<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Echarts</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript" src="static/echarts/echarts.js"></script>
  <script type="text/javascript" src="static/jquery-1.12.4.min.js"></script>
  <body>
  <div id="chartmain" style="width:600px; height: 400px;"></div>
  </body>
  <script type="text/javascript">
      //指定图标的配置和数据
      var start=[]
      var end=[]
      $.ajax({
          url:"data",
          async:false,
          cache:false,
          success:function(data){
              console.log(data)
              start = data.data.start;
              console.log(start)
              end = data.data.end;
          },
      });
      var start_data=[]
      var end_data=[]
      for(var i=0;i<start.length;i++){
          start_data.push(start[i].PRICE)
      }
      for(var i=0;i<start.length;i++){
          end_data.push(end[i].END_SPOT)
      }
      console.log(end_data)
      console.log(start_data)
      var option = {
          title:{
              text:'ECharts 数据统计'
          },
          tooltip: {
              trigger: 'axis'
          },
          legend: {
              name:'机票价格'
          },
          toolbox: {
              feature: {
                  saveAsImage: {}
              }
          },
          xAxis:{
              data:end_data
          },
          yAxis:{

          },
          series:[{
              name:'机票价格',
              type:'line',
              data: start_data
          }]

      };
      //初始化echarts实例
      var myChart = echarts.init(document.getElementById('chartmain'));

      //使用制定的配置项和数据显示图表
      myChart.setOption(option);
  </script>
</html>
