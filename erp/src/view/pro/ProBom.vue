<template>
  <div>
    <div>
      <div style="display: flex;justify-content: space-between">
        <div>
          <el-input placeholder="输入报价单号进行搜索..." prefix-icon="el-icon-search"
                    clearable
                    @clear="initPurs"
                    style="width: 350px;margin-right: 10px" v-model="keyword"
                    @keydown.enter.native="initPurs" :disabled="showAdvanceSearchView"></el-input>
          <el-button icon="el-icon-search" type="primary" @click="initPurs" :disabled="showAdvanceSearchView">
            搜索
          </el-button>
        </div>
        <div>
          <el-button type="success" icon="el-icon-plus" @click="showAddOrder">
            BOM清单设计
          </el-button>
        </div>
      </div>
    </div>
    <div style="margin-top: 10px">
      <el-table
        :data="purprice"
        stripe
        border
        v-loading="loading"
        lazy
        :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
        element-loading-text="努力奔跑中^-^"
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
          prop="states.stateName"
          width="120"
          align="center"
          label="状态">
        </el-table-column>
        <el-table-column
          fixed="right"
          width="120"
          align="center"
          label="操作">
          <template slot-scope="scope">
            <el-button @click="showEditPurView(scope.row)" style="padding: 3px" size="medium">编辑</el-button>
            <el-button @click="deletePur(scope.row)" style="padding: 3px" size="medium" type="danger">删除
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
    <el-dialog
      :title="title"
      :visible.sync="dialogVisible"
      :modal="false"
      width="70%">
      <div>
        <el-form :model="price" :rules="rules" ref="purForm">
          <el-row>
            <el-col :span="6">
              <el-form-item label="物料名称:" prop="supplier">
                <el-select v-model="price.supplier" placeholder="供应商" size="mini"
                           style="width: 150px;">
                  <el-option
                    v-for="item in suppliers"
                    :key="item.id"
                    :label="item.supplierName"
                    :value="item.id">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="物料价格:" prop="price">
                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                          v-model="price.price" placeholder="请输入价格……"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="税率:" prop="tax">
                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                          v-model="price.tax" placeholder="请输入税率……"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="记录日期:" prop="date">
                <el-date-picker
                  v-model="price.date"
                  size="mini"
                  type="date"
                  value-format="yyyy-MM-dd"
                  style="width: 180px;"
                  placeholder="选择报价日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="预计日期:" prop="aboutdate">
                <el-date-picker
                  v-model="price.aboutdate"
                  size="mini"
                  type="date"
                  value-format="yyyy-MM-dd"
                  style="width: 180px;"
                  placeholder="选择预计日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="报价总额:（元）" prop="amount">
                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                          v-model="price.amount" placeholder="请输入报价总额……"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="采购总量:" prop="acount">
                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                          v-model="price.acount" placeholder="请输入采购总量……"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="doAddPur">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "PurPrice",
    data() {
      return {
        title: '',
        importDataBtnText: '导入附件',
        importDataBtnIcon: 'el-icon-upload2',
        importDataDisabled: false,
        showAdvanceSearchView: false,
        loading: false,
        dialogVisible: false,
        total: 0,
        page: 1,
        keyword: '',
        size: 10,
        purprice: [],
        suppliers: [],
        states: [],
        price: {
          id: "99999999",
          supplierName: "武汉精工制造",
          date: "2020-03-13",
          aboutdate: "2020-03-30",
          price: "5.99",
          tax: "4.39%",
          acount: '5400',
          amount: '50000',
          stateName: '已审核',
        },
        rules: {
          supplierName: [{required: true, message: '请输入供应商名称', trigger: 'blur'}],
          yewu: [{required: true, message: '请输入供应商提供的业务', trigger: 'blur'}],
          city: [{required: true, message: '请输入供应商所在城市', trigger: 'blur'}],
          name: [{required: true, message: '请填写负责人名称', trigger: 'blur'}],
          phone: [{required: true, message: '请输入联系方式，如手机号、微信号等', trigger: 'blur'}],
          date: [{required: true, message: '请选择日期', trigger: 'blur'}],
        }
      }
    },
    mounted() {
      this.initPurs();
      this.initData();
    },
    methods: {
      onError(err, file, fileList) {
        this.importDataBtnText = '导入附件';
        this.importDataBtnIcon = 'el-icon-upload2';
        this.importDataDisabled = false;
      },
      onSuccess(response, file, fileList) {
        this.importDataBtnText = '导入附件';
        this.importDataBtnIcon = 'el-icon-upload2';
        this.importDataDisabled = false;
        this.initPurs();
      },
      beforeUpload() {
        this.importDataBtnText = '正在导入';
        this.importDataBtnIcon = 'el-icon-loading';
        this.importDataDisabled = true;
      },
      exportData() {
        window.open('/pur/order/export/', '_parent');
      },
      emptyEmp() {
        this.price = {
          id: "",
          supplierName: "",
          date: "",
          aboutdate: "",
          price: "",
          tax: "",
          acount: '',
          amount: '',
          stateName: '',
        }
      },
      showEditPurView(data) {
        this.title = '编辑BOM清单信息';
        this.price = data;
        this.dialogVisible = true;
      },
      deletePur(data) {
        this.$confirm('此操作将永久删除报价单号为：【' + data.id + '】的信息, 是否继续?', '提示', {
          confirmButtonText: '狠心删除',
          cancelButtonText: '考虑一下',
          type: 'warning'
        }).then(() => {
          this.deleteRequest("/pur/price/" + data.id).then(resp => {
            if (resp) {
              this.initPurs();
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '您刚刚取消了删除操作哟！'
          });
        });
      },
      // getMaxId() {
      //   this.getRequest("/pur/supplier/maxId").then(resp => {
      //     if (resp) {
      //       this.supplier.id = resp.obj;
      //     }
      //   })
      // },
      doAddPur() {
        if (this.price.id) {
          this.$refs['purForm'].validate(valid => {
            if (valid) {
              this.putRequest("/pur/price/", this.price).then(resp => {
                if (resp) {
                  this.dialogVisible = false;
                  this.initPurs();
                }
              })
            }
          });
        } else {
          this.$refs['purForm'].validate(valid => {
            if (valid) {
              this.postRequest("/pur/price/", this.price).then(resp => {
                if (resp) {
                  this.dialogVisible = false;
                  this.initPurs();
                }
              })
            }
          });
        }
      },
      sizeChange(currentSize) {
        this.size = currentSize;
        this.initPurs();
      },
      currentChange(currentPage) {
        this.page = currentPage;
        this.initPurs();
      },
      showAddOrder() {
        this.emptyEmp();
        this.title = 'BOM清单设计';
        // this.getMaxId();
        this.dialogVisible = true;
      },
      initData() {
        if (!window.sessionStorage.getItem("suppliers")) {
          this.getRequest('/pur/order/supplier/').then(resp => {
            if (resp) {
              this.suppliers = resp;
              window.sessionStorage.setItem("suppliers", JSON.stringify(resp));
            }
          })
        } else {
          this.suppliers = JSON.parse(window.sessionStorage.getItem("suppliers"));
        }
        if (!window.sessionStorage.getItem("states")) {
          this.getRequest('/pur/order/state/').then(resp => {
            if (resp) {
              this.states = resp;
              window.sessionStorage.setItem("states", JSON.stringify(resp));
            }
          })
        } else {
          this.states = JSON.parse(window.sessionStorage.getItem("states"));
        }
      },
      initPurs(type) {
        this.loading = true;
        let url = '/pur/price/?page=' + this.page + '&size=' + this.size;
        if (type && type != 'advanced') {
          url += "&id=" + this.keyword;
        }
        this.getRequest(url).then(resp => {
          this.loading = false;
          if (resp) {
            this.purprice = resp.data;
            this.total = resp.total;
          }
        });
      }
    }
  }
</script>

<style>
  /* 可以设置不同的进入和离开动画 */
  /* 设置持续时间和动画函数 */
  .slide-fade-enter-active {
    transition: all .8s ease;
  }

  .slide-fade-leave-active {
    transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }

  .slide-fade-enter, .slide-fade-leave-to
    /* .slide-fade-leave-active for below version 2.1.8 */
  {
    transform: translateX(10px);
    opacity: 0;
  }
</style>
