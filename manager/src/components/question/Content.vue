<template>
  <div class="wrapper">
    {{ info }}
  </div>
</template>

<script>
export default {
  name: "question-content",
  props: {
    info: {
      require: true,
      type: Object,
      default() {
        return {}
      }
    }
  },
  methods:{

  }
}
</script>