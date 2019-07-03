<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="page-wrapper">
	<!-- 引入 echarts.js -->
	<script src="${pageContext.request.contextPath}/sb-admin2/js/echarts.js"></script>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				
				<div class="panel-body">
					<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
					<div id="divExamKnowlegeQuestions"
						style="width: 600px; height: 400px;"></div>
				</div>
			</div>
		</div>
		<!-- /.panel-body -->
	</div>
	<script type="text/javascript">
	     	// 基于准备好的dom，初始化echarts实例
	        var myChart = echarts.init(document.getElementById('divExamKnowlegeQuestions'));
	        myChart.setOption ({
	            title: {
	                text: '试题统计'
	            },
	            tooltip: {
	            	  trigger: 'axis',
	                  axisPointer: {
	                      type: 'cross'
	                  },
	                  backgroundColor: 'rgba(245, 245, 245, 0.8)',
	                  borderWidth: 1,
	                  borderColor: '#ccc',
	                  padding: 10,
	                  textStyle: {
	                      color: '#000'
	                  },
	                  position: function (pos, params, el, elRect, size) {
	                      var obj = {top: 10};
	                      obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 30;
	                      return obj;
	                  },
	                  extraCssText: 'width: 170px'
	            },
	            legend: {
	                data:['试题数量']
	            },
	            xAxis: {
	                data: [${xAxis}]
	            },
	            yAxis: {},
	            series: [{
	                name: '试题数量',
	                type: 'bar',
	                data: [${data}]
	            }]
	        });
   		</script>
</div>