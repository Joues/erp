<template>
  <div>
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane v-for="(item, index) in tabPane" :label="item.label" :name="item.name" :key="index">
        <div style="margin-top: 10px">
          <el-table
            :data="check"
            stripe
            border
            v-loading="loading"
            element-loading-text="我拼了命的努力，只为展现更好的自己！"
            element-loading-spinner="el-icon-loading"
            element-loading-background="rgba(0, 0, 0, 0.8)"
            style="width: 100%">
            <el-table-column
              type="selection"
              align="center"
              width="40">
            </el-table-column>
            <el-table-column
              prop="oid"
              fixed
              align="center"
              label="订单号"
              width="95">
            </el-table-column>
            <el-table-column
              prop="wname"
              label="物料名"
              align="center"
              width="100">
            </el-table-column>
            <el-table-column
              prop="types.typeName"
              width="95"
              align="center"
              label="类别">
            </el-table-column>
            <el-table-column
              prop="purSuppliers.supplierName"
              width="200"
              align="center"
              label="供应商">
            </el-table-column>
            <el-table-column
              prop="price"
              width="80"
              align="center"
              label="价格">
            </el-table-column>
            <el-table-column
              prop="num"
              width="80"
              align="center"
              label="数量">
            </el-table-column>
            <el-table-column
              prop="unit"
              width="80"
              align="center"
              label="单位">
            </el-table-column>
            <el-table-column
              prop="date"
              width="160"
              align="center"
              label="下单时间">
            </el-table-column>
            <el-table-column
              fixed="right"
              width="160"
              align="center"
              label="操作">
              <template slot-scope="scope">
                <el-button @click="editState(scope.row)" style="padding: 3px" :type="item.type" size="middle">
                  {{item.text}}
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: flex-end">
            <el-pagination
              background
              @current-change="currentChange"
              @size-change="sizeChange"
              layout="sizes, prev, pager, next, jumper, ->, total, slot"
              :total="total">
            </el-pagination>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  export default {
    name: "PurOrderCheck",
    data() {
      return {
        dialogVisible: false,
        tabPane: [
          {label: '待审核', name: '1', type: 'danger', text: '开始审核'},
          {label: '审核中', name: '2', type: 'success', text: '审核通过'},
          {label: '已审核', name: '3', type: 'primary', text: '已审核'}
        ],
        uncheck: {
          id: "99999999",
          oid: "99999999",
          wname: "冲压气缸974-I",
          guige: "其他",
          unit: "件",
          supplier: "武汉精工制造",
          store: '汉南1号仓',
          price: 5,
          num: 3200,
          state: '已审核',
          date: '2020-03-13',
        },
        check: [],
        activeName: '1',
        loading: false,
        total: 0,
        page: 1,
        size: 10,
        label: '1',
      }
    },
    watch: {},
    mounted() {
      this.initCheck();
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
        this.label = tab.name;
        this.initCheck();
      },
      editState(data) {
        switch (data.state) {
          case 1:
            data.state = 2;
            this.putRequest("/pur/order/", data).then(resp => {
              if (resp) {
                this.initCheck();
              }
            });
            break;
          case 2:
            data.state = 3;
            this.putRequest("/pur/order/", data).then(resp => {
              if (resp) {
                this.initCheck();
              }
            });
            break;
          default:
            this.$message({
              message: '此功能正在开发中，尽请期待！欢迎关注本人个人站点建设：www.yihang.ml 。',
              type: 'success'
            });
        }
      },
      sizeChange(currentSize) {
        this.size = currentSize;
        this.initCheck();
      },
      currentChange(currentPage) {
        this.page = currentPage;
        this.initCheck();
      },
      initCheck() {
        this.loading = true;
        let url = '/pur/order/?page=' + this.page + '&size=' + this.size;
        if (this.label) {
          url += '&state=' + this.label;
        }
        this.getRequest(url).then(resp => {
          this.loading = false;
          if (resp) {
            this.check = resp.data;
            this.total = resp.total;
          }
        });
      },
      filterNode(value, data) {
        if (!value) return true;
        return data.name.indexOf(value) !== -1;
      }
    }
  }
</script>

<style>
  .depBtn {
    padding: 2px;
  }
</style>
