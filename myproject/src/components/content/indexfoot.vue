<template>
  <div class="foot">
    <!-- Start of Footer -->
    <footer id="footer-wrapper">
      <div id="footer" class="container">
        <div class="row">
          <div class="span3">
            <section class="widget">
              <h3 class="title">网站主要功能</h3>
              <div class="textwidget">
                <p>“跳蚤校园”是西南石油大学信息发布二手物品信息的平台，用户通过注册账号后就可以发布商品信息和评论，你可以在这里进行交易。</p>
              </div>
              <h4 class="title">公告</h4>
              <div class="textwidget">
                <p>禁止在本平台上发表不当言论 禁止出现辱骂他人的语言，一经发现进行封号处理</p>
              </div>
            </section>
          </div>

          <div class="span3">
            <section class="widget">
              <h4 class="title">友情链接</h4>
              <ul>
                <li>
                  <a href="#" title="Lorem ipsum dolor sit amet,">西南石油大学官网</a>
                </li>
                <li>
                  <a href="#" title="Lorem ipsum dolor sit amet,">西南石油大学教务处</a>
                </li>
                <li>
                  <a
                    href="http://www.guoang.xyz/"
                    target="“_blank”"
                    title="Lorem ipsum dolor sit amet,"
                  >个人博客</a>
                </li>
             
              </ul>
            </section>
          </div>

          <div class="span3">
            <section class="widget">
              <h4 class="title">广告信息</h4>
              <div id="twitter_update_list">
                <ul>
                  <li>No </li>
                </ul>
              </div>
            </section>
          </div>

          <div class="span3">
            <section class="widget">
              <h3 class="title">客服中心</h3>
             <ul>
                <li>
                  <router-link to="/fankui" title="Lorem ipsum dolor sit amet,">意见反馈</router-link>
                </li>
                <li>
                  <router-link to="/shensu" title="Lorem ipsum dolor sit amet,">账号解封</router-link>
                </li>
                 <li>
                  <router-link to="/jubao" title="Lorem ipsum dolor sit amet,">不良行为举报</router-link>
                </li>
                
              </ul>
            </section>
          </div>
        </div>
      </div>
      <!-- end of #footer -->

      <!-- Footer Bottom -->
      <div id="footer-bottom-wrapper">
        <div id="footer-bottom" class="container">
        
        </div>
      </div>
      <!-- End of Footer Bottom -->
    </footer>
    <!-- End of Footer -->
  </div>
</template>

<script>
export default {
  name: "foot",
  props: {
    msg: String
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>