<template>
  <div>
    <div style="margin-top: 10px;display: flex;justify-content: space-between;padding: 0 10px;">
      <div>
        <el-input v-model="keywords" placeholder="通过用户名搜索用户..." prefix-icon="el-icon-search"
                  style="width: 400px;margin-right: 10px" @keydown.enter.native="doSearch"></el-input>
        <el-button icon="el-icon-search" type="primary" @click="doSearch">搜索</el-button>
      </div>
      <el-button icon="el-icon-plus" type="success" plain @click="addUsers">新增操作员</el-button>
    </div>
    <div class="hr-container">
      <el-card class="hr-card" v-for="(hr,index) in hrs" :key="index">
        <div slot="header" class="clearfix">
          <span>{{hr.name}}</span>
          <el-button style="float: right; padding: 3px 0;color: #e30007;" type="text"
                     icon="el-icon-delete" @click="deleteHr(hr)"></el-button>
        </div>
        <div>
          <div class="img-container">
            <img :src="hr.userface" :alt="hr.name" :title="hr.name" class="userface-img">
          </div>
          <div class="userinfo-container">
            <div>用户名：{{hr.name}}</div>
            <div>手机号码：{{hr.phone}}</div>
            <div>电话号码：{{hr.telephone}}</div>
            <div>地址：{{hr.address}}</div>
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
                      :key="indexj">{{role.nameZh}}
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
                    v-for="r in allroles"
                    :key="r.id"
                    :label="r.nameZh"
                    :value="r.id">
                  </el-option>
                </el-select>
                <el-button slot="reference" icon="el-icon-more" type="text"></el-button>
              </el-popover>
            </div>
            <div>备注：{{hr.remark}}</div>
          </div>
        </div>
      </el-card>
      <el-dialog
        :title="title"
        :visible.sync="dialogVisible"
        :modal="false"
        width="60%">
        <div>
          <el-form :model="users" :rules="rules" ref="purForm">
            <el-row>
              <el-col :span="7">
                <el-form-item label="姓名:" prop="name">
                  <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                            v-model="users.name" placeholder="请输入操作员姓名……"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="7">
                <el-form-item label="手机号:" prop="phone">
                  <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                            v-model="users.phone" placeholder="请输入操作员手机号……"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="7">
                <el-form-item label="公司电话:" prop="telephone">
                  <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                            v-model="users.telephone" placeholder="请输入公司电话……"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="7">
                <el-form-item label="用户地址:" prop="address">
                  <el-input size="mini" style="width: 120px" prefix-icon="el-icon-edit"
                            v-model="users.address" placeholder="请输入用户地址……"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="7">
                <el-form-item label="用户名：" prop="username">
                  <el-input size="mini" style="width: 140px" prefix-icon="el-icon-edit"
                            v-model="users.username" placeholder="请输入登录用户名……"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="7">
                <el-form-item label="头像：" prop="userface">
                  <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                            v-model="users.userface" placeholder="请输入用户头像……"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="7">
                <el-form-item label="备注：" prop="remark">
                  <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                            v-model="users.remark" placeholder="请输入备注……"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </div>
        <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="doAddUser">确 定</el-button>
            </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  export default {
    name: "SysUsers",
    data() {
      return {
        keywords: '',
        title: '',
        dialogVisible: false,
        hrs: [],
        selectedRoles: [],
        allroles: [],
        users: {
          id: "1",
          name: "周逸航",
          phone: "17371248552",
          telephone: "027-00000000",
          address: "湖北黄冈",
          username: "系统管理员",
          password: "",
          userface: "",
          remark: "",
        },
        rules: {
          name: [{required: true, message: '请输入姓名', trigger: 'blur'}],
          phone: [{required: true, message: '请输入手机号', trigger: 'blur'}],
          address: [{required: true, message: '请输入地址', trigger: 'blur'}],
          username: [{required: true, message: '请输入登录用户名', trigger: 'blur'}],
          userface: [{required: true, message: '请输入头像', trigger: 'blur'}],
        }
      }
    },
    mounted() {
      this.initHrs();
    },
    methods: {
      deleteHr(hr) {
        this.$confirm('此操作将永久删除【' + hr.name + '】, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.deleteRequest("/system/users/" + hr.id).then(resp => {
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
          let url = '/system/users/role?usersid=' + hr.id;
          this.selectedRoles.forEach(sr => {
            url += '&uids=' + sr;
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
        this.putRequest("/system/users/", hr).then(resp => {
          if (resp) {
            this.initHrs();
          }
        })
      },
      initAllRoles() {
        this.getRequest("/system/users/roles").then(resp => {
          if (resp) {
            this.allroles = resp;
          }
        })
      },
      initHrs() {
        this.getRequest("/system/users/?keywords=" + this.keywords).then(resp => {
          if (resp) {
            this.hrs = resp;
          }
        })
      },
      emptyUsers() {
        this.users = {
          id: "",
          name: "",
          phone: "",
          telephone: "",
          address: "",
          username: "",
          password: "",
          userface: "",
          remark: "",
        }
      },
      addUsers() {
        this.emptyUsers();
        this.title = "新增操作员";
        this.dialogVisible = true;
        this.$notify({
          title: '注意',
          message: '新增操作员用户登录密码默认为空！',
          type: 'warning'
        });
      },
      doAddUser() {
        this.$refs['purForm'].validate(valid => {
          if (valid) {
            this.postRequest("/system/users/", this.users).then(resp => {
              if (resp) {
                this.dialogVisible = false;
                this.initHrs();
              }
            })
          }
        });
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
