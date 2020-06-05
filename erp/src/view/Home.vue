<template>
  <div class="home" style="width: 100%;height: 100%;">
    <div class="baseheader">
      <div class="baseheader_tilte">物料生产管理系统 v1.0</div>
      <div class="baseheader_menu">
        <el-button title="看板" icon="el-icon-monitor" type="text" class="buttonClick" size="normal"
                   @click="goHome"></el-button>
        <el-button title="待审信息" icon="el-icon-bell" type="text" class="buttonClick" size="normal"
                   @click="goChat"></el-button>
        <el-dropdown class="userInfo" @command="commandHandler">
          <div class="touxiang">
                          <span class="el-dropdown-link" style="color: #ffffff;">
                            <img :src="user.userface" alt=""><p>{{user.name}}</p>
                          </span>
          </div>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="userinfo">个人中心</el-dropdown-item>
            <el-dropdown-item command="setting">设置</el-dropdown-item>
            <el-dropdown-item command="logout" divided>注销登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>

    <el-container>
      <div class="baseAside">
        <template>
          <div class="head">
            <img :src="user.userface" alt="">
            <div class="head_userinfo" :data="status">
              <p>{{user.name}}</p>
<!--              <a v-bind:style="{color: status.color}"><i :class="status.icon"></i>{{status.text}}</a>-->
              <a style="color: #5daf34; padding-left: 15px;">{{user.roles[0].nameZh}}</a>
            </div>
          </div>
        </template>
        <el-aside style="width: 230px;height: 100%;color: #ffffff">
          <el-menu
            router unique-opened
            background-color="#222d32"
            text-color="#fff"
            active-text-color="#ffd04b"
          >
            <el-submenu :index="index+''" v-for="(item,index) in routes" v-if="!item.hidden" :key="index">
              <template slot="title">
                <i style="color: #409eff;margin-right: 5px;" :class="item.iconCls"></i>
                <span style="color: lightgray">{{item.name}}</span>
              </template>
              <el-menu-item :index="child.path" v-for="(child,indexj) in item.children" :key="indexj">
                {{child.name}}
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
      </div>

      <el-container>
        <el-header style="text-align: right; font-size: 12px">
          <el-breadcrumb separator-class="el-icon-arrow-right" style="padding: 10px;">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
          </el-breadcrumb>
        </el-header>

        <el-main>
          <router-view v-if="isRouteAlive"/>
          <div v-if="this.$router.currentRoute.path=='/home'">
            <el-row :gutter="20">
              <el-col :span="16">
                <div class="block">

                  <div class="block_menu" v-for="(item, index) in block_menu" :key="index"
                       :style="{background: item.bgcolor}">
                    <div class="block_menu_main">
                      <i :class="item.icon" style="color: white"></i>
                      <div class="block_menu_text">
                        <h4>{{item.title}}</h4>
                      </div>
                    </div>
                    <div class="block_menu_footer">
                      <span>查看更多</span>
                    </div>
                  </div>

                </div>
              </el-col>
              <el-col :span="8">
                <div id="iecharts1" class="idata"></div>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="16">
                <div>
                  <div id="iecharts2" class="idata2"></div>
                </div>
              </el-col>
              <el-col :span="8">
                <div id="iecharts3" style="width: auto;height: 200px;"></div>
              </el-col>
            </el-row>
          </div>

        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<!--
author: yihangjou
date: 2019/12/2
disc: index.vue,router,iconfont,element引入
-->

