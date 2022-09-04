<template>
      <div>
        <el-row>
             <el-col :span="12">
               <div id="main" style="width:500px;height:400px"></div>
             </el-col>
              <el-col :span="12">
                   <div id="pie" style="width:500px;height:400px;margin-top: 10px;"></div>
             </el-col>
        </el-row>
      </div>
</template>

<script>
import request from "@/utils/request";
import * as echarts from 'echarts';

export default {
    name: 'Count',
    data(){
      return{

      }
    },
    mounted(){   /**页面元素渲染之后再触发**/
       //折线图
     var option = {
       title: {
             text: '最近七天的出入库量',
             subtext: '所有仓库',
             left: 'center'
     },
         xAxis: {
         type: 'category',
         data: []
         },
        yAxis: {
              type: 'value'
         },
      series: [
         {
            data: [],
            type: 'line'
         },
         {
            data: [],
            type: 'bar',
            label: {
                  show: true,
                  position: 'inside'
            },
         },
             ]
      };

       var chartDom = document.getElementById('main');
       var myChart = echarts.init(chartDom);

       request.get("/ecahrts/countStock").then(res=>{
        option.xAxis.data =res.data.x;
        option.series[0].data=res.data.y;
        option.series[1].data=res.data.y;
        //数据准备完毕后，setOption
        myChart.setOption(option);
        }
       )


      //饼图
  var chartDomPie = document.getElementById('pie');
  var myChartPie = echarts.init(chartDomPie);

  var  optionPie = {
  title: {
    text: '仓库一级分类统计',
    subtext: '所有仓库',
    left: 'center'
  },
  tooltip: {
    trigger: 'item'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: 'Access From',
      type: 'pie',
      radius: '50%',
      data: [
       
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
    };
    
     
        request.get("/ecahrts/countStockClass").then(res=>{
           optionPie.series[0].data=res.data;

           myChartPie.setOption(optionPie);
        })
    }
}

</script>

<style>

 .block{
   margin: 10px 0;
   position: fixed;
   bottom: 0;
  
 }
</style>