<template>
  <div class="baseheader">
    <!-- <el-row :gutter="20">
      <el-col v-for="(item,index) of col" :key="index" :span="item.span">
        <div
          :class="headerTitle == index+1 ? 'grid-content bg-purple headerTitle' : 'grid-content bg-purple'"
        >{{ item.title }}</div>
      </el-col>
    </el-row>-->

    <el-row :gutter="20">
      <el-col :span="6">
        <div class="grid-content bg-purple head_title">物料生产管理erp系统 v1.0</div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content bg-purple">
          <span class="el-dropdown-link home_userinfo" style="display: flex;align-items: center">
            <!-- {{user.name}} -->username/
            <i>
              <!-- <img
                v-if="user.userface!=''"
                :src="user.userface"
                style="width: 40px;height: 40px;margin-right: 5px;margin-left: 5px;border-radius: 40px"
              /> -->   userface
            </i>
          </span>
        </div>
      </el-col>

    </el-row>
  </div>
</template>

<script>
  export default {
    name: "baseheader",
    data() {
      return {
        headerTitle: 1,
        col: [
          {
            id: "0",
            span: "6",
            title: "物料生产管理erp系统 v1.0"
          },
          {
            id: "1",
            span: "6",
            title: ""
          },
          {
            id: "2",
            span: "9",
            title: ""
          },
          {
            id: "3",
            span: "3",
            title: "头像+昵称"
          }
        ]
      };
    }
  };
</script>

<style>
  .baseheader {
    width: 100%;
    background: #3c8dbc;
    margin: 0;
    position: relative;
  }

  .el-row {
    width: 100%;
    padding: 20px 0;
    background: #3c8dbc;
    color: #ffffff;

  &
  :last-child {
    margin-bottom: 0;
  }

  }
  .el-col {
    border-radius: 4px;
  }

  .bg-purple-dark {
    background: #99a9bf;
  }

  .bg-purple {
    background: #3c8dbc;
  }

  .bg-purple-light {
    background: #e5e9f2;
  }

  .grid-content {
    /* border-radius: 4px; */
    min-height: 36px;
  }

  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }

  .head_title {
    font-family: "华文楷体";
    font-size: 24px;
  }
</style>
