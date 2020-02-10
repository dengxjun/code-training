package heyman.org.com.v1.cookmenu;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>加载资源目录-cookbook下食谱文件: </p>
 *
 * @author heyman
 * @date 2020/2/10
 */
public class CookBookLoader {

    private final static String DEFAULT_RESOURCE_PATH = "cookbook";

    private HCookBook[] cachedCookBooks;

    private static volatile CookBookLoader instance;

    private CookBookLoader(){}

    public synchronized static CookBookLoader getInstance(){
        instance = new CookBookLoader();
        return instance;
    }

    public HCookBook getCookBook(MenuLsit menuLsit) throws IOException {
        loadAndConvert();

        for (HCookBook cookBook : cachedCookBooks){
            if (cookBook.getCookMenuName().equals(menuLsit.getValue())){
                return cookBook;
            }
        }

        return null;
    }

    public File[] getResourcesFiles(String resourcePath){
        URL url = CookBookLoader.class.getClassLoader().getResource(resourcePath);
        if (url == null){
            url = CookBookLoader.class.getClassLoader().getResource(DEFAULT_RESOURCE_PATH);
        }

        if (url == null){
            return new File[0];
        }

        File rootFile = new File(url.getFile());
        List<File> resourceFiles = new ArrayList<>();
        loopFile(rootFile, resourceFiles);
        return resourceFiles.toArray(new File[resourceFiles.size()]);
    }

    public HCookBook[] loadAndConvert() throws IOException {
        if (cachedCookBooks != null && cachedCookBooks.length > 0){
            return cachedCookBooks;
        }
        List<HCookBook> cookBooks = new ArrayList<>();
        File[] files = getResourcesFiles(DEFAULT_RESOURCE_PATH);
        for (File file : files){
            convert(file, cookBooks);
        }
        cachedCookBooks = cookBooks.toArray(new HCookBook[cookBooks.size()]);
        return cachedCookBooks;
    }

    private void convert(File file, List<HCookBook> cookBooks) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setDefaultUseWrapper(true);
        //自动忽略无法对应pojo的字段
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        CommonCookBook cookBook = xmlMapper.readValue(file, CommonCookBook.class);
        cookBooks.add(cookBook);
    }

    private void loopFile(File rootFile, List<File> resourceFiles){
        if (rootFile.isDirectory()){
            File[] files = rootFile.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    String fileName = pathname.getName();
                    int index = fileName.indexOf(".");
                    String subfix = fileName.substring(index);
                    return pathname.canRead() && ".xml".equals(subfix);
                }
            });

            for (File file : files){
                loopFile(file, resourceFiles);
            }
        }else {
            resourceFiles.add(rootFile);
        }
    }
}