<script>
  import baseheader from "../components/base-header";
  import baseAside from "../components/base-aside";
  import {getRequest} from "../utils/api";

  export default {
    name: "index",
    components: {},
    data() {
      const item = {};
      return {
        user: JSON.parse(window.sessionStorage.getItem("user")),
        isRouteAlive: true,
        myChart1: null,
        myChart2: null,
        myChart3: null,
        option1: '',
        option2: '',
        option3: '',
        status: [{color: '#5daf34'}, {icon: 'el-icon-sunny'}, {text: '在线'}],
        block_menu: [{
          bgcolor: 'linear-gradient(-90deg, #26cdfd 0%, #638bfe 100%) !important',
          icon: 'el-icon-data-board',
          title: '供应商'
        }, {
          bgcolor: 'linear-gradient(90deg, #ff8b8a 0%, #ff6c88 100%) !important',
          icon: 'el-icon-data-line',
          title: '工位电脑'
        }, {
          bgcolor: 'linear-gradient(-90deg, #25eba3 0%, #09b1e6 100%) !important',
          icon: 'el-icon-data-analysis',
          title: '车间发布'
        }, {
          bgcolor: 'linear-gradient(-90deg, #fa83ec 0%, #737cfe 100%) !important',
          icon: 'el-icon-receiving',
          title: '产线发布'
        }, {
          bgcolor: 'linear-gradient(-90deg, #fea22b 0%, #ffd343 100%) !important',
          icon: 'el-icon-collection',
          title: '生产达成'
        }, {
          bgcolor: 'linear-gradient(-90deg, #af53ff 0%, #b224e1 100%) !important',
          icon: 'el-icon-guide',
          title: '生产效率'
        }, {
          bgcolor: 'linear-gradient(90deg, #0ec28b 0%, #a0e03e 100%) !important',
          icon: 'el-icon-money',
          title: '工位库存'
        }, {
          bgcolor: 'linear-gradient(-90deg, #97c6e6 0%, #6063bd 100%) !important',
          icon: 'el-icon-coffee-cup',
          title: '生产统计'
        }]

      };
    },
    computed: {
      routes() {
        return this.$store.state.routes;
      }
    },
    methods: {
      // devMsg() {
      //   this.$alert('为了确保所有的小伙伴都能看到完整的数据演示，数据库只开放了查询权限和部分字段的更新权限，其他权限都不具备，完整权限的演示需要大家在自己本地部署后，换一个正常的数据库用户后即可查看，这点请大家悉知!', '友情提示', {
      //     confirmButtonText: '确定',
      //     callback: action => {
      //       this.$notify({
      //         title: '重要重要!',
      //         type: 'warning',
      //         message: '小伙伴们需要注意的是，目前只有权限管理模块完工了，因此这个项目中你无法看到所有的功能，除了权限管理相关的模块。权限管理相关的模块主要有两个，分别是 [系统管理->基础信息设置->权限组] 可以管理角色和资源的关系， [系统管理->操作员管理] 可以管理用户和角色的关系。',
      //         duration: 0
      //       });
      //     }
      //   });
      // },
      // loginSuccess() {
      //   this.$message({
      //     showClose: true,
      //     message: '欢迎您使用物料生产管理系统v1.0！',
      //     type: 'success'
      //   });
      // },
      goChat() {
        this.$router.push("/swagger-ui.html");
      },
      goHome() {
        // this.isRouteAlive = false;
        // this.$nextTick(function () {
        //   this.isRouteAlive = true;
        // });
        this.$router.push("/home");
      },
      Status() {
        if (window.sessionStorage.getItem("user")) {
          this.status.color = '#5daf34';
          this.status.icon = 'el-icon-sunny';
          this.status.text = '在线';
        } else {
          this.status.color = '#e30007';
          this.status.icon = 'el-icon-lightning';
          this.status.text = '离线';
        }
      },
      commandHandler(cmd) {
        if (cmd == 'logout') {
          this.$confirm('您想要注销登录, 是否继续?', '提示', {
            confirmButtonText: '确定注销',
            cancelButtonText: '考虑一下',
            type: 'warning'
          }).then(() => {
            getRequest("/logout").then(resp => {
              this.loading = true;
              if (resp) {
                // this.getRequest("/logout");
                window.sessionStorage.removeItem("user");
                this.Status();
                this.$store.commit('initRoutes', []);
                this.$router.replace("/");
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '您撤销了注销登录操作！'
            })
          })
        }
      },
      InitCanvas() {
        // 基于准备好的dom，初始化echarts实例
        let myChart1 = this.$echarts.init(document.getElementById('iecharts1'));
        let myChart2 = this.$echarts.init(document.getElementById('iecharts2'));
        let myChart3 = this.$echarts.init(document.getElementById('iecharts3'));

        // 指定图表的配置项和数据
        let option1 = ({
          title: {
            text: '月度产量趋势图'
          },
          tooltip: {},
          legend: {
            data: ['产量']
          },
          xAxis: {
            data: ["2月", "4月", "6月", "8月", "10月", "12月"]
          },
          yAxis: {
            axisLabel: {
              margin: 2,
            }
          },
          grid: {
            left: 35
          },
          series: [{
            name: '产量',
            type: 'bar',
            data: [5000, 2000, 3600, 1000, 1000, 2000]
          }]
        });
        let option2 = ({
          title: {
            text: '7天产量趋势图'
          },
          xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line'
          }]
        });
        let option3 = ({
          title: {
            text: '良品率',
            left: 'center'
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            data: ['不合格', '合格', ]
          },
          series: [
            {
              name: '良品率',
              type: 'pie',
              radius: '55%',
              center: ['50%', '60%'],
              data: [
                {value: 4.15, name: '不合格',itemStyle: {color: '#34495e'}},
                {value: 95.85, name: '合格',itemStyle:{color: '#27ae60'}},
              ],
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        });

        //定时器
        setInterval(getList, 1000);

        function getList() {
          var res = new Array();
          res = [5000, 2000, 3600, 1000, 1000, 2000];
          for (let i = 0; i < res.length; i++) {
            let j = parseInt(Math.random() * res.length);
            res[i] += j;
          }
          option1.series[0].data = res;
          myChart1.setOption(option1);
        }
        myChart2.setOption(option2);
        myChart3.setOption(option3);

        // 自适应
        window.onresize = function () {
          myChart1.resize();
          myChart2.resize();
          myChart3.resize();
        }
        // 使用刚指定的配置项和数据显示图表。
        // myChart.setOption(option);

        // //建议加上以下这一行代码，不加的效果图如下（当浏览器窗口缩小的时候）。超过了div的界限（红色边框）
        // window.addEventListener('resize',function() {myChart.resize()});
      },
      // InitData() {
      //     this.option1 = ({
      //       title: {
      //         text: '月度产量趋势图'
      //       },
      //       tooltip: {},
      //       legend: {
      //         data: ['产量']
      //       },
      //       xAxis: {
      //         data: ["2月", "4月", "6月", "8月", "10月", "12月"]
      //       },
      //       yAxis: {
      //         axisLabel: {
      //           margin: 2,
      //         }
      //       },
      //       grid: {
      //         left: 35
      //       },
      //       series: [{
      //         name: '产量',
      //         type: 'bar',
      //         data: [5000, 2000, 3600, 1000, 1000, 2000]
      //       }]
      //     });
      //     this.option2 = ({
      //       title: {
      //         text: '7天产量趋势图'
      //       },
      //       xAxis: {
      //         type: 'category',
      //         data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
      //       },
      //       yAxis: {
      //         type: 'value'
      //       },
      //       series: [{
      //         data: [820, 932, 901, 934, 1290, 1330, 1320],
      //         type: 'line'
      //       }]
      //     });
      //     this.option3 = ({
      //       title: {
      //         text: '良品率',
      //         left: 'center'
      //       },
      //       tooltip: {
      //         trigger: 'item',
      //         formatter: '{a} <br/>{b} : {c} ({d}%)'
      //       },
      //       legend: {
      //         orient: 'vertical',
      //         left: 'left',
      //         data: ['不合格', '合格', ]
      //       },
      //       series: [
      //         {
      //           name: '良品率',
      //           type: 'pie',
      //           radius: '55%',
      //           center: ['50%', '60%'],
      //           data: [
      //             {value: 4.15, name: '不合格',itemStyle: {color: '#34495e'}},
      //             {value: 95.85, name: '合格',itemStyle:{color: '#27ae60'}},
      //           ],
      //           emphasis: {
      //             itemStyle: {
      //               shadowBlur: 10,
      //               shadowOffsetX: 0,
      //               shadowColor: 'rgba(0, 0, 0, 0.5)'
      //             }
      //           }
      //         }
      //       ]
      //     });
      // },
      // InitDrow() {
      //   this.myChart1 = this.$echarts.init(document.getElementById('iecharts1'));
      //   this.myChart2 = this.$echarts.init(document.getElementById('iecharts2'));
      //   this.myChart3 = this.$echarts.init(document.getElementById('iecharts3'));
      //
      //   function getList() {
      //     var res = new Array();
      //     res = [5000, 2000, 3600, 1000, 1000, 2000];
      //     for (let i = 0; i < res.length; i++) {
      //       let j = parseInt(Math.random() * res.length);
      //       res[i] += j;
      //     }
      //     this.option1.series[0].data = res;
      //     this.myChart1.setOption(this.option1);
      //   }
      //   this.myChart2.setOption(this.option2);
      //   this.myChart3.setOption(this.option3);
      //
      //   // // 自适应
      //   // window.onresize = function () {
      //   //   this.myChart1.resize();
      //   //   this.myChart2.resize();
      //   //   this.myChart3.resize();
      //   // }
      // }
    },
    mounted() {
      // this.loginSuccess();
      this.InitCanvas();
      // this.InitData();
      // this.InitDrow();
      this.Status();
    },
    watch: {
      // option1: {
      //   handler(newVal, oldVal) {
      //     if (this.myChart1) {
      //       if (newVal) {
      //         this.myChart1.setOption(newVal);
      //       } else {
      //         this.myChart1.setOption(oldVal);
      //       }
      //     } else {
      //       this.InitCanvas();
      //     }
      //   },
      //   deep: true //对象内部属性的监听，关键。
      // }
    },
  }
</script>

<style scoped>
  .home {
    margin: -8px;
    width: 100%;
    height: 100%;
    /* 设置overflow: hidden;可以防止div溢出 */
    overflow: hidden;
    /* 设置position: fixed;可以铺满屏幕 */
    position: fixed;
  }

  .el-header {
    background-color: #b3c0d1;
    color: #333;
    line-height: 40px;
    display: flex;
    align-items: center;
  }

  .el-main {
    padding: 10px 20px;

    /*此处记得修改！！！！*/
    margin-bottom: 100px;
  }

  .baseheader {
    height: 60px;
    width: 100%;
    background: #3c8dbc;
    margin: 0;
    /*position: relative;*/
    padding: 8px 10px;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .baseheader_tilte {
    /*float: left;*/
    /*width: 300px;*/
    padding: 10px;
    text-align: center;
    color: #ffffff;
    font-size: 22px;
  }

  .baseheader_menu {
    /*float: right;*/
    /*padding: 10px;*/
  }

  .userInfo {
    vertical-align: middle;
    cursor: pointer;
    height: 40px;
    margin-right: 30px;
  }

  .buttonClick {
    /*font-size: 22px;*/
    /*color: white;*/
    /*cursor: pointer;*/
    /*display: inline;*/
    /*margin-right: 25px;*/
    /*margin-top: 10px;*/
    margin-right: 12px;
    color: #ffffff;
    font-size: 20px;
  }

  .touxiang {
    text-align: center;
    /*vertical-align: center;*/
    /*margin: 0 auto;*/
    margin-right: 35px;
    cursor: pointer;
    display: inline;
    position: relative;
  }

  .touxiang img {
    width: 30px;
    height: 30px;
    border-radius: 50%;
    clear: both;
  }

  .touxiang p {
    margin: 0 auto;
    clear: both;
  }

  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }

  .head_title {
    font-family: "华文楷体";
    font-size: 24px;
  }

  .baseAside {
    background-color: #222d32;
    height: 100%;
    width: 230px;
    position: relative;
  }

  .head {
    line-height: 60px;
    color: #ffffff;
    margin: 0 auto;
    padding: 10px 10px 10px 20px;
    float: left;
    display: inline;
  }

  .head_userinfo {
    float: left;
    margin: 0;
    padding: 0 5px 0;
    line-height: 1;
    display: inline;
  }

  .head img {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    float: left;
    margin: 0 0 0 25px;
  }

  .head p {
    /*float: left;*/
    margin: 2px 8px 6px;
    padding-left: 10px;
    font-size: 16px;
  }

  .head i {
    margin-right: 5px;
  }

  .head a {
    font-size: 10px;
    cursor: pointer;
  }

  .el-row {
    background: #f0fff0;
    /*display: flex;*/
    /*justify-content: space-between;*/
  }

  .block {
    max-width: 100%;
    display: flex;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    cursor: pointer;
    /*border: 1px red solid;*/
    overflow: hidden;
  }

  .block_menu {
    flex: 0 0 25%;
    max-width: 22.5%;
    margin: .5rem !important;
    height: 150px;
    border-radius: 5px;
    box-shadow: 0px 6px 6px rgba(204, 204, 204, 0.35);
  }

  .block_menu:hover {
    transform: translate(0, -8px);
    transition: all 0.5s linear;
    -moz-transition: all 0.5s linear; /* Firefox 4 */
    -webkit-transition: all 0.5s linear; /* Safari 和 Chrome */
    -o-transition: all 0.5s linear; /* Opera */
  }

  .block_menu_main {
    height: 100px;
    position: relative;
    display: flex;
    flex-direction: row-reverse;
  }

  .block_menu_main i {
    display: block;
    font-size: 40px;
    color: #fff;
    position: absolute;
    right: 20px;
    top: 15px;
  }

  .block_menu_text {
    position: absolute;
    left: 20px;
    bottom: 0px;
    font-weight: bold;
    font-size: 21px;
    color: #fff;
  }

  .block_menu_text h4 {
    margin: 0;
  }

  .block_menu_footer {
    text-align: left;
  }

  .block_menu_footer span {
    display: inline-block;
    padding: 10px 5px 0px 20px;
    font-weight: normal;
    font-size: 14px;
    color: #fff;
    opacity: 0.6;
  }

  .idata {
    width: auto;
    height: 300px;
    /*border: 1px red solid;*/
  }

  .idata2 {
    width: auto;
    height: 200px;
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
