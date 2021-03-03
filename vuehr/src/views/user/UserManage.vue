<template>
  <div>
    <div style="margin-top: 10px;display: flex;justify-content: center">
      <el-input v-model="keywords" placeholder="通过用户名搜索用户..." prefix-icon="el-icon-search"
                style="width: 400px;margin-right: 10px" @keydown.enter.native="doSearch"></el-input>
      <el-button icon="el-icon-search" type="primary" @click="doSearch">搜索</el-button>
      <el-button type="primary" icon="el-icon-plus" @click="showAddHr">
        添加用户
      </el-button>
    </div>
    <div class="hr-container">
      <el-card class="hr-card" v-for="(hr,index) in hrs" :key="index">
        <div slot="header" class="clearfix">
          <span>{{ hr.name }}</span>
          <el-button style="float: right; padding: 3px 0;color: #e30007;" type="text"
                     icon="el-icon-delete" @click="deleteHr(hr)"></el-button>
        </div>
        <div>
          <div class="img-container">
            <img :src="hr.userface" :alt="hr.name" :title="hr.name" class="userface-img">
          </div>
          <div class="userinfo-container">
            <div>用户名：{{ hr.name }}</div>
            <div>手机号码：{{ hr.phone }}</div>
            <div>电话号码：{{ hr.telephone }}</div>
            <div>地址：{{ hr.address }}</div>
            <div>用户状态：
              <el-switch
                  v-model="hr.enabled"
                  active-text="启用"
                  @change="enabledChange(hr)"
                  active-color="#13ce66"
                  inactive-color="#ff4949"
                  inactive-text="禁用">
              </el-switch>
            </div>
            <div>用户角色：
              <el-tag type="success" style="margin-right: 4px" v-for="(role,indexj) in hr.roles"
                      :key="indexj">{{ role.nameZh }}
              </el-tag>
              <el-popover
                  placement="right"
                  title="角色列表"
                  @show="showPop(hr)"
                  @hide="hidePop(hr)"
                  width="200"
                  trigger="click">
                <el-select v-model="selectedRoles" multiple placeholder="请选择">
                  <el-option
                      v-for="(r,indexk) in allroles"
                      :key="indexk"
                      :label="r.nameZh"
                      :value="r.id">
                  </el-option>
                </el-select>
                <el-button slot="reference" icon="el-icon-more" type="text"></el-button>
              </el-popover>
            </div>
            <div>备注：{{ hr.remark }}</div>
            <div style="display: flex;justify-content: space-around;margin-top: 10px">
              <el-button type="primary" @click="showUpdateHrInfoView(hr)">修改信息</el-button>
              <el-button type="danger" @click="showUpdatePasswdView(hr)">修改密码</el-button>
            </div>
          </div>
        </div>
      </el-card>
      <el-dialog
          title="修改用户信息"
          :visible.sync="dialogVisible"
          width="30%">
        <div>
          <table>
            <tr>
              <td>
                <el-tag>用户昵称：</el-tag>
              </td>
              <td>
                <el-input v-model="hr2.name"></el-input>
              </td>
            </tr>
            <tr>
              <td>
                <el-tag>电话号码：</el-tag>
              </td>
              <td>
                <el-input v-model="hr2.telephone"></el-input>
              </td>
            </tr>
            <tr>
              <td>
                <el-tag>手机号码：</el-tag>
              </td>
              <td>
                <el-input v-model="hr2.phone"></el-input>
              </td>
            </tr>
            <tr>
              <td>
                <el-tag>用户地址：</el-tag>
              </td>
              <td>
                <el-input v-model="hr2.address"></el-input>
              </td>
            </tr>
          </table>
        </div>
        <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="updateHrInfo">确 定</el-button>
  </span>
      </el-dialog>
      <el-dialog
          title="修改密码"
          :visible.sync="passwdDialogVisible"
          width="30%">
        <div>
          <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"
                   class="demo-ruleForm">
            <el-form-item label="请输入新密码" prop="pass">
              <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="新确认密码" prop="checkPass">
              <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
              <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-dialog>
      <el-dialog
          title="添加用户"
          :visible.sync="dialogVisible1"
          width="30%">
        <div>
          <table>
            <tr>
              <td>
                <el-tag>用户昵称：</el-tag>
              </td>
              <td>
                <el-input v-model="hr3.name"></el-input>
              </td>
            </tr>
            <tr>
              <td>
                <el-tag>电话号码：</el-tag>
              </td>
              <td>
                <el-input v-model="hr3.telephone"></el-input>
              </td>
            </tr>
            <tr>
              <td>
                <el-tag>手机号码：</el-tag>
              </td>
              <td>
                <el-input v-model="hr3.phone"></el-input>
              </td>
            </tr>
            <tr>
              <td>
                <el-tag>用户地址：</el-tag>
              </td>
              <td>
                <el-input v-model="hr3.address"></el-input>
              </td>
            </tr>
            <tr>
              <td>
                <el-tag>昵称：</el-tag>
              </td>
              <td>
                <el-input v-model="hr3.username"></el-input>
              </td>
            </tr>
            <tr>
              <td>
                <el-tag>密码：</el-tag>
              </td>
              <td>
                <el-input v-model="hr3.password"></el-input>
              </td>
            </tr>
          </table>
        </div>
        <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible1 = false">取 消</el-button>
    <el-button type="primary" @click="updateHrInfo1">确 定</el-button>
  </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserManage",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        oldpass: '',
        pass: '',
        checkPass: ''
      },
      rules: {
        oldpass: [
          {validator: validatePass, trigger: 'blur'}
        ],
        pass: [
          {validator: validatePass, trigger: 'blur'}
        ],
        checkPass: [
          {validator: validatePass2, trigger: 'blur'}
        ]
      },
      id: -1,
      keywords: '',
      hrs: [],
      hr2: null,
      hr3: {
        name: 'mbw',
        phone: '17801233393',
        telephone: 'null',
        address: 'null',
        password: 123,
        enabled: false,
        username: 'mabowen',
        userface: 'http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg',
        remark: null
      },
      selectedRoles: [],
      allroles: [],
      dialogVisible: false,
      dialogVisible1: false,
      passwdDialogVisible: false
    }
  },
  mounted() {
    this.initHrs();
  },
  methods: {
    showAddHr() {
      this.dialogVisible1 = true;
    },
    showUpdateHrInfoView(hr) {
      this.id = hr.id;
      Object.assign(this.hr2, hr);
      this.dialogVisible = true;
    },
    showUpdatePasswdView(hr) {
      this.id = hr.id;
      this.passwdDialogVisible = true;
    },
    updateHrInfo() {
      this.hr2.id = this.id;
      this.putRequest("/hr/info", this.hr2).then(resp => {
        if (resp) {
          this.dialogVisible = false;
        }
        this.initHrs();
      })
    },
    updateHrInfo1(){
      this.putRequest("/hr/info1", this.hr3).then(resp => {
        if (resp) {
          this.dialogVisible1 = false;
        }
        this.initHrs();
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.ruleForm.hrid = this.id;
          this.putRequest("/hr/pass1", this.ruleForm).then(resp => {
            this.passwdDialogVisible = false;
          })
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    deleteHr(hr) {
      this.$confirm('此操作将永久删除【' + hr.name + '】, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteRequest("/system/hr/" + hr.id).then(resp => {
          if (resp) {
            this.initHrs();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    doSearch() {
      this.initHrs();
    },
    hidePop(hr) {
      let roles = [];
      Object.assign(roles, hr.roles);
      let flag = false;
      if (roles.length != this.selectedRoles.length) {
        flag = true;
      } else {
        for (let i = 0; i < roles.length; i++) {
          let role = roles[i];
          for (let j = 0; j < this.selectedRoles.length; j++) {
            let sr = this.selectedRoles[j];
            if (role.id == sr) {
              roles.splice(i, 1);
              i--;
              break;
            }
          }
        }
        if (roles.length != 0) {
          flag = true;
        }
      }
      if (flag) {
        let url = '/system/hr/role?hrid=' + hr.id;
        this.selectedRoles.forEach(sr => {
          url += '&rids=' + sr;
        });
        this.putRequest(url).then(resp => {
          if (resp) {
            this.initHrs();
          }
        });
      }
    },
    showPop(hr) {
      this.initAllRoles();
      let roles = hr.roles;
      this.selectedRoles = [];
      roles.forEach(r => {
        this.selectedRoles.push(r.id);
      })
    },
    enabledChange(hr) {
      delete hr.roles;
      this.putRequest("/system/hr/", hr).then(resp => {
        if (resp) {
          this.initHrs();
        }
      })
    },
    initAllRoles() {
      this.getRequest("/system/hr/roles").then(resp => {
        if (resp) {
          this.allroles = resp;
        }
      })
    },
    initHrs() {
      this.getRequest("/system/hr/?keywords=" + this.keywords).then(resp => {
        if (resp) {
          this.hrs = resp;
          this.hr2 = Object.assign({}, resp[0]);
        }
      })
    }
  }
}
</script>

<style>
.userinfo-container div {
  font-size: 12px;
  color: #409eff;
}

.userinfo-container {
  margin-top: 20px;
}

.img-container {
  width: 100%;
  display: flex;
  justify-content: center;
}

.userface-img {
  width: 72px;
  height: 72px;
  border-radius: 72px;
}

.hr-container {
  margin-top: 10px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}

.hr-card {
  width: 350px;
  margin-bottom: 20px;
}
</style>