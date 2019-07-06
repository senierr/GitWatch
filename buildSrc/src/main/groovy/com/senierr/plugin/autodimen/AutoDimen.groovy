package com.senierr.plugin.autodimen

import org.gradle.api.Plugin
import org.gradle.api.Project

class AutoDimen implements Plugin<Project> {

    private def static minSP = 5   // 生成最小SP尺寸
    private def static minDP = 0   // 生成最小DP尺寸

    @Override
    void apply(Project project) {
        // 创建DSL
        def extension  = project.extensions.create('AutoDimen', DesignExtension)
        // 创建任务
        project.task('autoDimen') {
            group = "senierr"
            doFirst {
                executeTask(project, extension)
            }
        }
    }

    /**
     * 执行任务
     *
     * @param project 项目
     * @param extension 扩展属性
     */
    static void executeTask(Project project, def extension) {
        // 当为设计图尺寸模板，输出至默认文件夹一份
        makeDimens(getDimenFile(project.projectDir, "values"), extension.designSW, extension.maxDP, extension.maxSP, extension.designSW)
        for (int i = extension.swMin; i <= extension.swMax; i = i + extension.interval) {
            makeDimens(getDimenFile(project.projectDir, "values-sw" + i + "dp"), i, extension.maxDP, extension.maxSP, extension.designSW)
        }
    }

    /**
     * 创建dimen文件内容
     *
     * @param sw 最小宽度 dp
     * @param maxDP 最大尺寸 dp
     * @param maxSP 最大尺寸 sp
     * @param designSW 设计图最小宽度 dp
     * @return
     */
    private static void makeDimens(File dimenFile, int sw, int maxDP, int maxSP, int designSW) {
        if (sw <= 0 || designSW <= 0) return

        StringBuilder sb = new StringBuilder()
        try {
            sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n")
            sb.append("<resources>\r\n")
            // 生成SP
            for (int i = minSP; i <= maxSP; i++) {
                float spValue = getActualDimen((float) i, (float) sw, (float) designSW)
                sb.append("\t<dimen name=\"sp_" + i + "\">" + spValue + "sp</dimen>\r\n")
            }
            // 生成DP
            for (int i = minDP; i <= maxDP; i++) {
                float dpValue = getActualDimen((float) i, (float) sw, (float) designSW)
                sb.append("\t<dimen name=\"dp_" + i + "\">" + dpValue + "dp</dimen>\r\n")
            }
            sb.append("</resources>\r\n")
        } catch (Exception e) {
            e.printStackTrace()
        }
        outputDimenFile(dimenFile, sb.toString())
    }

    /**
     * 输出文件
     *
     * @param dimenFile
     * @param content
     */
    private static void outputDimenFile(File dimenFile, String content) {
        try {
            FileOutputStream fos = new FileOutputStream(dimenFile.absolutePath)
            fos.write(content.getBytes())
            fos.flush()
            fos.close()
        } catch (FileNotFoundException e) {
            e.printStackTrace()
        } catch (IOException e) {
            e.printStackTrace()
        }
    }

    /**
     * 获取Dimen文件
     *
     * @param projectDir
     * @param folderName
     * @return
     */
    private static File getDimenFile(File projectDir, String folderName) {
        // 创建文件夹
        File dir = new File(projectDir.absolutePath + File.separator +
                "src" + File.separator +
                "main" + File.separator +
                "res" + File.separator +
                folderName)
        if (!dir.exists()) dir.mkdirs()
        // 创建文件
        File dimenFile = new File(dir, "dimens.xml")
        if (dimenFile.exists()) {
            dimenFile = new File(dir, "dimens_auto.xml")
        }
        if (dimenFile.exists()) {
            dimenFile.delete()
        }
        return dimenFile
    }

    /**
     * 获取实际尺寸
     *
     * @param value 值
     * @param sw 手机最小边
     * @param designSW 设计图最小宽
     * @return
     */
    private static float getActualDimen(float value, float sw, float designSW) {
        float dpValue = value * sw / designSW
        BigDecimal bigDecimal = new BigDecimal(dpValue)
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()
    }
}