<template>
  <div>
    <el-tabs v-model="activeName" type="card">
      <el-tab-pane label="采购单审批" name="purordercheck"><PurOrderCheck></PurOrderCheck></el-tab-pane>
      <el-tab-pane label="报价单审批" name="purpricecheck"><PurPriceCheck></PurPriceCheck></el-tab-pane>
      <!--      <el-tab-pane label="供应商业务" name="joblevelmana"><JobLevelMana></JobLevelMana></el-tab-pane>-->
    </el-tabs>
  </div>
</template>

<script>
  import PurOrderCheck from '../../components/sys/basic/PurOrderCheck'
  import PurPriceCheck from '../../components/sys/basic/PurPriceCheck'
  // import JobLevelMana from '../../components/sys/basic/JobLevelMana'
  export default {
    name: "CheOrdercheck",
    data(){
      return{
        activeName: 'purordercheck'
      }
    },
    components:{
      PurOrderCheck,
      PurPriceCheck,
      // JobLevelMana,
    }
  }
</script>

<style scoped>

</style>
