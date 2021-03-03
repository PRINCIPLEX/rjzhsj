<template>
  <div>
    <el-form
        :rules="rules"
        ref="loginForm"
        v-loading="loading"
        element-loading-text="正在登录..."
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
        :model="loginForm"
        class="loginContainer">
      <h3 class="loginTitle">系统登录</h3>
      <el-form-item prop="username">
        <el-input size="normal" type="text" v-model="loginForm.username" auto-complete="off"
                  placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input size="normal" type="password" v-model="loginForm.password" auto-complete="off"
                  placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item prop="code">
        <el-input size="normal" type="text" v-model="loginForm.code" auto-complete="off"
                  placeholder="点击图片更换验证码" @keydown.enter.native="submitLogin" style="width: 250px"></el-input>
        <img :src="vcUrl" @click="updateVerifyCode" alt="" style="cursor: pointer">
      </el-form-item>
      <el-checkbox size="normal" class="loginRemember" v-model="checked"></el-checkbox>
      <el-button size="normal" type="primary" style="width: 30%;" @click="submitLogin">登录</el-button>
      <el-button size="normal" type="primary" style="width: 30%;" @click="showAddHr">注册</el-button>
    </el-form>

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
</template>

<script>

export default {
  name: "Login",
  data() {
    return {
      loading: false,
      dialogVisible1: false,
      vcUrl: '/verifyCode?time=' + new Date(),
      loginForm: {
        username: 'admin',
        password: '123',
        code: ''
      },
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
      checked: true,
      rules: {
        username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}],
        code: [{required: true, message: '请输入验证码', trigger: 'blur'}]
      }
    }
  },
  methods: {
    updateHrInfo1(){
      this.putRequest("/hr/info1", this.hr3).then(resp => {
        if (resp) {
          this.dialogVisible1 = false;
        }
        this.initHrs();
      })
    },
    showAddHr() {
      this.dialogVisible1 = true;
    },
    updateVerifyCode() {
      this.vcUrl = '/verifyCode?time=' + new Date();
    },
    submitLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          this.postRequest('/doLogin', this.loginForm).then(resp => {
            this.loading = false;
            if (resp) {
              this.$store.commit('INIT_CURRENTHR', resp.obj);
              window.sessionStorage.setItem("user", JSON.stringify(resp.obj));
              let path = this.$route.query.redirect;
              this.$router.replace((path == '/' || path == undefined) ? '/home' : path);
            } else {
              this.vcUrl = '/verifyCode?time=' + new Date();
            }
          })
        } else {
          return false;
        }
      });
    }
  }
}
</script>

<style>
.loginContainer {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 15px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.loginTitle {
  margin: 15px auto 20px auto;
  text-align: center;
  color: #505458;
}

.loginRemember {
  text-align: left;
  margin: 0px 0px 15px 0px;
}

.el-form-item__content {
  display: flex;
  align-items: center;
}
</style>
