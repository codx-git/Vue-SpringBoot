<template>
  <el-container style="min-height: 100vh">

    <el-aside :width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
      <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
               background-color="rgb(48, 65, 86)"
               text-color="#fff"
               active-text-color="#ffd04b"
               :collapse-transition="false"
               :collapse="isCollapse"
      >
        <div style="height: 60px; line-height: 60px; text-align: center">
          <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; right: 5px">
          <b style="color: white" v-show="logoTextShow">后台管理系统</b>
        </div>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-message"></i>
            <span slot="title">导航一</span>
          </template>
          <el-menu-item-group title="分组2">
            <el-menu-item index="1-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <template slot="title">选项4</template>
            <el-menu-item index="1-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span slot="title">导航二</span>
          </template>
          <el-submenu index="2-4">
            <template slot="title">选项4</template>
            <el-menu-item index="2-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-setting"></i>
            <span slot="title">导航三</span>
          </template>
          <el-submenu index="3-4">
            <template slot="title">选项4</template>
            <el-menu-item index="3-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px; display: flex">
        <div style="flex: 1; font-size: 20px">
          <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
        </div>
        <el-dropdown style="width: 70px; cursor: pointer;line-height: 60px;">
          <span>王小虎</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
          <el-dropdown-menu slot="dropdown" >
            <el-dropdown-item style="font-size: 14px; padding: 5px 0">个人信息</el-dropdown-item>
            <el-dropdown-item style="font-size: 14px; padding: 5px 0">退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

      </el-header>

      <el-main>
        <div style="margin-bottom: 30px">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>用户管理</el-breadcrumb-item>
          </el-breadcrumb>
        </div>

        <div style="margin: 10px 0">
          <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="username" class="ml-5"></el-input>
          <el-input style="width: 200px" placeholder="请输入昵称" suffix-icon="el-icon-message" v-model="nickname" class="ml-5"></el-input>
          <el-input style="width: 200px" placeholder="请输入邮箱" suffix-icon="el-icon-message" v-model="email" class="ml-5"></el-input>
          <el-input style="width: 200px" placeholder="请输入地址" suffix-icon="el-icon-position" v-model="address" class="ml-5"></el-input>
          <el-button class="ml-5" type="primary" @click="loadTable">搜索</el-button>
        </div>

        <div style="margin: 10px 0">
          <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
          <el-popconfirm
                  class="ml-5"
                  confirm-button-text='好的'
                  cancel-button-text='不用了'
                  icon="el-icon-info"
                  icon-color="red"
                  title="您确定要批量删除吗？"
                  @confirm="delBatch"
                >
          <el-button type="danger" slot="reference" >批量删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
          <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
          <el-button type="primary" >导出 <i class="el-icon-top"></i></el-button>
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55">
          </el-table-column>
          <el-table-column prop="id" label="ID" width="80">
          </el-table-column>
          <el-table-column prop="username" label="用户" width="140">
          </el-table-column>
          <el-table-column prop="nickname" label="昵称" width="120">
          </el-table-column>
          <el-table-column prop="email" label="邮箱" width="120">
          </el-table-column>
          <el-table-column prop="phone" label="电话" width="120">
          </el-table-column>
          <el-table-column prop="address" label="地址">
          </el-table-column>
          <el-table-column label="操作"  width="200" text-align="center">
            <template slot-scope="scope">
              <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>             
              <el-popconfirm
                  class="ml-5"
                  confirm-button-text='好的'
                  cancel-button-text='不用了'
                  icon="el-icon-info"
                  icon-color="red"
                  title="您确定删除吗？"
                  @confirm="del(scope.row.id)"
                >
                  <el-button type="danger" slot="reference" >删除 <i class="el-icon-remove-outline"></i></el-button>
                </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
        <div style="padding: 10px 0">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[2, 5, 10, 20]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
          </el-pagination>
        </div>

        <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="25%">
          <el-form :model="form">
            <el-form-item label="用户名" :label-width="formLabelWidth">
              <el-input v-model="form.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="昵称" :label-width="formLabelWidth">
              <el-input v-model="form.nickname" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" :label-width="formLabelWidth">
              <el-input v-model="form.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="电话" :label-width="formLabelWidth">
              <el-input v-model="form.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="地址" :label-width="formLabelWidth">
              <el-input v-model="form.address" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </div>
        </el-dialog>

      </el-main>

    </el-container>
  </el-container>
</template>

<script>
import request from '../utils/request'


export default {
  name: 'Home',
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 2,
      username: "",
      nickname: "",
      email:"",
      address: "",
      form:{},
      multipleSelection :[],
      formLabelWidth:"80px",
      dialogFormVisible:false,
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      headerBg: 'headerBg'
    }
  },
  created(){
    this.loadTable()
  },
  methods: {
    collapse() {  // 点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {  // 收缩
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {   // 展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    },
    loadTable(){
    //   fetch("http://localhost:9090/user/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize).then(res => res.json()).then(res=>{
    //   this.tableData = res.records
    //   this.total = res.total
    // })
    request.get("user/page",{
      params:{
        pageSize: this.pageSize,
        pageNum: this.pageNum,
        username: this.username,
        nickname: this.nickname,
        email:this.email,
        address: this.address
      }
    }).then(res =>{
      this.tableData = res.records
      this.total = res.total
    })
    },
    handleSizeChange(pageSize){ //表页面大小更改
      this.pageSize = pageSize
      this.loadTable()
    },
    handleCurrentChange(pageNum){ //跳转指定页数
      this.pageNum = pageNum
      this.loadTable()
    },
    handleAdd(){
      this.dialogFormVisible=true
    },
    handleEdit(row){
      this.dialogFormVisible=true;
      this.form = row
    },
    handleSelectionChange(val){
      this.multipleSelection = val;
    },
    del(id){
      request.delete("user/"+id).then(res =>{
        if(res){
          this.$message.success("删除成功！");
          this.dialogFormVisible = false;
          this.loadTable();
        }
        else{
          this.$message.error("删除失败！");
        }
      })
    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.id);
      request.post("user/del/batch",ids).then(res =>{
        if(res){
          this.$message.success("删除成功！");
          this.dialogFormVisible = false;
          this.loadTable();
        }
        else{
          this.$message.error("删除失败！");
        }
      })
    },
    save(){
      request.post("user",this.form).then(res=>{
        if(res){
          this.$message.success("保存成功！");
          this.dialogFormVisible = false;
          this.loadTable();
        }
        else{
          this.$message.error("保存失败！");
        }
      })
    },
  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>