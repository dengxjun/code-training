package heyman.org.com.v1.cookmenu;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/10
 */
public class CookBookLoaderTest {
    private CookBookLoader bookLoader;

    @Before
    public void setup(){
        bookLoader =  CookBookLoader.getInstance();
    }

    @Test
    public void getResourcesFiles() throws Exception {
        File[] tests = bookLoader.getResourcesFiles("test");
        for (File f : tests){
            System.out.println(f.getName());
        }
    }

    @Test
    public void loadAndConvert() throws IOException {
        HCookBook[] hCookBooks = bookLoader.loadAndConvert();
        for (HCookBook cookBook : hCookBooks){
            System.out.println(cookBook.getCookMenuName());
        }
    }

}