package str.simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_609_FindDuplicateFileInSystem {
    public static void main(String[] args) {
        String[] paths = {
                "root/a 1.txt(abcd) 2.txt(efgh)",
                "root/c 3.txt(abcd)",
                "root/c/d 4.txt(efgh)",
                "root 4.txt(efgh)"
        };
        System.out.println(findDuplicate(paths));
    }

    public static List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> list = new ArrayList<>();
        //String用来存储文件的内容，将内容相同的目录存在在相同的键下
        Map<String, List<String>> map = new HashMap<>();
        //遍历paths
        for(String s: paths) {
            //files用来存储按照空格分割的字符串，其中files[0]存储的是文件的根目录，files[1]存储的是文件名称和文件内容
            String[] files = s.split(" ");
            //root用来存储文件根目录
            String root = files[0];
            //对每个根目录进行遍历
            for(int i = 1; i < files.length; i++) {
                //以"()"来分割每个字符串的文件内容
                String[] content = files[i].split("[()]");
                List<String> newContent = map.getOrDefault(content[1], new ArrayList<>());
                newContent.add(root + "/" + content[0]);
                map.put(content[1], newContent);
            }
        }
        for(List<String> s: map.values()) {
            if(s.size() > 1) {
                list.add(s);
            }
        }
        return list;
    }
}
