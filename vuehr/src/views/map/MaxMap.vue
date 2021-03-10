<template>
  <div class="viewer">
    <vc-viewer @ready="ready">
      <vc-entity :position="position1" :description="description" :model.sync="model1" :label.sync="label1">
        <vc-graphics-label ref="label" :text="description" font="20px sans-serif"></vc-graphics-label>
        <vc-graphics-model ref="model" :uri="uri1"></vc-graphics-model>
      </vc-entity>
    </vc-viewer>
  </div>
</template>

<script>
  export default {
    name:'MaxMap',
    props:['jingdu','weidu'],
    data() {
      return {
        jingdu: this.jingdu,
        weidu: this.weidu,
        description: "经度"+this.jingdu+"纬度"+this.weidu,
        model1: {},
        label1: {},
        position1: { lng: 114.0, lat: 40.0, height: 1.0 },
        uri1: './model.gltf'
      }
    },
    mounted() {
      Promise.all([this.$refs.label.createPromise, this.$refs.model.createPromise]).then((instances) => {
        instances[0].viewer.zoomTo(instances[0].viewer.entities)
      })
    },
    methods: {
      ready(cesiumInstance) {
        const { Cesium, viewer } = cesiumInstance
      }
    }
  }
</script>