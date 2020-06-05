<template>
  <div>
    <div>
      <div style="display: flex;justify-content: space-between">
        <div>
          <el-input placeholder="输入供应商名称进行搜索，可以直接回车搜索..." prefix-icon="el-icon-search"
                    clearable
                    @clear="initPurs"
                    style="width: 350px;margin-right: 10px" v-model="keyword"
                    @keydown.enter.native="initPurs" :disabled="showAdvanceSearchView"></el-input>
          <el-button icon="el-icon-search" type="primary" @click="initPurs" :disabled="showAdvanceSearchView">
            搜索
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
            添加供应商
          </el-button>
        </div>
      </div>
    </div>
    <div style="margin-top: 10px">
      <el-table
        :data="pursupplier"
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
          prop="id"
          fixed
          align="center"
          label="序号"
          width="60">
        </el-table-column>
        <el-table-column
          prop="supplierName"
          label="供应商"
          align="center"
          width="200">
        </el-table-column>
        <el-table-column
          prop="yewu"
          label="从事业务"
          align="center"
          width="200">
        </el-table-column>
        <el-table-column
          prop="city"
          width="80"
          align="center"
          label="所在城市">
        </el-table-column>
        <el-table-column
          prop="name"
          width="80"
          align="center"
          label="负责人">
        </el-table-column>
        <el-table-column
          prop="phone"
          width="120"
          align="center"
          label="联系方式">
        </el-table-column>
        <el-table-column
          prop="date"
          width="120"
          align="center"
          label="日期">
        </el-table-column>
        <el-table-column
          prop="others"
          width="150"
          align="center"
          label="备注">
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
        <el-form :model="supplier" :rules="rules" ref="purForm">
          <el-row>
            <el-col :span="6">
              <el-form-item label="供应商:" prop="supplierName">
                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                          v-model="supplier.supplierName" placeholder="请输入供应商……"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="所在城市:" prop="city">
                <el-input size="mini" style="width: 130px" prefix-icon="el-icon-edit"
                          v-model="supplier.city" placeholder="请输入所在城市……"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="从事业务:" prop="yewu">
                <el-input size="mini" style="width: 160px" prefix-icon="el-icon-edit"
                          v-model="supplier.yewu" placeholder="所涵盖的业务……"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-form-item label="负责人:" prop="name">
                <el-input size="mini" style="width: 130px" prefix-icon="el-icon-edit"
                          v-model="supplier.name" placeholder="请输入相关负责人……"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="联系方式:" prop="phone">
                <el-input size="mini" style="width: 130px" prefix-icon="el-icon-edit"
                          v-model="supplier.phone" placeholder="请输入联系方式……"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="登记日期:" prop="date">
                <el-date-picker
                  v-model="supplier.date"
                  size="mini"
                  type="date"
                  value-format="yyyy-MM-dd"
                  style="width: 180px;"
                  placeholder="信息登记日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="备注:" prop="num">
                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                          v-model="supplier.others" placeholder="添加备注……"></el-input>
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
    name: "PurSupplier",
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
        pursupplier: [],
        supplier: {
          id: "99999999",
          supplierName: "武汉精工制造",
          yewu: "黄铜、生铁等",
          city: "武汉",
          name: "周逸航",
          phone: "13429905127",
          date: '2020-03-21',
          others: '暂无',
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
        this.supplier = {
          id: "",
          supplierName: "",
          yewu: "",
          city: "",
          name: "",
          phone: "",
          date: '',
          others: '',
        }
      },
      showEditPurView(data) {
        this.title = '编辑供应商信息';
        this.supplier = data;
        this.dialogVisible = true;
      },
      deletePur(data) {
        this.$confirm('此操作将永久删除【' + data.supplierName + '】的信息, 是否继续?', '提示', {
          confirmButtonText: '狠心删除',
          cancelButtonText: '考虑一下',
          type: 'warning'
        }).then(() => {
          this.deleteRequest("/pur/supplier/" + data.id).then(resp => {
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
        if (this.supplier.id) {
          this.$refs['purForm'].validate(valid => {
            if (valid) {
              this.putRequest("/pur/supplier/", this.supplier).then(resp => {
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
              this.postRequest("/pur/supplier/", this.supplier).then(resp => {
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
        this.title = '添加供应商';
        // this.getMaxId();
        this.dialogVisible = true;
      },
      initPurs(type) {
        this.loading = true;
        let url = '/pur/supplier/?page=' + this.page + '&size=' + this.size;
        if (type && type != 'advanced') {
          url += "&supplierName=" + this.keyword;
        }
        this.getRequest(url).then(resp => {
          this.loading = false;
          if (resp) {
            this.pursupplier = resp.data;
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
