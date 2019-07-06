# Gradle插件

### AutoDimen

```
// 1. 导入插件
apply plugin: 'AutoDimen'

// 2. 自定义配置
AutoDimen { // 以下为默认值
    designSW = 360  // 设计图最小宽度
    swMin = 300     // 生成最小SmallestWidth
    swMax = 500     // 生成最大SmallestWidth
    interval = 10   // 间隔值
    maxDP = 400     // 需要生成的最大DP尺寸
    maxSP = 60      // 需要生成的最大SP尺寸
}

// 3. 执行Task
执行项目->Gradle->senierr->autoDimen
```
