(this.webpackJsonplogin=this.webpackJsonplogin||[]).push([[0],{139:function(e,a,t){},209:function(e,a,t){"use strict";t.r(a);var r=t(0),s=t.n(r),n=t(28),c=t.n(n),o=t(71),i=(t(120),t(47)),l=t.n(i),u=t(56),j=t(57),m=t.n(j),d=(t(139),t(213)),p=t(215),b=t(111),h=t(218),O=t(219),x=t(7);function f(e,a){if("login"===e){var t=function(){var e=Object(u.a)(l.a.mark((function e(a){return l.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,m.a.post("http://localhost:8081/login?username="+a.username+"&password="+a.password).then((function(){window.location.replace("http://localhost:8081/")}));case 2:case"end":return e.stop()}}),e)})));return function(a){return e.apply(this,arguments)}}();return Object(x.jsxs)(d.a,{name:"normal_login",className:"login-form",initialValues:{remember:!0},onFinish:t,children:[Object(x.jsx)(d.a.Item,{name:"username",rules:[{required:!0,message:"Please input your Username!"}],children:Object(x.jsx)(p.a,{prefix:Object(x.jsx)(h.a,{className:"site-form-item-icon"}),placeholder:"Username"})}),Object(x.jsx)(d.a.Item,{name:"password",rules:[{required:!0,message:"Please input your Password!"}],children:Object(x.jsx)(p.a,{prefix:Object(x.jsx)(O.a,{className:"site-form-item-icon"}),type:"password",placeholder:"Password"})}),Object(x.jsxs)(d.a.Item,{wrapperCol:{offset:8,span:16},children:[Object(x.jsx)(b.a,{type:"primary",htmlType:"submit",className:"login-form-button",children:"\u0412\u043e\u0439\u0442\u0438"}),Object(x.jsx)("br",{}),Object(x.jsx)("br",{}),Object(x.jsx)(b.a,{style:{align:"center"},type:"primary",onClick:a,children:"\u0417\u0430\u0433\u0435\u0441\u0442\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c\u0441\u044f"})]})]})}}var y=t(42),w=t(214),g=t(217);function v(e,a){if("register"===e){var t=w.a.Option,r={labelCol:{xs:{span:24},sm:{span:8}},wrapperCol:{xs:{span:24},sm:{span:16}}},s={wrapperCol:{xs:{span:24,offset:0},sm:{span:16,offset:8}}},n=function(){var e=d.a.useForm(),n=Object(o.a)(e,1)[0],c=function(){var e=Object(u.a)(l.a.mark((function e(t){return l.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:m.a.put("http://localhost:8081/users/save",t).then((function(){a()}));case 1:case"end":return e.stop()}}),e)})));return function(a){return e.apply(this,arguments)}}();return Object(x.jsxs)(d.a,Object(y.a)(Object(y.a)({},r),{},{form:n,name:"register",onFinish:c,scrollToFirstError:!0,children:[Object(x.jsx)(d.a.Item,{name:"username",label:"\u041b\u043e\u0433\u0438\u043d",tooltip:"What do you want others to call you?",rules:[{required:!0,message:"Please input your nickname!",whitespace:!0}],children:Object(x.jsx)(p.a,{})}),Object(x.jsx)(d.a.Item,{name:"accountType",label:"\u0422\u0438\u043f \u0430\u043a\u043a\u0430\u0443\u043d\u0442\u0430",hasFeedback:!0,rules:[{required:!0,message:"Please select your country!"}],children:Object(x.jsxs)(w.a,{placeholder:"\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0442\u0438\u043f \u0430\u043a\u043a\u0430\u0443\u043d\u0442\u0430",children:[Object(x.jsx)(t,{value:"USER",children:"User"}),Object(x.jsx)(t,{value:"SHOP",children:"Partner"})]})}),Object(x.jsx)(d.a.Item,{name:"email",label:"\u041f\u043e\u0447\u0442\u0430",rules:[{type:"email",message:"The input is not valid E-mail!"},{required:!0,message:"Please input your E-mail!"}],children:Object(x.jsx)(p.a,{})}),Object(x.jsx)(d.a.Item,{name:"password",label:"\u041f\u0430\u0440\u043e\u043b\u044c",rules:[{required:!0,message:"Please input your password!"}],hasFeedback:!0,children:Object(x.jsx)(p.a.Password,{})}),Object(x.jsx)(d.a.Item,{name:"confirm",label:"\u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0434\u0438\u0442\u0435 \u043f\u0430\u0440\u043e\u043b\u044c",dependencies:["password"],hasFeedback:!0,rules:[{required:!0,message:"Please confirm your password!"},function(e){var a=e.getFieldValue;return{validator:function(e,t){return t&&a("password")!==t?Promise.reject(new Error("The two passwords that you entered do not match!")):Promise.resolve()}}}],children:Object(x.jsx)(p.a.Password,{})}),Object(x.jsx)(d.a.Item,{name:"phoneNumber",label:"\u041d\u043e\u043c\u0435\u0440 \u0442\u0435\u043b\u0435\u0444\u043e\u043d\u0430",rules:[{required:!0,message:"Please input your phone number!"}],children:Object(x.jsx)(p.a,{style:{width:"100%"}})}),Object(x.jsx)(d.a.Item,{name:"fcs",label:"\u0424\u0418\u041e",tooltip:"What do you want others to call you?",rules:[{required:!0,message:"Please input your nickname!",whitespace:!0}],children:Object(x.jsx)(p.a,{})}),Object(x.jsx)(d.a.Item,{name:"city",label:"\u0413\u043e\u0440\u043e\u0434",tooltip:"What do you want others to call you?",rules:[{required:!0,message:"Please input your nickname!",whitespace:!0}],children:Object(x.jsx)(p.a,{})}),Object(x.jsx)(d.a.Item,{name:"address",label:"\u0410\u0434\u0440\u0435\u0441",tooltip:"What do you want others to call you?",rules:[{required:!0,message:"Please input your nickname!",whitespace:!0}],children:Object(x.jsx)(p.a,{})}),Object(x.jsx)(d.a.Item,Object(y.a)(Object(y.a)({valuePropName:"checked",rules:[{validator:function(e,a){return a?Promise.resolve():Promise.reject(new Error("Should accept agreement"))}}]},s),{},{children:Object(x.jsxs)(g.a,{children:["I have read the ",Object(x.jsx)("a",{href:"",children:"agreement"})]})})),Object(x.jsx)(d.a.Item,Object(y.a)(Object(y.a)({},s),{},{children:Object(x.jsx)(b.a,{type:"primary",htmlType:"submit",children:"Register"})}))]}))};return Object(x.jsx)(n,{})}}function P(){var e=Object(r.useState)("login"),a=Object(o.a)(e,2),t=a[0],s=a[1],n=function(){s("register")};return Object(x.jsxs)("div",{style:{width:600,margin:"auto"},children:[f(t,n),v(t,n)]})}c.a.render(Object(x.jsx)(s.a.StrictMode,{children:Object(x.jsx)(P,{})}),document.getElementById("root"))}},[[209,1,2]]]);
//# sourceMappingURL=main.f2cd69dc.chunk.js.map