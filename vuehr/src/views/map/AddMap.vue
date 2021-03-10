<template>
  <div ref="map-root" style="width: 100%; height: 840px"></div>
</template>
<script>

import 'ol/ol.css'
import {Map, View} from 'ol'
import TileLayer from 'ol/layer/Tile'
import OSM from 'ol/source/OSM'
import XYZ from 'ol/source/XYZ'
import ScaleLine from 'ol/control/ScaleLine'
import Measures from "@/views/map/Measures";

export default {
  name: "AddMap",
  methods: {
    initMap(data) {
      new Map({
        target: this.$refs['map-root'],
        layers: [
          new TileLayer({
            source: new XYZ({
              url: 'http://wprd0{1-4}.is.autonavi.com/appmaptile?lang=zh_cn&size=1&style=7&x={x}&y={y}&z={z}'
            })
          })
        ],
        view: new View({
          zoom: 15,
          minZoom: 10,
          maxZoom: 16,
          projection: "EPSG:4326",
          center: [116.243988, 40.207504]
        })
      })
    }
  },
  mounted() {
    this.initMap()
    Measures.measure(this.map, type);
  }
}
</script>

<style scoped>

</style>