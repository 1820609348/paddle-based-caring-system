import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from '../components/login/LoginPage.vue'
import App from "@/App.vue";
import Main from '../components/main/Main.vue'
import RealTimeStatistics from '../views/data/RealTimeStatistics.vue'
import MissionProgress from '../views/data/MissionProgress.vue'
import RiskOccured from '../views/management/RiskOccured.vue'
import ElderInfo from "@/views/mission/ElderInfo.vue";
import FamilyInfo from "@/views/mission/FamilyInfo.vue";
import LogsInfo from "@/views/mission/LogsInfo.vue";
// 1. 创建组件
Vue.use(VueRouter)
// 2. 路由和组件进行映射，变量必须叫routes
const routes = [{
    // 标准写法：根目录对应App，重定向到login
    // Vue会按照组件的嵌套顺序寻找router-view并对应相应的组件
    path: '/',
    component: App,
    redirect: '/login',
    children: [
        {
            path: "/login", component: LoginPage
        },
        {
            path: "/main",
            component: Main,
            redirect: '/main/realtime-statistics',
            children: [
                {
                    // 实时统计
                    path: "realtime-statistics",
                    component: RealTimeStatistics,
                },
                {
                    // 任务进展
                    path: "mission-progress",
                    component: MissionProgress,
                },
                {
                    // 风险发生管理
                    path: "risk-occured",
                    component: RiskOccured,
                },
                {
                    // 易风险老人信息状态管理
                    path: "elder-info",
                    component: ElderInfo,
                },
                {
                    // 家属信息管理
                    path: "family-info",
                    component: FamilyInfo,
                },
                {
                    // 日志信息管理
                    path: "logs-info",
                    component: LogsInfo,
                },
            ]
        }

    ]
}


]
// 3. 进行注册
const router = new VueRouter({
    routes
})
// 4. 配置路由守卫
router.beforeEach((to, from, next) => {
    // 在路由切换之前执行的操作
    let status = sessionStorage.getItem("status")
    // console.log(status)
    if (status !== null && status === 'true') {
        // 已经登录
        if (to.path === '/login' || to.path === '/') {
            next('/main/realtime-statistics')
        } else {
            next();
        }
    } else {
        if (to.path === '/' || to.path === '/login') {
            next()
        } else {
            next('/');
        }
    }
    // 调用 next() 进行路由跳转
});
// 5. 对节点进行暴露
export default router
// 6. 在main.js中挂入router
// 7. 在应该显示路由的位置显示路由


// 嵌套路由，即多级路由
// 第一级路由在父组件中以router-view为路由出口
// 第二级路由则在第一级路由对应组件中设置router-view路由出口