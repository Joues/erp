<template>
  <!--  物料生产管理erp系统 V1.0-->
  <div class="header">
    <div class="left">左侧logo角标</div>
    <!-- 系统标题 -->
    <div class="title">物料生产管理erp系统</div>
    <!-- 回到主页+账户信息+风险预警 -->
    <div class="title_icon">
      <ul>
        <li v-for="(item,index) of icons" :key="index" v-html="item.icon">
          {{ item.icon }}
        </li>
      </ul>
    </div>

    <div class="head">
      <a href="">头像</a>
      <a href="">昵称信息</a>
    </div>
  </div>
</template>

<script>
  export default {
    name: "header",
    data() {
      return {
        rr: "你好啊",
        icons: [
          {
            id: '0',
            icon: '<span class="icon iconfont" >&#xe618;</span>'
          },
          {
            id: '1',
            icon: '<span class="icon iconfont" >&#xe6eb;</span>'
          },
          {
            id: '2',
            icon: '<span class="icon iconfont" >&#xe847;</span>'
          }
        ]
      };
    },
  }
</script>

<style scoped>
  .header {
    display: flex;
    background: #3C8DBC;
    /* border: red 1px solid; */
  }

  .left {
    width: 200px;
    padding: 15px 0;
  }

  .title {
    width: 400px;
    padding: 15px 0;
    font-family: '楷体';
    font-size: 24px;
    color: #fff;
  }

  .title_icon {

  }

  ul {
    list-style: none;
  }

  li {

  }

  .head {

  }
</style>
