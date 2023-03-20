(function(){"use strict";var e={4357:function(e,t,s){var n=s(6369),o=function(){var e=this,t=e._self._c;return t("div",{attrs:{id:"app"}},[t("chat",{attrs:{msg:"Welcome to Your Vue.js App"}})],1)},r=[],i=function(){var e=this,t=e._self._c;return t("div",{staticClass:"chat-container"},[t("div",{staticClass:"chat-messages"},e._l(e.messages,(function(s){return t("div",{key:s.id,staticClass:"chat-message",class:{"user-message":s.isUser,"robot-message":!s.isUser}},[t("div",{staticClass:"message-text"},[e._v(e._s(s.text))])])})),0),t("div",{staticClass:"input-box"},[t("input",{directives:[{name:"model",rawName:"v-model",value:e.message,expression:"message"}],attrs:{type:"text",placeholder:"请输入您的消息..."},domProps:{value:e.message},on:{input:function(t){t.target.composing||(e.message=t.target.value)}}}),t("button",{on:{click:e.sendMessage}},[e._v("发送")])])])},a=[],u=(s(7658),s(6964));let c=null;function l(e){return c||(c=(0,u.j)(`ws://localhost:8080/chat?name=${e}`)),c.onopen=function(e){console.log},c}function f(e,t){const s=l(t);s.subscribe((t=>e(t)),(e=>console.error(e)),(()=>console.log("WebSocket closed")))}function p(e){return{toUser:{ip:"konb",name:"konb"},type:0,mode:1,message:e}}var g={data(){return{message:"",reply:null,messages:[]}},mounted(){f(this.onMessageReceived,"konb")},methods:{sendMessage(){const e=l();if(this.reply)console.log("gaf",this.reply),this.messages.push({id:this.messages.length+1+"",isUser:!0,text:this.reply.message}),e.next(this.reply);else{let t=p(this.message);this.messages.push({id:this.messages.length+1+"",isUser:!0,text:t.message}),e.next(t)}},onMessageReceived(e){console.log("sad",e),this.messages.push({id:e.id,isUser:!1,text:e.message}),this.reply=e}}},h=g,d=s(1001),m=(0,d.Z)(h,i,a,!1,null,null,null),v=m.exports,b={name:"App",components:{chat:v}},y=b,x=(0,d.Z)(y,o,r,!1,null,null,null),_=x.exports;n.ZP.config.productionTip=!1,new n.ZP({render:e=>e(_)}).$mount("#app")}},t={};function s(n){var o=t[n];if(void 0!==o)return o.exports;var r=t[n]={exports:{}};return e[n](r,r.exports,s),r.exports}s.m=e,function(){var e=[];s.O=function(t,n,o,r){if(!n){var i=1/0;for(l=0;l<e.length;l++){n=e[l][0],o=e[l][1],r=e[l][2];for(var a=!0,u=0;u<n.length;u++)(!1&r||i>=r)&&Object.keys(s.O).every((function(e){return s.O[e](n[u])}))?n.splice(u--,1):(a=!1,r<i&&(i=r));if(a){e.splice(l--,1);var c=o();void 0!==c&&(t=c)}}return t}r=r||0;for(var l=e.length;l>0&&e[l-1][2]>r;l--)e[l]=e[l-1];e[l]=[n,o,r]}}(),function(){s.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return s.d(t,{a:t}),t}}(),function(){s.d=function(e,t){for(var n in t)s.o(t,n)&&!s.o(e,n)&&Object.defineProperty(e,n,{enumerable:!0,get:t[n]})}}(),function(){s.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){s.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)}}(),function(){var e={143:0};s.O.j=function(t){return 0===e[t]};var t=function(t,n){var o,r,i=n[0],a=n[1],u=n[2],c=0;if(i.some((function(t){return 0!==e[t]}))){for(o in a)s.o(a,o)&&(s.m[o]=a[o]);if(u)var l=u(s)}for(t&&t(n);c<i.length;c++)r=i[c],s.o(e,r)&&e[r]&&e[r][0](),e[r]=0;return s.O(l)},n=self["webpackChunkchat_vue"]=self["webpackChunkchat_vue"]||[];n.forEach(t.bind(null,0)),n.push=t.bind(null,n.push.bind(n))}();var n=s.O(void 0,[998],(function(){return s(4357)}));n=s.O(n)})();
//# sourceMappingURL=app.08fedce5.js.map