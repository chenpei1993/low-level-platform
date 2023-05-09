<template>
    <div class="common-layout">
        <el-container>
            <el-header style="padding: 0">
                <Header />
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <Menu />
                </el-aside>
                <el-main>
                    <router-view/>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import Header from '@/components/app/Header.vue'
import Menu from '@/components/app/Menu.vue'

export default {
    name: 'Index',
    components: {
        Header, Menu
    }
}
</script>

