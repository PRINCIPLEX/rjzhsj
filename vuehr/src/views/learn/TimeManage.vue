<template>
	<div>		<!-- 卡片视图 -->
		<el-card>
			<!-- 2.为Echarts准备一个Dom -->
			<div id="main" style="width: 750px; height:400px;"></div>
		</el-card>
	</div>
</template>

<script>
	// 引入基本模板
	let echarts = require('echarts/lib/echarts');
	// 引入柱状图组件
	require('echarts/lib/chart/bar');
	// 引入提示框和title组件
	require('echarts/lib/component/tooltip');
	require('echarts/lib/component/title');
	export default {
		name: "DataCount",
		data() {
			return {
				XDate: [],
				YDate: [],
			}
		},

		mounted() {
			this.getData();
		},
		methods: {

			getData() {
				this.getRequest("/time/manage/").then(resp => {
					if (resp) {
						var i;
						for(i = 0;i<8;i++)
						{
							this.XDate[i]=resp[i].date;
							this.YDate[i]=resp[i].time;
						}
						this.drawLine();
					}
				})
			},

			drawLine() {
				// 基于准备好的dom，初始化echarts实例
				const echarts = require('echarts/lib/echarts');
				require('echarts/lib/chart/bar');
				require('echarts/lib/chart/line');
				require('echarts/lib/component/grid');
				// 3.基于准备好的dom，初始化echarts实例
				var myChart = echarts.init(document.getElementById('main'))
				// 绘制图表
				myChart.setOption({
					title: {
						text: '',
						subtext: ''
					},
					tooltip: {
						trigger: 'axis'
					},
					legend: {
						data: ['学习时间']
					},
					toolbox: {
						show: true,
						feature: {
							dataZoom: {
								yAxisIndex: 'none'
							},
							dataView: {
								readOnly: false
							},
							magicType: {
								type: ['line', 'bar']
							},
							restore: {},
							saveAsImage: {}
						}
					},
					xAxis: {
						type: 'category',
						boundaryGap: false,
						data: this.XDate,
					},
					yAxis: {
						type: 'value',
						axisLabel: {
							formatter: '{value}'
						}
					},
					series: [{
						name: '学习时间',
						type: 'line',
						data: this.YDate,
						markPoint: {
							data: [{
									type: 'max',
									name: '最大值'
								},
								{
									type: 'min',
									name: '最小值'
								}
							]
						},
						markLine: {
							data: [{
								type: 'average',
								name: '平均值'
							}]
						}
					}, ]
				});
			}
		}
	}
</script>


<style lang="less" scoped>

</style>
