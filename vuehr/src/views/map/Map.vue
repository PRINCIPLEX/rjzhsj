<template>
	<div>
		<div>
			<el-button icon="el-icon-plus" size="small" type="primary" @click="showAddMap">添加</el-button>
		</div>
		<div class="mapMain">
			<el-table :data="maps" border @selection-change="handleSelectionChange" size="small" stripe style="width: 70%">
				<el-table-column type="selection" width="55">
				</el-table-column>
				<el-table-column prop="id" label="编号" width="55">
				</el-table-column>
				<el-table-column prop="mapName" label="地区" width="180">
				</el-table-column>
				<el-table-column prop="mapKey" width="150" label="关键字">
				</el-table-column>
				<el-table-column label="操作" width="180">
					<template slot-scope="scope">

						<el-button size="mini" @click="showLookView(scope.$index,scope.row)">预览
						</el-button>
						<el-button size="mini" type="danger" @click="handleDelete(scope.$index,scope.row)">删除
						</el-button>
					</template>
				</el-table-column>
			</el-table>

		</div>
		<el-dialog title="地图展示" :visible.sync="dialogVisible_show" width="80%">
			<!-- <div class="img-container">
				<img :src="m.url" :alt="m.name" :title="m.name" class="map-img">
			</div>
 -->
			<!-- :keyword="keyword"-->
			<div>
				<baidu-map :scroll-wheel-zoom="true">
					<bm-view style="width: 100%; height:24.375rem; flex: 1" class="bm-view"></bm-view>
					<bm-local-search :keyword="m.mapKey" :auto-viewport="true" :location="m.mapName"></bm-local-search>
				</baidu-map>
			</div>
			<span slot="footer" class="dialog-footer">
				<el-button size="small" @click="dialogVisible_show = false"> 退 出
				</el-button>
				<!-- <el-button size="small" type="primary"
@click="doUpdate">确 定</el-button>-->
			</span>
		</el-dialog>
		<el-dialog title="增加地图" :visible.sync="dialogVisible_add" width="40%">
			<div>
				<div>
					<el-tag>地图名称</el-tag>
					<el-input class="addMapInput" size="small" v-model="AddMap.mapName"></el-input>
				</div>
				<div>
					<el-tag>地图关键字</el-tag>
					<el-input class="addMapInput" size="small" v-model="AddMap.mapKey">
					</el-input>
				</div>
			</div>
			<span slot="footer" class="dialog-footer">
				<el-button size="small" @click="dialogVisible_add = false"> 取 消
				</el-button>
				<el-button size="small" type="primary" @click="addMap"> 确 定
				</el-button>
			</span>
		</el-dialog>
	</div>
</template>
<script>
	export default {
		name: "baidu",
		data() {
			return {
				m: {
					mapName: '',
					mapKey: ''
				},
				dialogVisible_show: false,
				dialogVisible_add: false,
				loading: false,
				AddMap: {
					mapName: '',
					mapKey: ''
				},
				multipleSelection: [],
				maps: []
			}
		},
		mounted() {
			this.initMaps();
		},
		methods: {

			handleSelectionChange(val) {
				this.multipleSelection = val;
			},
			showAddMap() {
				// if( this.AddMap.name&&this.AddMap.url){}
				// else {
				this.AddMap.mapName = '';
				this.AddMap.url = '';
				// }
				this.dialogVisible_add = true;
			},
			
			addMap() {
				
				this.postRequest("/map/manage/",
					this.AddMap).then(resp => {
					if (resp) {
						this.initMaps();
						this.dialogVisible_add = false;
					}
				})
				// } else {
				// this.$message.error('地图名称和地图链接不可以为空 ');
				// }
			},
			showLookView(index, data) {
				Object.assign(this.m, data);
				this.dialogVisible_show = true;
			},
			
			handleDelete(index, data) {
				this.$confirm('此操作将永久删除【' + data.mapName + '】,是否继续 ? ', '提示 ', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.deleteRequest("/map/manage/" +
						data.id).then(resp => {
						if (resp) {
							this.initMaps();
						}
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
			},
			initMaps() {
				this.loading = true;
				this.getRequest("/map/manage/").then(resp => {
					this.loading = false;
					if (resp) {
						this.maps = resp;
					}
				})
			}
		}
	}
</script>
<style>
	.addMapInput {
		width: 200px;
		margin-left: 8px;
	}

	.mapMain {
		margin-top: 10px;
	}

	.map-img {
		width: 600px;
		height: 600px;
		border-radius: 72px;
	}

	.img-container {
		width: 900px;
		height: 900px;
		/*width: 150px;*/
		/*height: 120px;*/
		display: flex;
		justify-content: center;
	}
</style>
