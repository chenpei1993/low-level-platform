<template>
  <div style="margin: 0 auto; max-width: 800px;">
      <div style="display: flex; flex-direction: column; align-items: center; justify-content: center; height: 60vh;">
        <img style="margin-bottom: 30px; width: 150px;" src="@/assets/sad.png" />
        <div>{{message}}</div>
      </div>
  </div>

</template>

<script>
import {QuestionFactory} from "./Question/js/QuestionFactory"
import {inject} from "vue";

export default {
  name: "question-content",
  props: {
    message: {
      require: true,
      type: String,
      default() {
        return ""
      }
    }
  }
}
</script>

