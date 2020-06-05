<template>
  <div class="baseAside">
    <template>
      <div class="head">头像+昵称</div>
    </template>
    <el-aside style="width: 230px;height: 100%">
      <el-menu
        index="index"
        class="el-menu-vertical-demo"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        :unique-opened="true"
        @select="menuClick"
        router
      >
        <div
          v-for="(item,index) in this.$router.options.routes.slice(1)"
          :key="index"
          index="index"
          v-if="!item.hidden">
          <el-submenu
            v-for="(mulu,indexi) in item.parent"
            :key="indexi"
            :index="indexi+''"
          >
            <template slot="title">
              <div class="daohang">
                <!--                <i class="el-icon-message"></i>-->
                {{ mulu.name }}
              </div>
            </template>

            <el-submenu
              v-for="(child, indexj) in mulu.children"
              :key="indexj"
              :index="child.path"
            >
              <template slot="title">{{ child.name }}</template>
              <el-menu-item
                v-for="(tom, indexl) in child.grandson"
                :key="indexl"
                :index = "tom.path"
              >{{ tom.name }}
              </el-menu-item>
            </el-submenu>
          </el-submenu>
        </div>
      </el-menu>
    </el-aside>
  </div>
  <!-- author: yihangjou
  date: 2019/12/06 凌晨 0:12-->
</template>

<script>
  /**
   * author: YihangJou
   * cnblogs: https://www.cnblogs.com/yihangjou/
   * date: 2019/12/28 17:47
   **/
  export default {
    name: "baseaside",
    data() {
      return {};
    },
    methods: {
      menuClick(index, indexPath) {
        console.log(index)
        console.log(indexPath)
      },
      defaultOpen() {

      }
    },
    created() {
      console.log("声明周期钩子测试开始。")
      console.log(this.$router.options.routes.slice(1))
    }
  };
</script>

<style scoped>
  .baseAside {
    background-color: #222d32;
    height: 100%;
    width: 230px;
    position: relative;
  }

  .head {
    line-height: 60px;
    text-align: center;
    color: #ffffff;
    margin: 0 auto;
  }

  .daohang {
    margin: 0 -20px;
    width: 230px;
    color: #dfe6e9;
    background-color: #222d32;
  }

  .daohang:hover {
    color: #ffffff;
    background: #2d3436;
  }

  .xuanxiang {
    width: 230px;
    margin-left: -40px;
    color: gray;
    background-color: #222d32;
  }

  .xuanxiang:hover {
    background: #2d3436;
  }

  /*定义滚动条高宽及背景 高宽分别对应横竖滚动条的尺寸*/
  ::-webkit-scrollbar {
    width: 5px; /*滚动条宽度*/
    height: 5px; /*滚动条高度*/
    /* background-color: white; */
    background-color: dimgrey;
  }

  /*定义滑块 内阴影+圆角*/
  ::-webkit-scrollbar-thumb {
    border-radius: 50%;
    -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
    background-color: rgba(221, 222, 224); /*滚动条的背景颜色*/
  }
</style>
