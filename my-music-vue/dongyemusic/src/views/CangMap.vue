<template>
   <div>
    <div id="container" style="width:100%;height:calc(100vh - 120px)"></div>
    <div id="tip">请拖拽路径试试</div>
   </div>
</template>

<script>
    var content = [
    "<div style='font-size: 14px; color: blue;width:150px;height:50px'>这是信息窗口</div>"
      ];

export default {
 name: 'CangMap',
    data(){
      return{}
      },
      created(){

      }, 
      mounted(){
       //创建地图示例
        var map = new AMap.Map("container", {
        zoom: 13,
        center: [116.39, 39.9],
        resizeEnable: true, 
        // mapStyle: 'amap://styles/whitesmoke'
        });

        // 创建 infoWindow 实例	
        var infoWindow = new AMap.InfoWindow({
        anchor:'bottom-right',
        content: content.join("<br>")  //传入 dom 对象，或者 html 字符串
        });


        var clickHandler = function(e) {
        console.log('您在[ '+e.lnglat.getLng()+','+e.lnglat.getLat()+' ]的位置点击了地图！');
        // 打开信息窗体
        infoWindow.open(map,map.getCenter());
        };

       
        // 创建一个 Marker 实例：
        var marker = new AMap.Marker({
        position: new AMap.LngLat(116.39, 39.9),   // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
        title: '北京'
        });
        marker.on('click', clickHandler); 
  

        // 将创建的点标记添加到已有的地图实例：
        map.add(marker);

        var path = [];
        path.push([116.303843, 39.983412]);
        path.push([116.321354, 39.896436]);
        path.push([116.407012, 39.992093]);
        //绘制初始路径
        map.plugin("AMap.DragRoute", function() {
    
        var route = new AMap.DragRoute(map, path, AMap.DrivingPolicy.LEAST_FEE); //构造拖拽导航类
        route.search(); //查询导航路径并开启拖拽导航
        })

        var polyLine =new AMap.Polyline({
          path:path,
          strokeWeight:5,
          borderWeight:5,
          strokeStyle:"solid",
          strokeColor:'#428100', //线条颜色
          lineJoin:'round'        //折线拐点连接处样式
        })
        map.add(polyLine)

        
        //构造路线导航类
    //     var driving = new AMap.Driving({
    //     map: map,
    //     panel: "panel"
    //     }); 
    //     // 根据起终点经纬度规划驾车导航路线
    //     driving.search(new AMap.LngLat(116.379028, 39.865042), new AMap.LngLat(116.427281, 39.903719), function(status, result) {
    //     // result 即是对应的驾车导航信息，相关数据结构文档请参考  https://lbs.amap.com/api/javascript-api/reference/route-search#m_DrivingResult
    //     if (status === 'complete') {
    //         log.success('绘制驾车路线完成')
    //     } else {
    //         log.error('获取驾车数据失败：' + result)
    //     }
    // });
      },
      methods:{

      }
}
</script>

    <style type="text/css">
        #panel {
            position: fixed;
            background-color: white;
            max-height: 90%;
            overflow-y: auto;
            top: 10px;
            right: 10px;
            width: 280px;
        }
        #panel .amap-call {
            background-color: #009cf9;
            border-top-left-radius: 4px;
   	        border-top-right-radius: 4px;
        }
        #panel .amap-lib-driving {
	        border-bottom-left-radius: 4px;
   	        border-bottom-right-radius: 4px;
            overflow: hidden;
        }
    </style>