<template>
	<div class="test_two_box">
		<video id="myVideo" class="video-js">
			<source src="../../../public/1.mp4" type="video/mp4">
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
