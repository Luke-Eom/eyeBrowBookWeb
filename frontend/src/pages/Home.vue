<template>
 <div class="Home">

    <carousel :autoplay="true" :nav="false" :dots="false" class="marginTop50">
      <div> 로고 </div>
      <div> 눈썹 종류 </div>
      <div> 주의 사항 </div>
      <div> 연락처 </div>
    </carousel>
    <carousel :autoplay="true" :nav="false" :dots="false" class="marginTop50">
      <img src="https://placeimg.com/200/200/any?1">
      <img src="https://placeimg.com/200/200/any?2">
      <img src="https://placeimg.com/200/200/any?3">
      <img src="https://placeimg.com/200/200/any?4">
    </carousel>

      <section class="py-1 text-center container" >
        <div class="row py-lg-3">
          <div class="col-lg-6 col-md-8 mx-auto">
            <span class="img" :style="{backgroundImage: `url(img/logo.jpg)`}" />
          </div>
        </div>
      </section>

        <div class="container" >

          <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <div class="col" v-for="(item, idx) in state.items" :key="idx">
              <Card :item="item"/>
            </div>
          </div>
        </div>
      </div>

</template>

<script>
import Card from "@/components/Card";
import axios from "axios";
import { reactive } from "vue";

export default {
  name: 'Home',
  components: {Card},
  setup() {
    const state = reactive({
        items: []
    })
    axios.get("/eyes/api/cats").then(({data}) => {
      console.log(data);
      state.items = data;
      })
    return {state};
  }
}
</script>

<style scoped>
.img {
    display: inline-block;
    width: 100%;
    height: 250px;
    object-fit: cover;
    background: no-repeat;
    background-size: contain;
    background-position: center;
}
</style>