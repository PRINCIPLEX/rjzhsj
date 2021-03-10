<template>
  <div class="mapbox">
    <div ref="map" id="map" :style="{width:width,height:height}">
      <!-- 自定义工具栏 -->
      <div class="tool-container">
        <div class="t_close"></div>
        <ul>
          <li @click="liandong">
            <div class="icon" title="定点">
              <img src="./image/icon1.svg">
            </div>
          </li>
          <Popover>
            <template v-slot:button>
              <li>
                <div class="icon" title="测量">
                  <img src="./image/icon2.svg">
                </div>
              </li>
            </template>
            <template v-slot:content>
              <ul class="maplist">
                <li @click="measure('Polygon')">Polygon</li>
                <li @click="measure('LineString')">LineString</li>
              </ul>
            </template>
          </Popover>
          <Popover>
            <template v-slot:button>
              <li>
                <div class="icon" title="切换地图">
                  <img src="./image/icon3.svg">
                </div>
              </li>
            </template>
            <template v-slot:content>
              <ul class="maplist">
                <li @click="changeMap(GAODEADRESS)">高德地图</li>
                <li @click="changeMap(OPENSTREETADRESS)">OpenStreet地图</li>
                <li @click="changeMap(ARCGISADRESS)">ArcGIS地图</li>
              </ul>
            </template>
          </Popover>
          <li @click="clearPic">
            <div class="icon" title="撤销上一次绘图">
              <img src="./image/icon5.svg">
            </div>
          </li>
          <li @click="clearboard">
            <div class="icon" title="清空绘图">
              <img src="./image/icon4.svg">
            </div>
          </li>
          <li @click="fullScreen(false)" v-show="!isFull">
            <div class="icon" title="全屏">
              <img src="./image/icon6.svg">
            </div>
          </li>
          <li @click="fullScreen(true)" v-show='isFull'>
            <div class="icon" title="缩屏">
              <img src="./image/icon7.svg">
            </div>
          </li>
        </ul>
      </div>
    </div>
    <el-dialog
        title="二三维联动"
        :visible.sync="showflag"
        width="80%">
      <p>经度:{{jingdu}}</p>
      <p>纬度:{{weidu}}</p>
      <MaxMap
          :jingdu = jingdu
      :weidu = weidu></MaxMap>
    </el-dialog>
  </div>
</template>

<script>
import OlMap from './map'
import Popover from './Popover'
import Measure from "./Measures";
import {  transform } from 'ol/proj'
import MaxMap from "@/views/map/MaxMap";
// import PmsMapOperator from './map/PmsMapOperator'
// 各个地图对应的code
const GAODEADRESS = 0
const OPENSTREETADRESS = 2
const ARCGISADRESS = 1

export default {
  name: 'OLMap',
  components: {
    MaxMap,
    Popover
  },
  props: {
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '840'
    }
  },
  data () {
    return {
      GAODEADRESS,
      ARCGISADRESS,
      OPENSTREETADRESS,
      isFull: false,
      showflag: false,
      jingdu:'',
      weidu:''
    }
  },
  mounted () {
    this.createMap(this.$refs.map);

  },
  methods: {
    measure(type1){
      Measure.measure(this.map, type1);
    },
    liandong(){
      console.log(this.showflag)
      let _self = this;
      this.map.once('singleclick', function(e){
        console.log(e.coordinate)
        console.log(transform(e.coordinate, 'EPSG:3857', 'EPSG:4326'));
        _self.showflag = true;
        _self.jingdu = e.coordinate[0]
        _self.weidu = e.coordinate[1]
        /*alert(e.coordinate);
        alert(ol.proj.transform(e.coordinate, 'EPSG:3857', 'EPSG:4326'));
        // 通过getEventCoordinate方法获取地理位置，再转换为wgs84坐标，并弹出对话框显示
        alert(map.getEventCoordinate(e.originalEvent));
        alert(ol.proj.transform(map.getEventCoordinate(e.originalEvent), 'EPSG:3857', 'EPSG:4326'));
        var lonlat = map.getCoordinateFromPixel(e.pixel);
        alert(lonlat);
        alert(ol.proj.transform(lonlat,"EPSG:3857", "EPSG:4326")); //由3857坐标系转为4326*/
      })
    },
    createMap (dom) {
      this.map = new OlMap(dom)
    },
    // 改变绘图类型
    changeType (val) {
      this.map.changeDrawType({ type: val })
      // this.map.clearLastDraw()
    },
    // 改变在线地图
    changeMap (mapcode) {
      this.map.changeMap(mapcode)
    },
    // 清除全部绘图
    clearPic () {
      this.map.clearLastDraw()
    },
    clearboard () {
      this.map.clearboard()
    },
    fullScreen (full) {
      let isFull = this.map.fullScreen(this.$refs.map, full)
      if (isFull !== false) {
        this.isFull = !this.isFull
      }
    }
  }
}
</script>
<style scoped>
.hidden1{
  display: none;
}
.mapbox {
  display: flex;
  align-items: center;
  justify-content: center;
}
.logo-large {
  width: 200px;
}
#map {
  height: 400px;
  width: 800px;
  position: relative;
}
.tool-container {
  position: absolute;
  right: 4px;
  top: 4px;
  height: 30px;
  border-radius: 4px;
  z-index: 10;
  font-size: 14px;
  display: flex;
}
.tool-container ul {
  display: flex;
}
.tool-container ul li {
  width: 30px;
  height: 30px;
  display: flex;
  justify-content: center;
  border-radius: 50%;
  background: white;
  cursor: pointer;
  margin-left: 7px;
  box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.6);
}
.icon {
  width: 20px;
  height: 100%;
  display: flex;
  align-items: center;
}
.icon img {
  width: 100%;
  height: auto;
}
</style>
