package com.ruoyi.web.controller.system;

public class Test {
    public static String removeBackslashesFromImgTag(String input) {
        // 修改后的正则表达式
        String regex = "(?<=<img[^>]*src=\")[^\"]*\\\\(?=[^\"]*\\\")";
        return input.replaceAll(regex, "");
    }
    public static void main(String[] args) {
        String input = "合肥日报讯<img src=\"/dev-api/profile/upload/2024/08/24/snipaste20240824_222029_20240824222049A001.png\" />哈哈哈哈";
        // 第一步: 替换 src=\" 成 src="
        String step1 = input.replaceAll("src=\\\"", "src=\"");
        // 第二步: 替换所有的 \" 成 "
        String step2 = step1.replaceAll("\\\\\"", "\"");
        System.out.println(step2);

    }
}
