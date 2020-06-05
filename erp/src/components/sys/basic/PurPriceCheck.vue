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
              prop="id"
              fixed
              align="center"
              label="报价单号"
              width="120">
            </el-table-column>
            <el-table-column
              prop="purSuppliers.supplierName"
              label="供应商"
              align="center"
              width="200">
            </el-table-column>
            <el-table-column
              prop="date"
              label="报价日期"
              align="center"
              width="120">
            </el-table-column>
            <el-table-column
              prop="price"
              width="80"
              align="center"
              label="价格(元)">
            </el-table-column>
            <el-table-column
              prop="tax"
              width="120"
              align="center"
              label="税率(百分率)">
            </el-table-column>
            <el-table-column
              prop="acount"
              width="120"
              align="center"
              label="采购总量">
            </el-table-column>
            <el-table-column
              prop="amount"
              width="150"
              align="center"
              label="报价总额(元)">
            </el-table-column>
            <el-table-column
              prop="aboutdate"
              width="120"
              align="center"
              label="预计日期">
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
    name: "PurPriceCheck",
    data() {
      return {
        dialogVisible: false,
        tabPane: [
          {label: '待审核', name: '1', type: 'danger', text: '开始审核'},
          {label: '审核中', name: '2', type: 'success', text: '审核通过'},
          {label: '已审核', name: '3', type: 'primary', text: '已审核'}
        ],
        check: {
          id: "99999999",
          supplierName: "武汉精工制造",
          date: "2020-03-13",
          aboutdate: "2020-03-30",
          price: "5.99",
          tax: "4.39%",
          acount: '5400',
          amount: '50000',
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
            this.putRequest("/pur/price/", data).then(resp => {
              if (resp) {
                this.initCheck();
              }
            });
            break;
          case 2:
            data.state = 3;
            this.putRequest("/pur/price/", data).then(resp => {
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
        let url = '/pur/price/?page=' + this.page + '&size=' + this.size;
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
