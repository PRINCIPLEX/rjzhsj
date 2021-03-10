<template>
	<div>
		<div>
			<el-button icon="el-icon-plus" size="small" type="primary" @click="showAddQuestion">添加</el-button>
		</div>
		<div class="hr-container">
			<el-card class="hr-card" v-for="(question,index) in questions" :key="index">
				<div slot="header" class="clearfix">
					<el-button style="float: right; padding: 3px 0;color:#e30007;" type="text" icon="el-icon-delete" @click="deleteQuestion(question)"></el-button>
					<div class="container_question">
						<div>
							问题: {{question.question}}
						</div>
					</div>
				</div>
				<div class="container_experience2">
					<div>
            心得:{{question.experience}}
					</div>
				</div>
			</el-card>
		</div>
		<el-dialog title="增加问题和心得" :visible.sync="dialogVisible_add" width="70%">
			<div>
				<div>
					<el-tag>问题</el-tag>
					<el-input type="textarea" :rows="3" placeholder="请输入问题" v-model="AddQuestion.question">
						<!-- class="addQuestionInput" size="small"
v-model="AddQuestion.question">-->
					</el-input>
				</div>
				<div>
					<el-tag>心得</el-tag>
					<el-input type="textarea" :rows="7" placeholder="请输入心得" v-model="AddQuestion.experience">
						<!--
class="addExperienceInput"-->
						<!-- size="large"-->
					</el-input>
				</div>
			</div>
			<span slot="footer" class="dialog-footer">
				<el-button size="small" @click="dialogVisible_add = false">取 消</el-button>
				<el-button size="small" type="primary" @click="addQuestion">确 定</el-button>
			</span>
		</el-dialog>
	</div>
</template>
<script>
	export default {
		name: "QuestionsManage",
		data() {
			return {
				questions: [],
				dialogVisible_add: false,
				AddQuestion: {
					question: '',
					experience: ''
				},
			}
		},
		mounted() {
			this.initQuestions();
		},
		methods: {
			deleteQuestion(question) {
				this.$confirm('此操作将永久删除该问题, 是否继续?', '提示 ', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {

					this.deleteRequest("/questions/manage/" + question.id).then(resp => {
						if (resp) {
							this.initQuestions();
						}
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
			},
			showAddQuestion() {
				this.AddQuestion.question = '';
				this.AddQuestion.experience = '';
				this.dialogVisible_add = true;
			},
			addQuestion() {
				this.postRequest("/questions/manage/",
					this.AddQuestion).then(resp => {
					if (resp) {
						this.initQuestions();
						this.dialogVisible_add = false;
					}
				})
			},
			initQuestions() {
				this.getRequest("/questions/manage/").then(resp => {
					if (resp) {
						this.questions = resp;
					}
				})
			}
		}
	}
</script>
<style scoped>
	.container_question div {
		font-size: 15px;
		color: #ff1456;
		font-weight: bold;
		text-align: left;
	}

	.container_answer div {
		font-size: 15px;
		color: #ff1456;
		font-weight: normal;
		text-align: left;
	}

	.container_experience1 div {
		font-size: 15px;
		color: #409eff;
		font-weight: normal;
		text-align: left;
	}

	.container_experience2 div {
		font-size: 21px;
		color: #409eff;
		font-weight: bold;
	}

	.addQuestionInput {
		width: 200px;
		margin-left: 8px;
	}

	.addExperienceInput {
		width: 600px;
		height: 80%;
		margin-left: 8px;
	}
</style>
