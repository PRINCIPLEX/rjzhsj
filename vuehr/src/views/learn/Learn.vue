<template>
	<div class="test_two_box">
		<video id="myVideo" class="video-js">
			<source src="http://mpvideo.qpic.cn/0bf2v4aaiaaajqaeao2zl5pfbl6dasxqabaa.f10002.mp4?dis_k=76bb6db5d7c3c5fb86b7495ce43d712b&dis_t=1614605586&spec_id=MzI1NDY0MTkzNQ%3D%3D1614605585&vid=wxv_1230044624060841984&format_id=10002" type="video/mp4">
		</video>
	</div>
</template>

<script>
	/* eslint-disable */
	export default {
		name: "TestTwo",
		data() {
			return {};
		},
		mounted() {
			this.initVideo();
		},
		methods: {
			initVideo() {
				//初始化视频方法
				let myPlayer = this.$video(myVideo, {
					//确定播放器是否具有用户可以与之交互的控件。没有控件，启动视频播放的唯一方法是使用autoplay属性或通过Player API。
					controls: true,
					//自动播放属性,muted:静音播放
					//建议浏览器是否应在<video>加载元素后立即开始下载视频数据。
					preload: "auto",
					//设置视频播放器的显示宽度（以像素为单位）
					width: "800px",
					//设置视频播放器的显示高度（以像素为单位）
					height: "600px"
				});
			}
		}
	};
</script>

<style scoped>
</style>
