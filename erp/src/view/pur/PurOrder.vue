<template>
  <div>
    <div>
      <div style="display: flex;justify-content: space-between">
        <div>
          <el-input placeholder="输入物料名称进行搜索，可以直接回车搜索..." prefix-icon="el-icon-search"
                    clearable
                    @clear="initPurs"
                    style="width: 350px;margin-right: 10px" v-model="keyword"
                    @keydown.enter.native="initPurs" :disabled="showAdvanceSearchView"></el-input>
          <el-button icon="el-icon-search" type="primary" @click="initPurs" :disabled="showAdvanceSearchView">
            搜索
          </el-button>
          <el-button type="primary" @click="showAdvanceSearchView = !showAdvanceSearchView">
            <i :class="showAdvanceSearchView?'fa fa-angle-double-up':'fa fa-angle-double-down'"
               aria-hidden="true"></i>
            高级搜索
          </el-button>
        </div>
        <div>
          <el-upload
            :show-file-list="false"
            :before-upload="beforeUpload"
            :on-success="onSuccess"
            :on-error="onError"
            :disabled="importDataDisabled"
            style="display: inline-flex;margin-right: 8px"
            action="/pur/order/import/">
            <el-button :disabled="importDataDisabled" type="success" :icon="importDataBtnIcon">
              {{importDataBtnText}}
            </el-button>
          </el-upload>
          <el-button type="success" @click="exportData" icon="el-icon-download">
            导出数据
          </el-button>
          <el-button type="primary" icon="el-icon-plus" @click="showAddOrder">
            添加订单
          </el-button>
        </div>
      </div>
      <transition name="slide-fade">
        <div v-show="showAdvanceSearchView"
             style="border: 1px solid #409eff;border-radius: 5px;box-sizing: border-box;padding: 5px;margin: 10px 0px;">
          <el-row>
            <el-col :span="6">
              供应商：
              <el-select v-model="searchValue.supplier" placeholder="供应商" size="mini"
                         style="width: 130px;">
                <el-option
                  v-for="item in suppliers"
                  :key="item.id"
                  :label="item.supplierName"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="5">
              仓库：
              <el-select v-model="searchValue.store" placeholder="仓库" size="mini"
                         style="width: 130px;">
                <el-option
                  v-for="item in stores"
                  :key="item.id"
                  :label="item.storeName"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="8">
              状态：
              <el-radio-group v-model="searchValue.state">
                <el-radio v-for="item in states" :key="item.id" :label="item.id">{{item.stateName}}</el-radio>
              </el-radio-group>
            </el-col>
          </el-row>
          <el-row style="margin-top: 10px">
            <el-col :span="6">
              类型：
              <el-select v-model="searchValue.type" placeholder="类型" size="mini"
                         style="width: 130px;">
                <el-option
                  v-for="item in types"
                  :key="item.id"
                  :label="item.typeName"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="9">
              日期:
              <el-date-picker
                v-model="searchValue.dateScope"
                type="daterange"
                size="mini"
                unlink-panels
                value-format="yyyy-MM-dd"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期">
              </el-date-picker>
            </el-col>
            <el-col :span="4" :offset="2">
              <el-button size="mini" @click="showAdvanceSearchView = !showAdvanceSearchView">取消</el-button>
              <el-button size="mini" icon="el-icon-search" type="primary" @click="initPurs('advanced')">搜索</el-button>
            </el-col>
          </el-row>
        </div>
      </transition>
    </div>
    <div style="margin-top: 10px">
      <el-table
        :data="purorder"
        stripe
        border
        v-loading="loading"
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
          prop="guige"
          label="规格"
          align="center"
          width="85">
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
          prop="stores.storeName"
          width="150"
          align="center"
          label="仓库">
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
          prop="states.stateName"
          width="90"
          align="center"
          label="状态">
        </el-table-column>
        <el-table-column
          fixed="right"
          width="195"
          align="center"
          label="操作">
          <template slot-scope="scope">
            <el-button @click="showEditPurView(scope.row)" style="padding: 3px" size="mini">编辑</el-button>
            <el-button style="padding: 3px" size="mini" type="primary">查看高级资料</el-button>
            <el-button @click="deletePur(scope.row)" style="padding: 3px" size="mini" type="danger">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="display: flex;justify-content: flex-end">
        <el-button type="danger" size="small" style="margin-top: 10px"
                   @click="">批量删除
        </el-button>
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
        <el-form :model="order" :rules="rules" ref="purForm">
          <el-row>
            <el-col :span="6">
              <el-form-item label="订单号:" prop="oid">
                <el-input size="mini" style="width: 120px" prefix-icon="el-icon-edit"
                          v-model="order.oid" placeholder="工号" disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="物料名:" prop="wname">
                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                          v-model="order.wname" placeholder="请输入物料名……"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="规格:" prop="guige">
                <el-input size="mini" style="width: 160px" prefix-icon="el-icon-edit"
                          v-model="order.guige" placeholder="请输入规格名……"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="单位:" prop="unit">
                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                          v-model="order.unit" placeholder="请输入物料单位……"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-form-item label="类别:" prop="type">
                <el-select v-model="order.type" placeholder="类别" size="mini"
                           style="width: 150px;">
                  <el-option
                    v-for="item in types"
                    :key="item.id"
                    :label="item.typeName"
                    :value="item.id">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="供应商:" prop="supplier">
                <el-select v-model="order.supplier" placeholder="供应商" size="mini"
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
              <el-form-item label="仓库:" prop="store">
                <el-select v-model="order.store" placeholder="仓库" size="mini"
                           style="width: 150px;">
                  <el-option
                    v-for="item in stores"
                    :key="item.id"
                    :label="item.storeName"
                    :value="item.id">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="价格:" prop="price">
                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                          v-model="order.price" placeholder="请输入物料单价……"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-form-item label="数量:" prop="num">
                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                          v-model="order.num" placeholder="请输入物料采购数量……"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="下单时间:" prop="date">
                <el-date-picker
                  v-model="order.date"
                  size="mini"
                  type="date"
                  value-format="yyyy-MM-dd"
                  style="width: 180px;"
                  placeholder="订单创建下单时间">
                </el-date-picker>
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
    name: "PurOrder",
    data() {
      return {
        searchValue: {
          type: null,
          store: null,
          supplier: null,
          state: null,
          dateScope: null,
        },
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
        purorder: [],
        suppliers: [],
        types: [],
        states: [],
        stores: [],
        order: {
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
        rules: {
          oid: [{required: true, message: '请输入物料编号', trigger: 'blur'}],
          wname: [{required: true, message: '请输入物料名称', trigger: 'blur'}],
          guige: [{required: true, message: '请选择物料规格', trigger: 'blur'}],
          unit: [{required: true, message: '请选择物料单位', trigger: 'blur'}],
          type: [{required: true, message: '请选择物料类型', trigger: 'blur'}],
          supplier: [{required: true, message: '请输入供应商名称', trigger: 'blur'}],
          store: [{required: true, message: '请选择仓库', trigger: 'blur'}],
          price: [{required: true, message: '请输入物料单价', trigger: 'blur'}],
          num: [{required: true, message: '请输入采购数量', trigger: 'blur'}],
          date: [{required: true, message: '请选择订单创建下单时间', trigger: 'blur'}],
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
        this.order = {
          id: "",
          oid: "",
          wname: "",
          guige: "",
          unit: "",
          type: "",
          supplier: "",
          store: "",
          price: "",
          num: "",
          state: "",
          date: "",
        }
      },
      showEditPurView(data) {
        this.title = '编辑订单信息';
        this.order = data;
        this.dialogVisible = true;
      },
      deletePur(data) {
        this.$confirm('此操作将永久删除订单号为【' + data.oid + '】的订单, 是否继续?', '提示', {
          confirmButtonText: '狠心删除',
          cancelButtonText: '考虑一下',
          type: 'warning'
        }).then(() => {
          this.deleteRequest("/pur/order/" + data.id).then(resp => {
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
      getMaxOID() {
        this.getRequest("/pur/order/maxoid").then(resp => {
          if (resp) {
            this.order.oid = resp.obj;
          }
        })
      },
      doAddPur() {
        if (this.order.id) {
          this.$refs['purForm'].validate(valid => {
            if (valid) {
              this.putRequest("/pur/order/", this.order).then(resp => {
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
              this.postRequest("/pur/order/", this.order).then(resp => {
                if (resp) {
                  this.dialogVisible = false;
                  this.initPurs();
                }
              })
            }
          });
        }
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
        if (!window.sessionStorage.getItem("stores")) {
          this.getRequest('/pur/order/store/').then(resp => {
            if (resp) {
              this.stores = resp;
              window.sessionStorage.setItem("stores", JSON.stringify(resp));
            }
          })
        } else {
          this.stores = JSON.parse(window.sessionStorage.getItem("stores"));
        }
        if (!window.sessionStorage.getItem("types")) {
          this.getRequest('/pur/order/type/').then(resp => {
            if (resp) {
              this.types = resp;
              window.sessionStorage.setItem("types", JSON.stringify(resp));
            }
          })
        } else {
          this.types = JSON.parse(window.sessionStorage.getItem("types"));
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
        this.title = '添加订单';
        this.getMaxOID();
        this.dialogVisible = true;
      },
      initPurs(type) {
        this.loading = true;
        let url = '/pur/order/?page=' + this.page + '&size=' + this.size;
        if (type && type == 'advanced') {
          if (this.searchValue.supplier) {
            url += '&supplier=' + this.searchValue.supplier;
          }
          if (this.searchValue.store) {
            url += '&store=' + this.searchValue.store;
          }
          if (this.searchValue.type) {
            url += '&type=' + this.searchValue.type;
          }
          if (this.searchValue.state) {
            url += '&state=' + this.searchValue.state;
          }
          if (this.searchValue.dateScope) {
            url += '&dateScope=' + this.searchValue.dateScope;
          }
        } else {
          url += "&wname=" + this.keyword;
        }
        this.getRequest(url).then(resp => {
          this.loading = false;
          if (resp) {
            // let purorder = [];
            // for (let i in resp.data) {
            //   purorder.push(resp.data[i]);
            // }
            // this.purorder = purorder;
            this.purorder = resp.data;
            // this.total = parseInt(resp.total);
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
