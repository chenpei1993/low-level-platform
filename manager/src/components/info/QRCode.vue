<template>
  <div>
    <div style="display: flex; justify-content: center;">
        <canvas id="qrcode"></canvas>
    </div>
    <div style="margin-top: 20px; color: #303133;">
        <span style="margin-right: 10px">网页地址：</span>
        <span>{{info.url}}</span>
    </div>
  </div>
</template> 

<script>

import QRCode from 'qrcode'

export default {
  name: "info-qr-code",
  props: {
    info: {
      require: true,
      type: Object,
      default () {
        return {
            autoSend: false,
            repeatCollectType: -1,
            repeatValue: "",
            isFixedTimeSend: true,
            sendDateTime: "",
            beginHours: 0,
            beginMinutes: 0,
            time: 0,
            url: "",
        }
      }
    },
  },
  data(){
      return {
          qrcode: null
      }
  },
  methods:{
      createQRCode(){
          let dom = document.getElementById("qrcode")
          QRCode.toCanvas(dom, "http://" + this.info.url, (e) => {
              console.log(e)
          })
      }
  },
  mounted() {
      this.createQRCode()
  }
}
</script>

<style scoped>

</style>