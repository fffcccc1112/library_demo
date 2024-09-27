import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    proxy: {
      // 代理配置，解决跨域
      '/api': {
        target: 'http://localhost:8088', // 后端服务的地址
        changeOrigin: true, // 是否允许跨域
        rewrite: (path) => path.replace(/^\/api/, ''), // 重写路径
      },
    },
  },
})
